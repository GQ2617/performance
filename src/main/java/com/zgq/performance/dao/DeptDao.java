package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表(Dept)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:56
 */
@Mapper
public interface DeptDao extends BaseMapper<Dept> {

}

