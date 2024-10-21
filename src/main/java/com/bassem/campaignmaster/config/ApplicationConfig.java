package com.bassem.campaignmaster.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableEncryptableProperties
public class ApplicationConfig {

    @Bean
    public MessageDigest messageDigest(){
        try {
            return MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ex){
            return null;
        }
    }
}
