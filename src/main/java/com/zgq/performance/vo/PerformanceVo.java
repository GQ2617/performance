package com.zgq.performance.vo;

import com.zgq.performance.entity.Performance;
import lombok.Data;

import java.util.List;

/**
 * Author: zgq
 * Create: 2023/6/9 8:49
 * Description:
 */
@Data
public class PerformanceVo {
    List<Performance> performances;
    Integer count;
    Double total;
}
