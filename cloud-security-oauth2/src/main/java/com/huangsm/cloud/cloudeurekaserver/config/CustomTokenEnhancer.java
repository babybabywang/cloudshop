package com.huangsm.cloud.cloudeurekaserver.config;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 令牌中的自定义声明
 * @author huang
 * @PACKAGE_NAME com.huangsm.Oauth2SSOSecurity.config
 * @PROJECT_NAME Oauth2-SSO-Security
 * @date 2019/1/4
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>(10);
        info.put("blog", "https://github.com/babybabywang");
        info.put("author","huangsm");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
        }
}
