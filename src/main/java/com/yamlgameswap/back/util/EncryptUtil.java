package com.yamlgameswap.back.util;

import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

    @Value("${salt.password}")
    public String saltPassword;

    @Value("${auth.privateKey}")
    private String authPrivateKey;

    @Value("${auth.publicKey}")
    private String authPublicKey;

    public String getUserToken(String address) {
        return DigestUtil.md5Hex(address + this.saltPassword);
    }

    public String decryptByPrivate(String encryptByPublic) {
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), authPrivateKey, authPublicKey);
        return rsa.decryptStr(encryptByPublic, KeyType.PrivateKey);
    }

    public String encryptByPublic(String data) {
        RSA rsa = new RSA(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), authPrivateKey, authPublicKey);
        return rsa.encryptBase64(data, KeyType.PublicKey);
    }
}
