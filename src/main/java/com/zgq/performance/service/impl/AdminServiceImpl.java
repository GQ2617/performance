package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.AdminDao;
import com.zgq.performance.entity.Admin;
import com.zgq.performance.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * 管理员表(Admin)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:23
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

}

