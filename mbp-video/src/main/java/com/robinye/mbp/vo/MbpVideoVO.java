package com.robinye.mbp.vo;

import com.robinye.mbp.base.common.AbstractVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MbpVideoVO extends AbstractVO {
    private Long id;

    private String name;

    private String description;

    private Integer deleted;
}
