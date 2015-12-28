package com.nhnent.basecamp.repository;

import com.nhnent.basecamp.model.GuestBookModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

@Repository
public interface GuestBookRepository {
    void deleteAll();

    int count();

    void insertNewComment(GuestBookModel guestBookModel);

    List<GuestBookModel> getAllCommentList();

    int comparePasswordToId(GuestBookModel guestBookModel);

    GuestBookModel findCommentById(int commentId);

    void updateComment(GuestBookModel guestBookModel);
}
