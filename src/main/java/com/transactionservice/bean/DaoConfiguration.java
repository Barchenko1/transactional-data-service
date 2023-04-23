package com.transactionservice.bean;

import com.core.im.modal.product.Product;
import com.core.im.modal.user.AppUser;
import com.cos.core.config.ConnectionPullHikariConfiguration;
import com.cos.core.config.IConnectionPullConfiguration;
import com.cos.core.dao.ConfigurationSessionFactory;
import com.cos.core.dao.product.IProductDao;
import com.cos.core.dao.product.impl.ProductDao;
import com.cos.core.dao.user.IAppUserDao;
import com.cos.core.dao.user.impl.AppUserDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    private final Class<?>[] classes = { AppUser.class };
    private final SessionFactory sessionFactory;

    public DaoConfiguration() {
        IConnectionPullConfiguration connectionPullConfiguration =
                new ConnectionPullHikariConfiguration();
        ConfigurationSessionFactory configurationSessionFactory =
                new ConfigurationSessionFactory(connectionPullConfiguration, classes);
        this.sessionFactory = configurationSessionFactory.getSessionFactory();
    }

    @Bean
    public IAppUserDao<AppUser> appUserDao() {
        IAppUserDao<AppUser> appUserDao = new AppUserDao<>(sessionFactory);
        appUserDao.setClazz(AppUser.class);
        return appUserDao;
    }

    @Bean
    public IProductDao<Product> productDao() {
        IProductDao<Product> appUserDao = new ProductDao<>(sessionFactory);
        appUserDao.setClazz(Product.class);
        return appUserDao;
    }
}
