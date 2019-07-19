package com.robinye.mbp.convertor;

import com.robinye.mbp.base.common.AbstractBeanConvertor;
import com.robinye.mbp.entity.MbpVideo;
import com.robinye.mbp.vo.MbpVideoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component public class MbpVideoConvertor extends AbstractBeanConvertor<MbpVideo, MbpVideoVO> {
    @Override public MbpVideo toEntity(MbpVideoVO mbpVideoVO) {
        if (mbpVideoVO == null) {
            return null;
        }

        MbpVideo mbpVideo = new MbpVideo();
        BeanUtils.copyProperties(mbpVideoVO, mbpVideo);

        return mbpVideo;
    }

    @Override public MbpVideoVO toVO(MbpVideo mbpVideo) {
        if (mbpVideo == null) {
            return null;
        }

        MbpVideoVO mbpVideoVO = new MbpVideoVO();
        BeanUtils.copyProperties(mbpVideo, mbpVideoVO);

        return mbpVideoVO;
    }
}
