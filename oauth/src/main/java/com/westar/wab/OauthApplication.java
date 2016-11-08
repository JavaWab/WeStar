package com.westar.wab;

import com.westar.wab.clientdetails.MongodbClientDetailsService;
import com.westar.wab.common.utils.BlowfishEncryptor;
import com.westar.wab.userdetails.UserPressDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class OauthApplication {
	@Value("${westar.flowfish.key}")
	private String blowfishKey;

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
	@Bean(name = "mongodbClientDetailsService")
	MongodbClientDetailsService getMongodbClientDetailsService(){
		return new MongodbClientDetailsService();
	}

	@Bean(name = "userPressDetailsService")
	UserPressDetailsService getUserPressDetailsService(){
		return new UserPressDetailsService();
	}

	@Bean(name = "blowfishEncryptor")
	BlowfishEncryptor getBlowfishEncryptor(){
		return new BlowfishEncryptor(blowfishKey);
	}
}