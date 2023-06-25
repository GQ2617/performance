package com.zgq.performance.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色权限关系表(RolePermission)表实体类
 *
 * @author makejava
 * @since 2023-06-07 20:31:02
 */
@SuppressWarnings("serial")
@Data
@ApiModel("角色权限关系表实体类")
public class RolePermission extends Model<RolePermission> {
    // id
    @ApiModelProperty("id")
    private Integer id;
    // 角色id
    @ApiModelProperty("角色id")
    private Integer labelid;
    // 权限id
    @ApiModelProperty("权限id")
    private Integer permissionId;

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

