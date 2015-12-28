package com.nhnent.basecamp.controller;

import com.nhnent.basecamp.model.GuestBookModel;
import com.nhnent.basecamp.service.GuestBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */


@Controller
@RequestMapping
public class GuestBookController {

    @Autowired
    private GuestBookService guestBookService;

    @RequestMapping(value = "/comment-add",method = RequestMethod.POST)
    public void addComment(GuestBookModel guestBookModel) {
        guestBookService.addNewComment(guestBookModel);
    }

    @RequestMapping(value = "/comment-count")
    public @ResponseBody
    int countComment() {
        return guestBookService.countComment();
    }
}
