package com.zgq.performance.controller;

import com.zgq.performance.common.R;
import com.zgq.performance.util.MinioUtilS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Author: zgq
 * Create: 2023/6/7 17:05
 * Description:
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/api/file")
public class MinioController {
    @Autowired
    private MinioUtilS minioUtilS;
    @Value("${minio.endpoint}")
    private String address;
    @Value("${minio.bucketName}")
    private String bucketName;

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        List<String> upload = minioUtilS.upload(new MultipartFile[]{file});
        // return R.success(address + "/" + bucketName + "/" + upload.get(0));
        return R.success(upload.get(0));
    }

}

