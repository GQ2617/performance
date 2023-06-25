package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.PermissionDao;
import com.zgq.performance.entity.Permission;
import com.zgq.performance.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * 权限表(Permission)表服务实现类
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@Service("permissionService")
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

}

