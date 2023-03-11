package com.yamlgameswap.back.service.interceptor.interceptors;


import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证拦截器
 */
@Component
public class AuthInterceptorService extends HandlerInterceptorAdapter {

    @Value("${privateKey}")
    private String privateKey;

    @Value("${publicKey}")
    private String publicKey;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String encryptByPublic = request.getHeader("auth");
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), privateKey, publicKey);
        String decryptByPrivate = rsa.decryptStr(encryptByPublic, KeyType.PrivateKey);
        return true;
    }
}
