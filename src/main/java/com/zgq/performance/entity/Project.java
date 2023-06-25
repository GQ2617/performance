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

/**
 * 项目表(Project)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:24
 */
@SuppressWarnings("serial")
@Data
@ApiModel("项目表实体类")
public class Project extends Model<Project> {
    // 项目id
    @ApiModelProperty("项目id")
    private Integer id;
    // 项目名称
    @ApiModelProperty("项目名称")
    private String name;
    // 项目负责人
    @ApiModelProperty("项目负责人")
    private Integer leaderId;
    // 项目金额
    @ApiModelProperty("项目金额")
    private Double allMoney;
    // 已收金额
    @ApiModelProperty("已收金额")
    private Double settleMoney;
    // 项目状态
    @ApiModelProperty("项目状态")
    private Integer status;
    // 合同签订日期
    @ApiModelProperty("合同签订日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    // 合同到期日期
    @ApiModelProperty("合同到期日期")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @TableField(exist = false)
    private String leader;

    @TableField(exist = false)
    private String statusName;

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

