package com.transactionservice.service;

import com.core.im.modal.product.Product;
import com.core.im.modal.user.AppUser;
import com.cos.core.dao.product.IProductDao;
import com.cos.core.dao.user.IAppUserDao;
import com.core.im.dto.AppUserProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AppUserProductService implements IAppUserProductService {
    private final IAppUserDao<AppUser> appUserDao;
    private final IProductDao<Product> productDao;

    @Autowired
    public AppUserProductService(IAppUserDao<AppUser> appUserDao, IProductDao<Product> productDao) {
        this.appUserDao = appUserDao;
        this.productDao = productDao;
    }

    public AppUserProductDto getCommonAppUsersProducts() {
        List<AppUser> appUserList = appUserDao.getAllUsers();
        List<Product> productList = productDao.getAllProducts();
        AppUserProductDto appUserProductDto = new AppUserProductDto();
        appUserProductDto.setAppUserList(appUserList);
        appUserProductDto.setProductList(productList);
        return appUserProductDto;
    }

    public Mono<AppUserProductDto> getCommonAppUsersProductsReactive() {
        Mono<List<AppUser>> appUserMono = Mono.just(appUserDao.getAllUsers());
        Mono<List<Product>> productMono = Mono.just(productDao.getAllProducts());
        return appUserMono
                .zipWith(productMono)
                .map(tuple -> {
                    AppUserProductDto appUserProductDto = new AppUserProductDto();
                    appUserProductDto.setAppUserList(tuple.getT1());
                    appUserProductDto.setProductList(tuple.getT2());
                    return appUserProductDto;
                });
    }
}
