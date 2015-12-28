package com.nhnent.basecamp.service;

import com.nhnent.basecamp.model.GuestBookModel;
import com.nhnent.basecamp.repository.GuestBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

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
    public void addNewComment(GuestBookModel guestBookModel) throws Exception {
        if(isEmail(guestBookModel.getEmail())) {
            guestBookRepository.insertNewComment(guestBookModel);
        }else{
            throw new Exception("invalid email");
        }
    }

    public boolean isEmail(String email) {
        if (email==null) return false;
        boolean b = Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim());
        return b;
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

    @Override
    public void modifyComment(GuestBookModel guestBookModel) {
        guestBookRepository.updateComment(guestBookModel);
    }

    @Override
    public void deleteCommentById(int id) {
        guestBookRepository.deleteById(id);
    }
}
