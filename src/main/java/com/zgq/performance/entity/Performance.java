package com.zgq.performance.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * 绩效表(Performance)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@SuppressWarnings("serial")
@Data
@ApiModel("绩效表实体类")
public class Performance extends Model<Performance> {
    // 绩效id
    @ApiModelProperty("绩效id")
    private Integer id;
    // 员工id
    @ApiModelProperty("员工id")
    private Integer staffId;
    // 工时
    @ApiModelProperty("工时")
    private String workTime;
    // 工作内容
    @ApiModelProperty("工作内容")
    private String workContent;
    // 自评
    @ApiModelProperty("自评")
    private Integer scoreSelf;
    // 领导打分
    @ApiModelProperty("领导打分")
    private Integer scoreLeader;
    // 分数说明
    @ApiModelProperty("分数说明")
    private String scoreDesc;
    // 年份
    @ApiModelProperty("年份")
    private String year;
    // 月份
    @ApiModelProperty("月份")
    private String month;
    // 填报时间
    @ApiModelProperty("填报时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date writeTime;
    // 审批时间
    @ApiModelProperty("审批时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approveTime;


    @TableField(exist = false)
    private List<Integer> projectIds;
    @TableField(exist = false)
    private List<Project> projects;
    // 员工
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

