package com.nhnent.basecamp.service;

import com.nhnent.basecamp.model.GuestBookModel;
import com.nhnent.basecamp.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dynamiczoe on 15. 12. 28..
 */

@Service
public class GuestBookServiceImpl implements GuestBookService {
    @Autowired
    private GuestBookRepository guestBookRepository;

    @Override
    public void deleteAllComment() {
        guestBookRepository.deleteAll();
    }

    @Override
    public int countComment() {
        return guestBookRepository.count();
    }

    @Override
    public void addNewComment(GuestBookModel guestBookModel) {

        guestBookRepository.insertNewComment(guestBookModel);
    }

    @Override
    public List<GuestBookModel> getAllCommentList() {
        return guestBookRepository.getAllCommentList();

    }

    @Override
    public boolean confirmAuth(int commentId, String password) {
        boolean auth = false;
        GuestBookModel guestBookModel = new GuestBookModel();
        guestBookModel.setId(commentId);
        guestBookModel.setPw(password);

        if(guestBookRepository.comparePasswordToId(guestBookModel) > 0){
            auth = true;
        }
        return auth;
    }

    @Override
    public GuestBookModel getCommentById(int commentId) {

        return guestBookRepository.findCommentById(commentId);
    }
}
