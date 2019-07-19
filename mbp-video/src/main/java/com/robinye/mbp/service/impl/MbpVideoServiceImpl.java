package com.robinye.mbp.service.impl;

import com.robinye.mbp.convertor.MbpVideoConvertor;
import com.robinye.mbp.entity.MbpVideo;
import com.robinye.mbp.exceptions.VideoErrorCode;
import com.robinye.mbp.exceptions.VideoRuntimeException;
import com.robinye.mbp.mapper.MbpVideoMapper;
import com.robinye.mbp.service.IMbpVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robinye.mbp.vo.MbpVideoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mybatis generator
 * @since 2019-07-19
 */
@Service
@Slf4j
public class MbpVideoServiceImpl extends ServiceImpl<MbpVideoMapper, MbpVideo> implements IMbpVideoService {
    @Autowired
    private MbpVideoConvertor mbpVideoConvertor;

    @Override
    public List<MbpVideoVO> listVideos(String name) {
        if (StringUtils.isEmpty(name)) {
            log.error("Video name is invalid, name : {}. ", name);
            throw new VideoRuntimeException(VideoErrorCode.InvalidVideo_NameIncorrect);
        }

        List<MbpVideo> mbpVideoList = baseMapper.listVideos(name);
        return mbpVideoConvertor.toVOList(mbpVideoList);
    }

    @Override
    public MbpVideoVO getVideo(Long id) {
        MbpVideo mbpVideo = getById(id);
        return mbpVideoConvertor.toVO(mbpVideo);
    }
}
