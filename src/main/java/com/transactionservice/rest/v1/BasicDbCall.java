package com.transactionservice.rest.v1;

import com.core.im.dto.AppUserProductDto;
import com.transactionservice.service.IAppUserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class BasicDbCall {

    private final IAppUserProductService appUserProductService;

    @Autowired
    public BasicDbCall(IAppUserProductService appUserProductService) {
        this.appUserProductService = appUserProductService;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("hello tds", HttpStatus.OK);
    }

    @RequestMapping(value = "/appUsersAndProducts", method = RequestMethod.GET)
    public ResponseEntity<AppUserProductDto> getAppUsersAndProducts() {
        long startTime = System.currentTimeMillis();
        AppUserProductDto appUserProductDto = appUserProductService.getCommonAppUsersProducts();
        System.out.println(System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(appUserProductDto, HttpStatus.OK);
    }

}
