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
import java.util.Map;

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

    @Override
    public AppUser updateCommonAppUser(AppUser updateAppUser, String id) {
        appUserDao.updateEntity(updateAppUser);
        AppUser appUser = appUserDao.getUserByUserName(updateAppUser.getUsername()).get();
        return appUser;
    }

    @Override
    public AppUser updateCommonAppUserPatch(Map<String, Object> updateAppUser, String id) {
        AppUser appUser = setupAppUser(updateAppUser, id);
        appUserDao.updateEntity(appUser);
        AppUser getAppUser = appUserDao.getUserByUserName(appUser.getUsername()).get();
        return getAppUser;
    }

    private AppUser setupAppUser(Map<String, Object> updateAppUser, String id) {
        AppUser appUser = new AppUser();
        appUser.setId(Long.parseLong(id));
        appUser.setUsername((String) updateAppUser.get("username"));
        appUser.setPassword((String) updateAppUser.get("password"));
        appUser.setEmail((String) updateAppUser.get("email"));
        appUser.setRole(null);
        appUser.setUserAddress(null);
        appUser.setUserDetails(null);
        appUser.setBucket(null);

        return appUser;
    }
}
