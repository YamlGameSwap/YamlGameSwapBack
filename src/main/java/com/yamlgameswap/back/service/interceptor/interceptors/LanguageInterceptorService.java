package com.yamlgameswap.back.service.interceptor.interceptors;

import com.alibaba.fastjson2.JSON;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.MessageEnum;
import com.yamlgameswap.back.service.common.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 判断语言格式是否正确
 */
@Component
public class LanguageInterceptorService extends HandlerInterceptorAdapter {

    @Value("${multilanguage}")
    private List<String> multiLangiage;

    @Autowired
    private ResultService resultService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String language = request.getHeader("language").toUpperCase();
            /**
             * 判断语言是否为空还是规则内的语言类型
             * */
            if (language == null || !multiLangiage.contains(language)) {
                getLanguageInvalidResult(response);
                return false;
            }
            return true;
        } catch (Exception e) {
            getLanguageInvalidResult(response);
            return false;
        }
    }

    public void getLanguageInvalidResult(HttpServletResponse response) throws IOException {
        Result result = resultService.getFail();
        result.setMessage("language is invalid");
        result.setCode(MessageEnum.language_type_error.getResponseCode());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JSON.toJSONString(result));
    }
}
