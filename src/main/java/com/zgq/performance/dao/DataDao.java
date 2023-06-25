package com.zgq.performance.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgq.performance.entity.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典表(Data)表数据库访问层
 *
 * @author makejava
 * @since 2023-06-03 11:57:55
 */
@Mapper
public interface DataDao extends BaseMapper<Data> {

}

