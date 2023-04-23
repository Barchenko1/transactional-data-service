package com.transactionservice.rest.v2;

import com.core.im.dto.AppUserProductDto;
import com.transactionservice.service.IAppUserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v2")
public class ReactiveDbCall {

    private final IAppUserProductService appUserProductService;

    @Autowired
    public ReactiveDbCall(IAppUserProductService appUserProductService) {
        this.appUserProductService = appUserProductService;
    }

    @RequestMapping(value = "/appUsersAndProducts", method = RequestMethod.GET)
    public ResponseEntity<Mono<AppUserProductDto>> getUser() {
        long startTime = System.currentTimeMillis();
        Mono<AppUserProductDto> appUserProductDto = appUserProductService.getCommonAppUsersProductsReactive();
        System.out.println(System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(appUserProductDto, HttpStatus.OK);
    }
}
