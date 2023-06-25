package com.zgq.performance.controller;

import com.zgq.performance.common.R;
import com.zgq.performance.util.VerCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author: zgq
 * Create: 2023/5/11 23:11
 * Description:
 */
@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
@Api(tags = "文件服务")
public class CommonController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${medicine.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public R<String> upload(MultipartFile file) {
        // file是一个临时文件，需要转存到指定位置，否则本次请求完成之后会删除
        log.info("文件：{}", file.toString());
        // 重名问题
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + suffix;
        // 目录文件是否存在
        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 转存指定位置
        try {
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(fileName);
    }

    /**
     * 文件下载
     *
     * @param response
     * @return
     */
    @GetMapping("/download")
    @ApiOperation("文件下载")
    public void download(HttpServletResponse response, String name) {
        log.info("开始下载");
        try {
            // 通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
            // 通过输出流将文件写回浏览器
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/vercode")
    @ApiOperation("验证码")
    public void verCode(HttpServletResponse response) {
        String verCode = VerCodeUtil.createVerCode(response);
        // 缓存到redis，设置有效期为5分钟
        redisTemplate.opsForValue().set("verCode", verCode, 5, TimeUnit.MINUTES);
    }
}
