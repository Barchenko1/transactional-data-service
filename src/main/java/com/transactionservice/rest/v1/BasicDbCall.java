package com.transactionservice.rest.v1;

import com.core.im.modal.user.AppUser;
import com.cos.core.dao.user.IAppUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class BasicDbCall {

    private final IAppUserDao<AppUser> appUserDao;

    @Autowired
    public BasicDbCall(IAppUserDao<AppUser> appUserDao) {
        this.appUserDao = appUserDao;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("hello tds", HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<AppUser>> getUser() {
        List<AppUser> appUserList = appUserDao.getAllUsers();
        System.out.println(appUserList);
        return new ResponseEntity<>(appUserList, HttpStatus.OK);
    }

}
