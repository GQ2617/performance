package com.zgq.performance.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Admin;
import com.zgq.performance.service.AdminService;
import com.zgq.performance.service.PermissionService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: zgq
 * Create: 2023/6/3 11:06
 * Description:
 */
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "管理员模块")
public class AdminController {

    private final AdminService adminService;
    private final RedisTemplate redisTemplate;
    private final PermissionService permissionService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public R login(String username, String password, String verCode) {
        if (username.length() < 3 || username.length() > 8) {
            return R.error("账号长度应在3-8位");
        }
        if (password.length() < 3 || password.length() > 16) {
            return R.error("密码长度应在3-16位");
        }

        if (!"".equals(verCode)) {
            String vercode = (String) redisTemplate.opsForValue().get("vercode");
            if (!verCode.equals(vercode)) {
                return R.error("验证码错误");
            }
        }

        Admin admin = adminService.getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, username));

        if (admin == null) {
            return R.error("账号不存在");
        }

        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        System.out.println(md5Pwd);
        if (!md5Pwd.equals(admin.getPassword())) {
            return R.error("密码错误");
        }

        if (admin.getStatus() == 1) {
            return R.error("账号已被禁用，请联系管理员");
        }

        // jwt生成token
        JwtBuilder builder = Jwts.builder();
        Map<String, Object> map = new HashMap<>();
        String token = builder.setSubject(username) // token携带的数据
                .setIssuedAt(new Date()) // token生成时间
                .setId(admin.getId() + "") // 设置用户id为token的唯一标识
                .setClaims(map) // map中可以存放用户的角色权限信息
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * 3)) // token过期时间
                .signWith(SignatureAlgorithm.HS256, "performance_token") // 设置加密方式和加密密码
                .compact();

        // 登录成功，清除redis缓存的验证码
        redisTemplate.delete("vercode");

        // 查询全部权限
        map.put("permission", permissionService.list());

        return new R(0, token, admin, map);
    }

    @GetMapping
    @ApiOperation("管理员列表")
    public R get(String username) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(username), Admin::getUsername, username);

        return R.success(adminService.list(queryWrapper));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取管理员")
    public R getById(@PathVariable("id") Integer id) {
        return R.success(adminService.getById(id));
    }

    @PostMapping
    @ApiOperation("添加管理员")
    public R add(@RequestBody Admin admin) {
        admin.setCreateTime(DateTime.now());
        admin.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        return R.success(adminService.save(admin));
    }

    @PutMapping("/info")
    @ApiOperation("修改信息")
    public R updateInfo(@RequestBody Admin admin) {
        Admin sAdmin = adminService.getById(admin.getId());
        sAdmin.setUsername(admin.getUsername());
        sAdmin.setRemarks(admin.getRemarks());
        sAdmin.setStatus(admin.getStatus());
        return R.success(adminService.updateById(sAdmin));
    }

    @PutMapping("/password")
    @ApiOperation("修改密码")
    public R updateStatus(Integer id, String newPwd, String oldPwd) {
        Admin admin = adminService.getById(id);
        if (newPwd.equals(oldPwd)) {
            return R.error("新旧密码一致");
        }

        if (!admin.getPassword().equals(DigestUtils.md5DigestAsHex(oldPwd.getBytes()))) {
            return R.error("旧密码错误");
        }

        admin.setPassword(DigestUtils.md5DigestAsHex(newPwd.getBytes()));
        return R.success(adminService.updateById(admin));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除管理员")
    public R delete(@PathVariable("id") Integer id) {
        Admin admin = adminService.getById(id);
        if (admin == null) {
            return R.error("账号不存在");
        }
        return R.success(adminService.removeById(admin));
    }
}
