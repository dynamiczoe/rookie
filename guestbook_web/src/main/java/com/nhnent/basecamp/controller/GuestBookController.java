package com.nhnent.basecamp.controller;

import com.nhnent.basecamp.model.GuestBookModel;
import com.nhnent.basecamp.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */


@Controller
@RequestMapping
public class GuestBookController {

    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping(value = "/comment-add",method = RequestMethod.POST)
    public String addComment(GuestBookModel guestBookModel) throws Exception {
        guestBookService.addNewComment(guestBookModel);
        return "redirect:index";
    }

    @RequestMapping(value = "/index")
    public List<GuestBookModel> getCommentList() {
        List<GuestBookModel> guestBookModelList = guestBookService.getAllCommentList();
        return guestBookModelList;
    }

    @RequestMapping(value = "/comment-modify")
    public @ResponseBody String modifyComment(GuestBookModel guestBookModel) {

        if(guestBookService.confirmAuth(guestBookModel.getId(),guestBookModel.getPw())){
            guestBookService.modifyComment(guestBookModel);
            return "success";
        }else{
            return "fail";
        }
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView dataAccessExceptionHandler(Exception e) {
        return new ModelAndView("error-page").addObject("errorlog", e.getMessage());
    }

}
