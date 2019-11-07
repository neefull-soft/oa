package com.mvc.base.modules.sys.oauth2;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * token
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-20 13:22
 */
public class OAuth2Token implements AuthenticationToken {

    private String tokenType ;

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    public OAuth2Token(String token, String tokenType){
        this.token = token;
        this.tokenType = tokenType ;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
