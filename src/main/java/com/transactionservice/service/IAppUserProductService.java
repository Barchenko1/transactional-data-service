package com.transactionservice.service;

import com.core.im.dto.AppUserProductDto;
import reactor.core.publisher.Mono;

public interface IAppUserProductService {
    AppUserProductDto getCommonAppUsersProducts();
    Mono<AppUserProductDto> getCommonAppUsersProductsReactive();
}
