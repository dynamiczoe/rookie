package com.nhnent.basecamp.service;

import com.nhnent.basecamp.model.GuestBookModel;

import java.util.List;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

public interface GuestBookService {
    void deleteAllComment();

    int countComment();

    void addNewComment(GuestBookModel guestBookModel);

    List<GuestBookModel> getAllCommentList();

    boolean confirmAuth(int commentId, String password);

    GuestBookModel getCommentById(int commentId);

    void modifyComment(GuestBookModel guestBookModel);
}
