package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.ProjectStaffDao;
import com.zgq.performance.entity.ProjectStaff;
import com.zgq.performance.service.ProjectStaffService;
import org.springframework.stereotype.Service;

/**
 * 项目-绩效关系表(ProjectStaff)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("projectStaffService")
public class ProjectStaffServiceImpl extends ServiceImpl<ProjectStaffDao, ProjectStaff> implements ProjectStaffService {

}

