package com.zgq.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Dept;
import com.zgq.performance.entity.Staff;
import com.zgq.performance.service.DeptService;
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
 * Create: 2023/6/5 14:27
 * Description:
 */
@RestController
@RequestMapping("/api/dept")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "部门模块")
public class DeptController {
    private final DeptService deptService;
    private final StaffService staffService;

    @GetMapping
    @ApiOperation("部门列表")
    public R list(String name) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), Dept::getName, name);
        List<Dept> list = deptService.list(queryWrapper);
        list = list.stream().map(item -> {
            Integer leaderId = item.getLeaderId();
            Staff staff = staffService.getById(leaderId);
            item.setLeader(staff.getName());

            Integer deptId = item.getId();
            long count = staffService.count(new LambdaQueryWrapper<Staff>().eq(Staff::getDeptId, deptId));
            item.setCount(count);

            return item;
        }).collect(Collectors.toList());
        return R.success(list);
    }

    @PostMapping
    @ApiOperation("添加部门")
    public R save(@RequestBody Dept dept) {
        return R.success(deptService.save(dept));
    }

    @PutMapping
    @ApiOperation("修改部门")
    public R update(@RequestBody Dept dept) {
        return R.success(deptService.updateById(dept));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除部门")
    public R delete(@PathVariable("id") Integer id) {
        return R.success(deptService.removeById(id));
    }

}
