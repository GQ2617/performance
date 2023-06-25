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
 * 员工表(Staff)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@SuppressWarnings("serial")
@Data
@ApiModel("员工表实体类")
public class Staff extends Model<Staff> {
    // 员工id
    @ApiModelProperty("员工id")
    private Integer id;
    // 工号
    @ApiModelProperty("工号")
    private String workNo;
    // 密码
    @ApiModelProperty("密码")
    private String password;
    // 头像
    @ApiModelProperty("头像")
    private String avatar;
    // 姓名
    @ApiModelProperty("姓名")
    private String name;
    // 手机号
    @ApiModelProperty("手机号")
    private String phone;
    // 部门
    @ApiModelProperty("部门")
    private Integer deptId;
    // 标签
    @ApiModelProperty("标签")
    private Integer labelId;
    // 状态
    @ApiModelProperty("状态")
    private Integer status;
    // 领导
    @ApiModelProperty("领导")
    private Integer leaderId;
    // 基本工资
    @ApiModelProperty("基本工资")
    private Double salaryBase;
    // 入职时间
    @ApiModelProperty("入职时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @TableField(exist = false)
    private String deptName;
    @TableField(exist = false)
    private String labelName;
    @TableField(exist = false)
    private String statusName;
    @TableField(exist = false)
    private String leaderName;

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

