package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Salary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工资表(Salary)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:54
 */
@Mapper
public interface SalaryDao extends BaseMapper<Salary> {

}

