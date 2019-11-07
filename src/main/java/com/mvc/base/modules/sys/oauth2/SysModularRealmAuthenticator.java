package com.mvc.base.modules.sys.oauth2;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SysModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        assertRealmsConfigured() ;
        Collection<Realm> realms = getRealms() ;
        List<Realm> typeRealms = new ArrayList<>() ;
        OAuth2Token tokenEntity = (OAuth2Token) authenticationToken;
        for (Realm realm : realms) {
            if (realm.getName().contains(tokenEntity.getTokenType())) {
                typeRealms.add(realm);
            }
        }
        if (typeRealms.size() == 1){
            return doSingleRealmAuthentication(typeRealms.get(0), tokenEntity) ;
        }else{
            return doMultiRealmAuthentication(typeRealms, tokenEntity) ;
        }
    }

}
