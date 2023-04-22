package com.transactionservice.rest.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class DbApi {
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("hello tds", HttpStatus.OK);
    }
}
