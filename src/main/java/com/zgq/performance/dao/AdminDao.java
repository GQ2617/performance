package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员表(Admin)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:52
 */
@Mapper
public interface AdminDao extends BaseMapper<Admin> {

}

