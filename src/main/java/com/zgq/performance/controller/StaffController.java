package com.zgq.performance.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.*;
import com.zgq.performance.service.*;
import com.zgq.performance.util.MinioUtilS;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工表(Staff)表控制层
 *
 * @author makejava
 * @since 2023-06-05 15:08:11
 */
@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "员工模块")
public class StaffController {
    /**
     * 服务对象
     */
    private final StaffService staffService;
    private final DeptService deptService;
    private final DataService dataService;
    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;

    @PostMapping("/login")
    @ApiOperation("员工登录")
    public R login(String workNo, String password) {
        if (workNo.length() < 3 || workNo.length() > 11) {
            return R.error("工号长度应在3-11位");
        }
        if (password.length() < 3 || password.length() > 16) {
            return R.error("密码长度应在3-16位");
        }
        Staff staff = staffService.getOne(new LambdaQueryWrapper<Staff>().eq(Staff::getWorkNo, workNo));
        if (staff == null) {
            return R.error("员工不存在");
        }

        String md5Pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Pwd.equals(staff.getPassword())) {
            return R.error("密码不正确");
        }

        // jwt生成token
        JwtBuilder builder = Jwts.builder();
        Map<String, Object> map = new HashMap<>();
        String token = builder.setSubject(workNo) // token携带的数据
                .setIssuedAt(new Date()) // token生成时间
                .setId(staff.getId() + "") // 设置用户id为token的唯一标识
                .setClaims(map) // map中可以存放用户的角色权限信息
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000 * 3)) // token过期时间
                .signWith(SignatureAlgorithm.HS256, "performance_token") // 设置加密方式和加密密码
                .compact();

        // 查询权限
        Integer labelId = staff.getLabelId();
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getLabelid, labelId);
        List<RolePermission> rolePermissionList = rolePermissionService.list(queryWrapper);
        List<Permission> permissions = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList) {
            Integer permissionId = rolePermission.getPermissionId();
            Permission permission = permissionService.getById(permissionId);
            permissions.add(permission);
        }
        map.put("permission", permissions);
        return new R(0, token, staff, map);
    }


    @GetMapping
    @ApiOperation("员工列表")
    public R selectAll(Staff staff) {
        LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(staff.getName()), Staff::getName, staff.getName());
        queryWrapper.eq(staff.getDeptId() != null, Staff::getDeptId, staff.getDeptId());
        queryWrapper.eq(staff.getLabelId() != null, Staff::getLabelId, staff.getLabelId());
        queryWrapper.eq(staff.getStatus() != null, Staff::getStatus, staff.getStatus());
        List<Staff> list = staffService.list(queryWrapper);
        list = list.stream().map(item -> {
            Integer deptId = item.getDeptId();
            Integer leaderId = item.getLeaderId();
            Integer labelId = item.getLabelId();
            Integer statusId = item.getStatus();

            Dept dept = deptService.getById(deptId);
            item.setDeptName(dept.getName());
            if (leaderId == 0) {
                item.setLeaderName("无");
            } else {
                Staff staffById = staffService.getById(leaderId);
                item.setLeaderName(staffById.getName());
            }
            Data label = dataService.getById(labelId);
            item.setLabelName(label.getDataName());
            Data status = dataService.getById(statusId);
            item.setStatusName(status.getDataName());

            return item;
        }).collect(Collectors.toList());

        return R.success(list);
    }

    @GetMapping("/{deptId}")
    @ApiOperation("部门id获取员工")
    public R getByDeptId(@PathVariable("deptId") Integer deptId) {
        return R.success(staffService.list(new LambdaQueryWrapper<Staff>().eq(Staff::getDeptId, deptId)));
    }

    @PostMapping
    @ApiOperation("添加员工")
    public R insert(@RequestBody Staff staff) {
        staff.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        return R.success(staffService.save(staff));
    }

    @PutMapping
    @ApiOperation("修改员工")
    public R update(@RequestBody Staff staff) {
        return R.success(staffService.updateById(staff));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除员工")
    public R delete(@PathVariable("id") Integer id) {
        return R.success(staffService.removeById(id));
    }
}

