package com.zgq.performance.controller;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zgq.performance.common.R;
import com.zgq.performance.entity.*;
import com.zgq.performance.service.*;
import com.zgq.performance.vo.PerformanceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: zgq
 * Create: 2023/6/8 8:44
 * Description:
 */
@RestController
@RequestMapping("/api/performance")
@RequiredArgsConstructor
@CrossOrigin
@Api(tags = "绩效工时模块")
@Transactional
public class PerformanceController {

    private final PerformanceService performanceService;
    private final ProjectStaffService projectStaffService;
    private final StaffService staffService;
    private final ProjectService projectService;
    private final SalaryService salaryService;

    @GetMapping
    @ApiOperation("根据年月获取绩效表")
    public R getByYearAndMonth(String year, String month) {
        PerformanceVo performanceVo = new PerformanceVo();
        // 绩效
        LambdaQueryWrapper<Performance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Strings.isNotEmpty(year), Performance::getYear, year);
        queryWrapper.eq(Strings.isNotEmpty(month), Performance::getMonth, month);
        List<Performance> list = performanceService.list(queryWrapper);
        list = list.stream().map(item -> {
            // 查询员工姓名
            Integer staffId = item.getStaffId();
            Staff staff = staffService.getById(staffId);
            item.setStaff(staff);
            // 查询项目
            Integer performanceId = item.getId();
            LambdaQueryWrapper<ProjectStaff> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(ProjectStaff::getPerformanceId, performanceId);
            List<ProjectStaff> projectStaffs = projectStaffService.list(queryWrapper1);
            List<Project> projects = projectStaffs.stream().map(projectStaff -> {
                Integer projectId = projectStaff.getProjectId();
                return projectService.getById(projectId);
            }).collect(Collectors.toList());
            item.setProjects(projects);
            return item;
        }).collect(Collectors.toList());
        performanceVo.setPerformances(list);
        // 在职人数
        long count = performanceService.count(queryWrapper);
        performanceVo.setCount((int) count);
        // 总工资
        Double total = 0.0;
        for (Performance performance : list) {
            // 绩效工资
            Integer staffId = performance.getStaffId();
            LambdaQueryWrapper<Salary> salaryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            salaryLambdaQueryWrapper.eq(Salary::getStaffId, staffId);
            salaryLambdaQueryWrapper.eq(Salary::getYear, year);
            salaryLambdaQueryWrapper.eq(Salary::getMonth, month);
            List<Salary> salaries = salaryService.list(salaryLambdaQueryWrapper);
            for (Salary salary : salaries) {
                Double salaryPerform = salary.getSalaryPerform();
                total += salaryPerform;
            }
            // 基本工资
            Staff staff = staffService.getById(staffId);
            Double salaryBase = staff.getSalaryBase();
            total += salaryBase;
        }
        performanceVo.setTotal(total);
        return R.success(performanceVo);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据员工id获取绩效表")
    public R getById(@PathVariable("id") Integer id) {
        LambdaQueryWrapper<Performance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Performance::getStaffId, id);
        // 查询绩效
        List<Performance> list = performanceService.list(queryWrapper);
        // 查询项目
        list = list.stream().map(item -> {
            Integer performanceId = item.getId();
            LambdaQueryWrapper<ProjectStaff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ProjectStaff::getPerformanceId, performanceId);
            List<ProjectStaff> projectStaffs = projectStaffService.list(lambdaQueryWrapper);
            List<Project> projects = projectStaffs.stream().map(projectStaff -> {
                Integer projectId = projectStaff.getProjectId();
                return projectService.getById(projectId);
            }).collect(Collectors.toList());
            item.setProjects(projects);
            return item;
        }).collect(Collectors.toList());
        return R.success(list);
    }

    @GetMapping("/leader/{leaderId}")
    @ApiOperation("根据领导id获取绩效表")
    public R getByLeaderId(@PathVariable("leaderId") Integer leaderId) {

        // 查询下属员工
        LambdaQueryWrapper<Staff> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Staff::getLeaderId, leaderId);
        List<Staff> staffs = staffService.list(lambdaQueryWrapper);
        if (staffs.size() == 0) {
            return R.success(null);
        }
        List<Integer> staffId = staffs.stream().map(Staff::getId).collect(Collectors.toList());
        LambdaQueryWrapper<Performance> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Performance::getStaffId, staffId);
        List<Performance> list = performanceService.list(queryWrapper);

        list = list.stream().map(item -> {
            // 根据员工id查询员工
            Integer staffId1 = item.getStaffId();
            Staff staff = staffService.getById(staffId1);
            item.setStaff(staff);
            // 根据项目id查询项目
            Integer performanceId = item.getId();
            LambdaQueryWrapper<ProjectStaff> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(ProjectStaff::getPerformanceId, performanceId);
            List<ProjectStaff> projectStaffs = projectStaffService.list(queryWrapper1);
            List<Project> projects = projectStaffs.stream().map(projectStaff -> {
                Integer projectId = projectStaff.getProjectId();
                return projectService.getById(projectId);
            }).collect(Collectors.toList());
            item.setProjects(projects);
            return item;
        }).collect(Collectors.toList());
        return R.success(list);
    }

    @PostMapping
    @ApiOperation("绩效工时填报")
    public R save(@RequestBody Performance performance) {
        performance.setWriteTime(DateTime.now());
        // 添加绩效工时
        performanceService.save(performance);

        // 添加绩效对应项目
        List<Integer> projectIds = performance.getProjectIds();
        Integer performanceId = performance.getId();
        List<ProjectStaff> projectStaffs = projectIds.stream().map(item -> {
            ProjectStaff projectStaff = new ProjectStaff();
            projectStaff.setProjectId(item);
            projectStaff.setPerformanceId(performanceId);
            return projectStaff;
        }).collect(Collectors.toList());
        projectStaffService.saveBatch(projectStaffs);


        return R.success(null);
    }

    @PutMapping
    @ApiOperation("绩效工时审批")
    public R examine(Integer id, Integer scoreLeader, String scoreDesc) {
        // 审批
        Performance performance = performanceService.getById(id);
        performance.setApproveTime(DateTime.now());
        performance.setScoreLeader(scoreLeader);
        performance.setScoreDesc(scoreDesc);
        performanceService.updateById(performance);

        // 生成工资
        Salary salary = new Salary();
        salary.setStaffId(performance.getStaffId());
        salary.setYear(performance.getYear());
        salary.setMonth(performance.getMonth());
        Double salaryPerform = Double.parseDouble(performance.getWorkTime()) + performance.getScoreSelf() + performance.getScoreLeader() * 3;
        salary.setSalaryPerform(salaryPerform);
        salaryService.save(salary);
        return R.success(null);
    }
}
