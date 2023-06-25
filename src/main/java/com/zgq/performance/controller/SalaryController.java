package com.zgq.performance.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.Salary;
import com.zgq.performance.entity.Staff;
import com.zgq.performance.service.SalaryService;
import com.zgq.performance.service.StaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zgq
 * Create: 2023/6/10 11:04
 * Description:
 */
@RestController
@RequestMapping("/api/salary")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "工资单")
public class SalaryController {
    private final SalaryService salaryService;
    private final StaffService staffService;

    @GetMapping
    @ApiOperation("根据年月获取工资单")
    public R getAll(String year, String month) {
        LambdaQueryWrapper<Salary> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Strings.isNotEmpty(year), Salary::getYear, year);
        queryWrapper.eq(Strings.isNotEmpty(month), Salary::getMonth, month);
        List<Salary> list = salaryService.list(queryWrapper);
        list = list.stream().map(item -> {
            Integer staffId = item.getStaffId();
            Staff staff = staffService.getById(staffId);
            item.setStaff(staff);
            return item;
        }).collect(Collectors.toList());
        return R.success(list);
    }
}
