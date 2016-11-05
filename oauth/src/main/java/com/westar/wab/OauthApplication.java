package com.westar.wab;

import com.westar.wab.clientdetails.MongodbClientDetailsService;
import com.westar.wab.userdetails.UserPressDetailsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableDiscoveryClient
public class OauthApplication {

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
}