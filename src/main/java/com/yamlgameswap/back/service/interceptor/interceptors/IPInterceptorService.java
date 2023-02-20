package com.yamlgameswap.back.service.interceptor.interceptors;

import com.alibaba.fastjson2.JSON;
import com.yamlgameswap.back.entity.except.DefinitionException;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.ErrorEnum;
import com.yamlgameswap.back.service.common.RedisService;
import com.yamlgameswap.back.service.common.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 防止单一 IP 操作频繁
 */

@Component
public class IPInterceptorService extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;
    @Autowired
    private ResultService resultService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String IP = request.getRemoteHost();
        String language = request.getHeader("language").toUpperCase();
        String key = IP + "_" + uri;
        String value = redisService.getString(key);
        if (value != null) {
            Result result = resultService.getFail();
            try{
                /**
                 * 有的时候 redis 中由于不知名原因没有存储这个 key 和 value，导致出错
                 * */
                result.setCode(ErrorEnum.ip_repeat_reginster.getResponseCode());
                result.setMessage(redisService.getResponseMsg("yamlgameswap_" + language, ErrorEnum.ip_repeat_reginster.getValue()));
            }catch (Exception e){
                DefinitionException definitionException = new DefinitionException();
                definitionException.setLanguage(language);
                definitionException.setErrorCode(ErrorEnum.redis_is_null.getCode());
                throw definitionException;
            }
            result.setCode(ErrorEnum.ip_repeat_reginster.getCode());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        redisService.setStringTime(key, "%", 30, TimeUnit.SECONDS);
        return true;
    }
}
