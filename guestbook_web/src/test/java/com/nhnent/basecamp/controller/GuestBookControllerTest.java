package com.nhnent.basecamp.controller;

import com.nhnent.basecamp.service.GuestBookService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class GuestBookControllerTest extends TestCase {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    private GuestBookService guestBookService;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(this.wac).build();
    }



    @Test
    public void modifyControllerTest() throws Exception {
        MvcResult result = mockMvc.perform(post("/comment-modify")
                .param("id", "27")
                .param("email", "nhnent@naver.com")
                .param("pw", "incorrectPw")
                .param("content", "코멘트 수정했음!"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        Assert.assertThat(content,is("fail"));

        result = mockMvc.perform(post("/comment-modify")
                .param("id","27")
                .param("email","nhnent@naver.com")
                .param("pw","any")
                .param("content","코멘트 수정했음!"))
                .andReturn();

        content = result.getResponse().getContentAsString();
        Assert.assertThat(content,is("success"));

    }

    @Test
    public void getListControllerTest() throws Exception {
        mockMvc.perform(post("/index"))
                .andDo(print());
    }

}