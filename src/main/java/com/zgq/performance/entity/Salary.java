package com.zgq.performance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 工资表(Salary)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@SuppressWarnings("serial")
@Data
@ApiModel("工资表实体类")
public class Salary extends Model<Salary> {
    // 工资单id
    @ApiModelProperty("工资单id")
    private Integer id;
    // 员工id
    @ApiModelProperty("员工id")
    private Integer staffId;
    // 年份
    @ApiModelProperty("年份")
    private String year;
    // 月份
    @ApiModelProperty("月份")
    private String month;
    // 绩效工资
    @ApiModelProperty("绩效工资")
    private Double salaryPerform;

    // 员工信息
    @TableField(exist = false)
    private Staff staff;

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

