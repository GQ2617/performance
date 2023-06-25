package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色权限关系表(RolePermission)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermission> {

}

