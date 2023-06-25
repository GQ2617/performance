package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.ProjectStaff;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目-绩效关系表(ProjectStaff)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:55
 */
@Mapper
public interface ProjectStaffDao extends BaseMapper<ProjectStaff> {

}

