package com.yamlgameswap.back.util;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

    @Value("${salt}")
    public String salt;

    public String getUserToken(String address) {
        return DigestUtil.md5Hex(address + this.salt);
    }
}
