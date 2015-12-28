package com.nhnent.basecamp.service;

import com.nhnent.basecamp.model.GuestBookModel;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

public interface GuestBookService {
    void deleteAllComment();

    int countComment();

    void addNewComment(GuestBookModel guestBookModel);
}
