package com.transactionservice.bean;

import com.core.im.modal.user.AppUser;
import com.cos.core.config.ConnectionPullHikariConfiguration;
import com.cos.core.config.IConnectionPullConfiguration;
import com.cos.core.dao.ConfigurationSessionFactory;
import com.cos.core.dao.user.IAppUserDao;
import com.cos.core.dao.user.impl.AppUserDao;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationDao {

    private final Class<?>[] classes = { AppUser.class };
    private final SessionFactory sessionFactory;

    public ConfigurationDao() {
        IConnectionPullConfiguration connectionPullConfiguration =
                new ConnectionPullHikariConfiguration();
        ConfigurationSessionFactory configurationSessionFactory =
                new ConfigurationSessionFactory(connectionPullConfiguration, classes);
        this.sessionFactory = configurationSessionFactory.getSessionFactory();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactory;
    }

    @Bean
    public IAppUserDao<AppUser> appUserDao() {
        IAppUserDao<AppUser> appUserDao = new AppUserDao<>(sessionFactory);
        appUserDao.setClazz(AppUser.class);
        return appUserDao;
    }
}
