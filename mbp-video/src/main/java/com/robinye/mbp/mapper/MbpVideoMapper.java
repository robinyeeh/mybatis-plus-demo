package com.robinye.mbp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robinye.mbp.entity.MbpVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mybatis generator
 * @since 2019-07-19
 */
public interface MbpVideoMapper extends BaseMapper<MbpVideo> {
    List<MbpVideo> listVideos(@Param("name") String name);
}
