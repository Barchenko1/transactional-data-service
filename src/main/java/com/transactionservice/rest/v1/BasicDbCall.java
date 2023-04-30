package com.transactionservice.rest.v1;

import com.core.im.dto.AppUserProductDto;
import com.core.im.modal.user.AppUser;
import com.transactionservice.service.IAppUserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


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

    @RequestMapping(value = "/updateUserPut/{id}", method = RequestMethod.PUT)
    public ResponseEntity<AppUser> updateUserPut(
            @RequestBody AppUser updateAppUser,
            @PathVariable("id") String id
    ) {
        long startTime = System.currentTimeMillis();
        AppUser appUser = appUserProductService
                .updateCommonAppUser(updateAppUser, id);
        System.out.println(System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUserPatch/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<AppUser> updateUserPatch(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") String id
    ) {
        long startTime = System.currentTimeMillis();

        AppUser appUser = appUserProductService
                .updateCommonAppUserPatch(updates, id);
        System.out.println(System.currentTimeMillis() - startTime);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

}
