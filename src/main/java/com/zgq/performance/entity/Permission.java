package com.zgq.performance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 权限表(Permission)表实体类
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@SuppressWarnings("serial")
@Data
@ApiModel("权限表实体类")
public class Permission extends Model<Permission> {
    // 权限id
    @ApiModelProperty("权限id")
    private Integer id;
    // 路径
    @ApiModelProperty("路径")
    @TableField("`key`")
    private String key;
    // 名称
    @ApiModelProperty("名称")
    private String label;
    // 标题
    @ApiModelProperty("标题")
    private String title;
    // 图标
    @ApiModelProperty("图标")
    private String icon;

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

