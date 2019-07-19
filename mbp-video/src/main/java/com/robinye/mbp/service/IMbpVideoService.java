package com.robinye.mbp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robinye.mbp.entity.MbpVideo;
import com.robinye.mbp.vo.MbpVideoVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mybatis generator
 * @since 2019-07-19
 */
public interface IMbpVideoService extends IService<MbpVideo> {
    List<MbpVideoVO> listVideos(String name);

    MbpVideoVO getVideo(Long id);
}
