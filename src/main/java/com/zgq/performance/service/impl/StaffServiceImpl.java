package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.StaffDao;
import com.zgq.performance.entity.Staff;
import com.zgq.performance.service.StaffService;
import org.springframework.stereotype.Service;

/**
 * 员工表(Staff)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("staffService")
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService {

}

