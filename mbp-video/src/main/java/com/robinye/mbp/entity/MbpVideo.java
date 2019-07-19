package com.robinye.mbp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author mybatis generator
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mbp_video")
public class MbpVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableField
    private String name;

    @TableField
    private String description;

    @TableField
    private Integer deleted;
}
