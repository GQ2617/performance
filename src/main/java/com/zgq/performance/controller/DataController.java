package com.zgq.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Data;
import com.zgq.performance.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

/**
 * Author: zgq
 * Create: 2023/6/5 21:26
 * Description:
 */
@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "字典数据")
public class DataController {

    private final DataService dataService;

    @GetMapping("/label")
    @ApiOperation("员工标签")
    public R getLabel() {
        return R.success(dataService.list(new LambdaQueryWrapper<Data>().eq(Data::getDataId, 2)));
    }

    @GetMapping("/status")
    @ApiOperation("员工状态")
    public R getStatus() {
        return R.success(dataService.list(new LambdaQueryWrapper<Data>().eq(Data::getDataId, 1)));
    }

    @GetMapping("/project")
    @ApiOperation("项目状态")
    public R getProjectStatus() {
        return R.success(dataService.list(new LambdaQueryWrapper<Data>().eq(Data::getDataId, 3)));
    }
}
