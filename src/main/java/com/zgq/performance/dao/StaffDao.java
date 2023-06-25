package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Staff;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工表(Staff)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:56
 */
@Mapper
public interface StaffDao extends BaseMapper<Staff> {

}

