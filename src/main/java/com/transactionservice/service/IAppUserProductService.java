package com.transactionservice.service;

import com.core.im.dto.AppUserProductDto;
import com.core.im.modal.user.AppUser;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface IAppUserProductService {
    AppUserProductDto getCommonAppUsersProducts();
    Mono<AppUserProductDto> getCommonAppUsersProductsReactive();
    AppUser updateCommonAppUser(AppUser updateAppUser, String id);
    AppUser updateCommonAppUserPatch(Map<String, Object> updateAppUser, String id);
}
