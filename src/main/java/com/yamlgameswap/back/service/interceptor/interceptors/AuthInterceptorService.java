package com.yamlgameswap.back.service.interceptor.interceptors;


import com.alibaba.fastjson2.JSON;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.service.common.ResultService;
import com.yamlgameswap.back.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证拦截器
 * <p>
 * 加密规则是 （毫秒级时间戳 + 固定加盐字段）
 */
@Component
public class AuthInterceptorService extends HandlerInterceptorAdapter {

    @Value("${salt.auth}")
    private String saltAuth;

    @Autowired
    private ResultService resultService;
    @Autowired
    private EncryptUtil encryptUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String encryptByPublic = request.getHeader("auth");
        try {
            if (encryptByPublic == null || encryptByPublic.length() == 0) {
                throw new Exception("auth is fail");
            } else {
                // hwy5bP4EI+KOt2JMogKZrfk4wRAdtkYCDhlljV8iGjrjjbDdzpEuCphHe1a0/OTMgK23TxZPpUd4cAE3eCcW1TRhZ9p5ngPbu7c4xqdLpvB1a31Ax/1xJQ3t4UTER2QIXwxZB32Q/LvvbOWFK+hO3nP8mw/oJWv7YY9cHaBTPeU=
                String decryptByPrivate = encryptUtil.decryptByPrivate(encryptByPublic);
                long _timestamp = Long.parseLong(decryptByPrivate.substring(0, 13));
                String _saltAuth = decryptByPrivate.substring(13);
                // 检验 auth 是否有效
                long nowTimeStamp = System.currentTimeMillis();
                // 误差小于绝对值 5000 毫秒
//                if (Math.abs(nowTimeStamp - _timestamp) > 5000) {
//                    throw new Exception("auth is fail");
//                }
                if (!_saltAuth.equals(saltAuth)) {
                    throw new Exception("auth is fail");
                }
            }
        } catch (Exception e) {
            Result result = resultService.getFail();
            result.setMessage("auth is fail");
            result.setCode(3004);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        return true;
    }
}
