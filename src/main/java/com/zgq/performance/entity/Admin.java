package com.zgq.performance.entity;

import java.util.Date;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 管理员表(Admin)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:55:08
 */
@SuppressWarnings("serial")
@Data
@ApiModel("管理员表实体类")
public class Admin extends Model<Admin> {
    // 管理员id
    @ApiModelProperty("管理员id")
    private Integer id;
    // 账号
    @ApiModelProperty("账号")
    private String username;
    // 密码
    @ApiModelProperty("密码")
    private String password;
    // 备注
    @ApiModelProperty("备注")
    private String remarks;
    // 状态 0正常1禁用
    @ApiModelProperty("状态 0正常1禁用")
    private Integer status;
    // 创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;

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

