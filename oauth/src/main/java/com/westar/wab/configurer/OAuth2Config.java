package com.westar.wab.configurer;

import com.westar.wab.clientdetails.MongodbClientDetailsService;
import com.westar.wab.clientdetails.MyBaseClientDetails;
import com.westar.wab.userdetails.UserPressDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/**
 * Created by ouyasukuni on 2016/10/31.
 * 客户端模式获取AccessToken URL：
 *      1、curl -X POST -H "Authorization: Basic YWNtZTphY21lc2VjcmV0" -d grant_type=client_credentials http://localhost:1111/uaa/oauth/token
 *      2、curl acme:acmesecret@localhost:1111/uaa/oauth/token -d grant_type=client_credentials
 *      3、curl -u acme:acmesecret http://localhost:1111/uaa/oauth/token -d grant_type=client_credentials
 * 用户名密码模式获取AccessToken URL: curl acme:acmesecret@localhost:1111/uaa/oauth/token -d grant_type=password -d username=user -d password=password -d scope=openid
 * 授权码模式获取AccessToken URL：curl acme:acmesecret@localhost:1111/uaa/oauth/token -d grant_type=authorization_code -d client_id=acme -d redirect_uri=http://example.com -d code=vGFx4k
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MongodbClientDetailsService mongodbClientDetailsService;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private UserPressDetailsService userPressDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
        MyBaseClientDetails clientDetails = new MyBaseClientDetails();
        clientDetails.setClientId("acme");
        clientDetails.setClientSecret("acmesecret");
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "client_credentials", "refresh_token", "password"));
        clientDetails.setScope(Arrays.asList("openid"));
        mongodbClientDetailsService.addClientDetails(clientDetails);
        */
        clients.withClientDetails(mongodbClientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(new RedisTokenStore(jedisConnectionFactory));
        endpoints.userDetailsService(userPressDetailsService);
        endpoints.setClientDetailsService(mongodbClientDetailsService);

    }

}
