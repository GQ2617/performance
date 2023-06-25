package com.zgq.performance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 部门表(Dept)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:55:09
 */
@SuppressWarnings("serial")
@Data
@ApiModel("部门表实体类")
public class Dept extends Model<Dept> {
    // 部门id
    @ApiModelProperty("部门id")
    private Integer id;
    // 部门名称
    @ApiModelProperty("部门名称")
    private String name;
    // 部门负责人
    @ApiModelProperty("部门负责人")
    private Integer leaderId;
    // 部门电话
    @ApiModelProperty("部门电话")
    private String phone;
    // 备注
    @ApiModelProperty("备注")
    private String remarks;

    @TableField(exist = false)
    private String leader;
    @TableField(exist = false)
    private Long count;


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

