package com.huangsm.cloud.cloudeurekaserver.config;

import com.huangsm.cloud.cloudeurekaserver.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 授权服务器
 * authorization_code：授权码类型。
 * implicit：隐式授权类型。
 * password：资源所有者（即用户）密码类型。
 * client_credentials：客户端凭据（客户端ID以及Key）类型。
 * refresh_token：通过以上授权获得的刷新令牌来获取新的令牌。
 * @author huangsm
 * @date 二〇一九年一月五日 12:06:46
 * @version V1.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private JwtTokenStore tokenStore;

    /**
     *
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，
     * 你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 配置客户端详细信息
         *clientId：（必填）客户端ID。
         *secret:(可信客户端需要）客户机密码（如果有）。
         *scope：客户受限的范围。如果范围未定义或为空（默认值），客户端不受范围限制。
         *authorizedGrantTypes：授予客户端使用授权的类型。默认值为空。
         *authorities授予客户的授权机构（普通的Spring Security权威机构）。
         */
        clients.inMemory()
                .withClient("client_1")
                .secret(bCryptPasswordEncoder.encode("123"))
                .scopes("app")
                //资源所有者（即用户）密码类型，通过以上授权获得的刷新令牌来获取新的令牌。
                .authorizedGrantTypes("password","authorization_code","refresh_token");
    }

    /**
     *  用来配置令牌端点(Token Endpoint)的安全约束.
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //允许表单
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");

    }

    /**
     *  用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        /**
         * AuthorizationEndpoint可以通过以下方式配置支持的授权类型AuthorizationServerEndpointsConfigurer。默认情况下，所有授权类型均受支持，除了密码（有关如何切换它的详细信息，请参见下文）。以下属性会影响授权类型：
         *
         * authenticationManager：通过注入密码授权被打开AuthenticationManager。
         * userDetailsService：如果您注入UserDetailsService或者全局配置（例如a GlobalAuthenticationManagerConfigurer），则刷新令牌授权将包含对用户详细信息的检查，以确保该帐户仍然活动
         * authorizationCodeServices：定义AuthorizationCodeServices授权代码授权的授权代码服务（实例）。
         * implicitGrantService：在批准期间管理状态。
         * tokenGranter：（TokenGranter完全控制授予和忽略上述其他属性）
         *
         * 配置端点URL
         * 该AuthorizationServerEndpointsConfigurer有一个pathMapping()方法。它有两个参数：
         *
         * 端点的默认（框架实现）URL路径
         * 需要的自定义路径（以“/”开头）
         * 由框架提供的URL路径/oauth/authorize（授权端点）/oauth/token（令牌端点）
         * /oauth/confirm_access（用户发布批准此处）/oauth/error（用于在授权服务器中呈现错误）
         * /oauth/check_token（由资源服务器用于解码访问令牌） ，并且/oauth/token_key（如果使用JWT令牌，则公开用于令牌验证的公钥）。
         */
        endpoints
                .authenticationManager(authenticationManager)
                //若无，refresh_token会有UserDetailsService is required错误
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
        //扩展token返回结果
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            //jwt
            endpoints.tokenEnhancer(tokenEnhancerChain)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 将token存储到redis中
     * @return
     */
//    @Bean
//    public RedisTokenStore tokenStore(){
//        return new RedisTokenStore(redisConnectionFactory);
//    }
}
