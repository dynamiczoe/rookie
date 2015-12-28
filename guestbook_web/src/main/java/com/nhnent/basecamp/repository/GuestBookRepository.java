package com.nhnent.basecamp.repository;

import com.nhnent.basecamp.model.GuestBookModel;
import org.springframework.stereotype.Repository;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

@Repository
public interface GuestBookRepository {
    void deleteAll();

    int count();

    void insertNewComment(GuestBookModel guestBookModel);
}
