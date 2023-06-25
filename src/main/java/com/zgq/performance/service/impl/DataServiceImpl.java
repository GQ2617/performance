package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.DataDao;
import com.zgq.performance.entity.Data;
import com.zgq.performance.service.DataService;
import org.springframework.stereotype.Service;

/**
 * 字典表(Data)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("dataService")
public class DataServiceImpl extends ServiceImpl<DataDao, Data> implements DataService {

}

