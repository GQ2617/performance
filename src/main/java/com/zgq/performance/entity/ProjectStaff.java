package com.zgq.performance.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 项目-绩效关系表(ProjectStaff)表实体类
 *
 * @author makejava
 * @since 2023-06-03 11:47:26
 */
@SuppressWarnings("serial")
@Data
@ApiModel("项目-绩效关系表实体类")
public class ProjectStaff extends Model<ProjectStaff> {
    // 唯一标识
    @ApiModelProperty("唯一标识")
    private Integer id;
    // 绩效id
    @ApiModelProperty("绩效id")
    private Integer performanceId;
    // 项目id
    @ApiModelProperty("项目id")
    private Integer projectId;

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

