package com.zgq.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Data;
import com.zgq.performance.entity.Project;
import com.zgq.performance.entity.Staff;
import com.zgq.performance.service.DataService;
import com.zgq.performance.service.DeptService;
import com.zgq.performance.service.ProjectService;
import com.zgq.performance.service.StaffService;
import com.zgq.performance.vo.CountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zgq
 * Create: 2023/6/10 13:38
 * Description:
 */
@RestController
@RequestMapping("/api/count")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "首页统计")
public class CountController {
    private final DataService dataService;
    private final ProjectService projectService;
    private final DeptService deptService;
    private final StaffService staffService;


    @GetMapping("/project")
    @ApiOperation("各状态项目数量")
    public R getProjectCount() {
        // 各状态项目
        List<Data> projectStatusList = dataService.list(new LambdaQueryWrapper<Data>().eq(Data::getDataId, 3));
        List<CountVo> countVos = projectStatusList.stream().map(data -> {
            CountVo countVo = new CountVo();
            // 状态id
            Integer statusId = data.getId();
            countVo.setId(statusId);
            // 状态名
            String dataName = data.getDataName();
            countVo.setName(dataName);
            // 状态数量
            LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Project::getStatus, statusId);
            long count = projectService.count(queryWrapper);
            countVo.setCount(count);
            return countVo;
        }).collect(Collectors.toList());

        return R.success(countVos);
    }

    @GetMapping("/staff")
    @ApiOperation("各状态员工数量")
    public R getStaffCount() {
        // 员工状态
        List<Data> staffList = dataService.list(new LambdaQueryWrapper<Data>().eq(Data::getDataId, 1));

        List<CountVo> countVos = staffList.stream().map(item -> {
            CountVo countVo = new CountVo();
            // 状态id
            Integer statusId = item.getId();
            countVo.setId(statusId);
            // 状态名
            String dataName = item.getDataName();
            countVo.setName(dataName);
            // 数量
            LambdaQueryWrapper<Staff> queryWrapper = new LambdaQueryWrapper<Staff>();
            queryWrapper.eq(Staff::getStatus, statusId);
            long count = staffService.count(queryWrapper);
            countVo.setCount(count);
            return countVo;
        }).collect(Collectors.toList());

        return R.success(countVos);
    }

    @GetMapping("/dept")
    @ApiOperation("部门数量")
    public R getDeptCount() {
        CountVo countVo = new CountVo();
        long count = deptService.count();
        countVo.setCount(count);
        return R.success(countVo);
    }
}
