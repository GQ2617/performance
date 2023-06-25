package com.zgq.performance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgq.performance.dao.PerformanceDao;
import com.zgq.performance.entity.Performance;
import com.zgq.performance.service.PerformanceService;
import org.springframework.stereotype.Service;

/**
 * 绩效表(Performance)表服务实现类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@Service("performanceService")
public class PerformanceServiceImpl extends ServiceImpl<PerformanceDao, Performance> implements PerformanceService {

}

