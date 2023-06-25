package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.DeptDao;
import com.zgq.performance.entity.Dept;
import com.zgq.performance.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:29
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}

