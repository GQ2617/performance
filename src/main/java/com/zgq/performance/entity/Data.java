package com.zgq.performance.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 字典表(Data)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@SuppressWarnings("serial")
@lombok.Data
@ApiModel("字典表实体类")
public class Data extends Model<Data> {
    // id
    @ApiModelProperty("id")
    private Integer id;
    // 数据id
    @ApiModelProperty("数据id")
    private Integer dataId;
    // 数据名称
    @ApiModelProperty("数据名称")
    private String dataName;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

