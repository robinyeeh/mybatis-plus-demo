package com.robinye.mbp.base.common;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractBeanConvertor<Entity, VO> {

    public abstract Entity toEntity(VO vo);

    public abstract VO toVO(Entity entity);

    public final List<VO> toVOList(List<Entity> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return Collections.emptyList();
        }

        return entityList.stream().map(this::toVO).collect(Collectors.toList());
    }

    public final List<Entity> toEntityList(List<VO> voList) {
        if (CollectionUtils.isEmpty(voList)) {
            return Collections.emptyList();
        }

        return voList.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
