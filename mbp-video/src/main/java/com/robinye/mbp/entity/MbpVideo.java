package com.robinye.mbp.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class MbpVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String description;

    private Integer deleted;


}
