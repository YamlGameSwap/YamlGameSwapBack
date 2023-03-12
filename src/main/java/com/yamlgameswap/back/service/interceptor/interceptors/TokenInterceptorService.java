package com.yamlgameswap.back.service.interceptor.interceptors;

import com.alibaba.fastjson2.JSON;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.user.user.UserEntity;
import com.yamlgameswap.back.enums.MessageEnum;
import com.yamlgameswap.back.mapper.user.UserMapper;
import com.yamlgameswap.back.service.common.RedisService;
import com.yamlgameswap.back.service.common.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 1. 检测有没有 userToken
 * 2. 检测 usetToken 是否在数据库中
 */
@Component
public class TokenInterceptorService extends HandlerInterceptorAdapter {

    @Value("${redis.languagekey}")
    private String languagekey;

    @Autowired
    private ResultService resultService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userToken = request.getHeader("userToken");
        String language = request.getHeader("language").toUpperCase();

        if (userToken == null || userToken.length() == 0) {
            getLanguageInvalidResult(language, MessageEnum.token_is_null, response);
            return false;
        }

        UserEntity userEntity = userMapper.findUserInfoByAddress(userToken);
        if (userEntity == null) {
            getLanguageInvalidResult(language, MessageEnum.token_is_error, response);
            return false;
        } else {
            return true;
        }
    }

    public void getLanguageInvalidResult(String language, MessageEnum errorTag, HttpServletResponse response) throws IOException {
        Result result = resultService.getFail();
        result.setMessage(redisService.getResponseMsg(languagekey + language, errorTag.getValue()));
        result.setCode(errorTag.getResponseCode());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(result));
    }
}
