/**
 * All rights Reserved, Designed By Robin
 * Copyright: Copyright(C) 2016-2020
 * Company    Robin  Co., Ltd.
 * <p>
 * Date:2019-07-11
 */
package com.robinye.mbp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robinye.mbp.exceptions.VideoErrorCode;
import com.robinye.mbp.vo.MbpVideoVO;
import lombok.extern.slf4j.Slf4j;
import com.robinye.mbp.MbpVideoApplication;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * video controller测试用例
 *
 * @author yetianbing
 * @version 1.0.0
 * @since jdk8
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MbpVideoApplication.class)
public class MbpVideoControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * @see MbpVideoController#getVideo(Long)
     * @throws Exception
     */
    @Test
    public void testGetVideo() throws Exception {
        long id = 1;
        RequestBuilder request = MockMvcRequestBuilders.get("/video/get")
                .param("id", String.valueOf(id))
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();


        log.info("Result status：{}.", status);
        Assert.assertEquals(200, status);

        log.info("Result content:{}.", content);

        if (StringUtils.isEmpty(content)) {
            return;
        }

        MbpVideoVO mbpVideoVO = jacksonObjectMapper.readValue(content, MbpVideoVO.class);
        log.info("MbpVideoVO: {}.", mbpVideoVO);
    }

    /**
     * @see MbpVideoController#listVideos(String)
     * @throws Exception
     */
    @Test
    public void testListVideos() throws Exception {
        String name = "avatar";
        RequestBuilder request = MockMvcRequestBuilders.get("/video/list")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();


        log.info("Result status：{}.", status);
        log.info("Result content:{}.", content);

        List<MbpVideoVO> mbpVideoVOList = jacksonObjectMapper.readValue(content, new TypeReference<List<MbpVideoVO>>(){});

        Assert.assertEquals(200, status);
        log.info("ConVideo: {}.", mbpVideoVOList);
    }

    /**
     * @see MbpVideoController#listVideos(String)
     * @throws Exception 错误处理
     */
    @Test
    public void testListVideos_InvalidName() throws Exception {
        String name = "";
        RequestBuilder request = MockMvcRequestBuilders.get("/video/list")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MvcResult mvcResult = mockMvc.perform(request).andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();


        log.info("Result status：{}.", status);
        log.info("Result content:{}.", content);

        VideoErrorCode error = jacksonObjectMapper.readValue(content, new TypeReference<VideoErrorCode>(){});

        Assert.assertEquals(200, status);
        Assert.assertEquals(error.getCode(), VideoErrorCode.InvalidVideo_NameIncorrect.getCode());
        log.info("ConVideo: {}.", error);
    }

}
