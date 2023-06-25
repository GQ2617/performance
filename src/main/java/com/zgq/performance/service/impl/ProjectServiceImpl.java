package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.ProjectDao;
import com.zgq.performance.entity.Project;
import com.zgq.performance.service.ProjectService;
import org.springframework.stereotype.Service;

/**
 * 项目表(Project)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("projectService")
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {

}

