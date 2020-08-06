package com.oauthdemo.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.oauthdemo.entity.UserEntity;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth2Authentication) {
		UserEntity userEntity = (UserEntity)auth2Authentication.getPrincipal();
		
		Map<String, Object> info  = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
		
		info.put("email", userEntity.getEmail());
		
		DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
		customAccessToken.setAdditionalInformation(info);
		
		return super.enhance(customAccessToken, auth2Authentication);
	}

}
