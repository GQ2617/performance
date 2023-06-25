package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Project;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目表(Project)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:54
 */
@Mapper
public interface ProjectDao extends BaseMapper<Project> {

}

