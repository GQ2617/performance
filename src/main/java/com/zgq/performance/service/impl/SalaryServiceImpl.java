package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.SalaryDao;
import com.zgq.performance.entity.Salary;
import com.zgq.performance.service.SalaryService;
import org.springframework.stereotype.Service;

/**
 * 工资表(Salary)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("salaryService")
public class SalaryServiceImpl extends ServiceImpl<SalaryDao, Salary> implements SalaryService {

}

