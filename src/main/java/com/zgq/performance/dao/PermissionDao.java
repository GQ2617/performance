package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限表(Permission)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

}

