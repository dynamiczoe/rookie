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

import java.util.List;

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

//    @Test
//    public void deleteAllCommentTest() throws Exception {
//
//        int beforeCount = guestBookService.countComment();
//
//        mockMvc.perform(post("/comment-add")
//                .param("email","nhnent@naver.com")
//                .param("pw","any")
//                .param("content","Junit 픽스쳐 코멘트"))
//                .andExpect(status().isOk());
//
//        int afterCount = guestBookService.countComment();
//
//        Assert.assertThat(afterCount-beforeCount,is(1));
//
//        mockMvc.perform(post("/comment-deleteAll"))
//                .andExpect(status().isOk());
//
//        guestBookService.countComment();
//
//        Assert.assertThat(guestBookService.countComment(),is(0));
//    }

    @Test
    public void getAllCommentListTest() throws Exception {

        int commentListSize = guestBookService.countComment();

        List<GuestBookModel> guestBookModelList = guestBookService.getAllCommentList();

        Assert.assertThat(guestBookModelList.size(),is(commentListSize));
    }

    @Test
    public void confirmAuthTest() throws Exception {

        mockMvc.perform(post("/comment-add")
                .param("email","nhnent@naver.com")
                .param("pw","any")
                .param("content","Junit 픽스쳐 코멘트"))
                .andExpect(status().isOk());

        List<GuestBookModel> guestBookModelList = guestBookService.getAllCommentList();
        GuestBookModel guestBookModel = guestBookModelList.get(guestBookModelList.size()-1);
        int commentId = guestBookModel.getId();
        String password = guestBookModel.getPw();
        String incorrectPW = "jejuman";

        Assert.assertThat(guestBookService.confirmAuth(commentId, password),is(true));
        Assert.assertThat(guestBookService.confirmAuth(commentId, incorrectPW), is(false));
    }

    @Test
    public void getCommentByIdTest() throws Exception {

        mockMvc.perform(post("/comment-add")
                .param("email","nhnent@naver.com")
                .param("pw","any")
                .param("content","Junit 픽스쳐 코멘트"))
                .andExpect(status().isOk());

        List<GuestBookModel> guestBookModelList = guestBookService.getAllCommentList();
        GuestBookModel guestBookModel1 = guestBookModelList.get(guestBookModelList.size() - 1);
        int commentId = guestBookModel1.getId();

        GuestBookModel guestBookModel2 = guestBookService.getCommentById(commentId);

        Assert.assertThat(guestBookModel1.getContent(),is(guestBookModel2.getContent()));
        Assert.assertThat(guestBookModel1.getId(),is(guestBookModel2.getId()));
        Assert.assertThat(guestBookModel1.getEmail(),is(guestBookModel2.getEmail()));
        Assert.assertThat(guestBookModel1.getCreateDate(),is(guestBookModel2.getCreateDate()));
    }

    @Test
    public void modifyCommentTest() throws Exception {

        mockMvc.perform(post("/comment-add")
                .param("email","pm2@naver.com")
                .param("pw","yayaya")
                .param("content","can you feel my heartbeat ? ha~bet!"))
                .andExpect(status().isOk());

        List<GuestBookModel> guestBookModelList = guestBookService.getAllCommentList();
        GuestBookModel originGuestBookModel = guestBookModelList.get(guestBookModelList.size() - 1);
        originGuestBookModel.setContent("sorry,, I can't.");
        guestBookService.modifyComment(originGuestBookModel);
        GuestBookModel modifiedGuestBookModel = guestBookService.getCommentById(originGuestBookModel.getId());

        Assert.assertThat(originGuestBookModel.getId(),is(modifiedGuestBookModel.getId()));
        Assert.assertThat(originGuestBookModel.getContent(), is(modifiedGuestBookModel.getContent()));
    }
}

