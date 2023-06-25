package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.RolePermissionDao;
import com.zgq.performance.entity.RolePermission;
import com.zgq.performance.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * 角色权限关系表(RolePermission)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

}

