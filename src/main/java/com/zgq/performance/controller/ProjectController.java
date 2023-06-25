package com.zgq.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Data;
import com.zgq.performance.entity.Project;
import com.zgq.performance.entity.Staff;
import com.zgq.performance.service.DataService;
import com.zgq.performance.service.ProjectService;
import com.zgq.performance.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zgq
 * Create: 2023/6/5 22:32
 * Description:
 */
@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "项目模块")
public class ProjectController {
    private final ProjectService projectService;
    private final StaffService staffService;
    private final DataService dataService;


    @GetMapping
    @ApiOperation("项目列表")
    public R list(String name) {
        LambdaQueryWrapper<Project> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), Project::getName, name);
        List<Project> list = projectService.list(queryWrapper);
        list.stream().map(item -> {
            Integer status = item.getStatus();
            Integer leaderId = item.getLeaderId();

            Data data = dataService.getById(status);
            item.setStatusName(data.getDataName());
            Staff staff = staffService.getById(leaderId);
            item.setLeader(staff.getName());

            return item;
        }).collect(Collectors.toList());

        return R.success(list);
    }

    @PostMapping
    @ApiOperation("添加项目")
    public R add(@RequestBody Project project) {
        return R.success(projectService.save(project));
    }

    @PutMapping
    @ApiOperation("修改项目")
    public R edit(@RequestBody Project project) {
        return R.success(projectService.updateById(project));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除项目")
    public R delete(@PathVariable("id") String id) {
        return R.success(projectService.removeById(id));
    }
}
