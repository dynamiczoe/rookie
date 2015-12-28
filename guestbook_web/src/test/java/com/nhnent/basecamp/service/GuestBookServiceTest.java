package com.nhnent.basecamp.service;

import com.nhnent.basecamp.model.GuestBookModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class GuestBookServiceTest {

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
    public void addCommentTest() throws Exception {

        int beforeCount = guestBookService.countComment();

        mockMvc.perform(post("/comment-add")
                    .param("email","nhnent@naver.com")
                    .param("pw","any")
                    .param("content","Junit 픽스쳐 코멘트"))
                .andExpect(status().isOk());

        int afterCount = guestBookService.countComment();

        Assert.assertThat(afterCount-beforeCount,is(1));
    }

    @Test
    public void deleteAllCommentTest() throws Exception {

        int beforeCount = guestBookService.countComment();

        mockMvc.perform(post("/comment-add")
                .param("email","nhnent@naver.com")
                .param("pw","any")
                .param("content","Junit 픽스쳐 코멘트"))
                .andExpect(status().isOk());

        int afterCount = guestBookService.countComment();

        Assert.assertThat(afterCount-beforeCount,is(1));

        mockMvc.perform(post("/comment-deleteAll"))
                .andExpect(status().isOk());

        guestBookService.countComment();

        Assert.assertThat(guestBookService.countComment(),is(0));
    }
}

