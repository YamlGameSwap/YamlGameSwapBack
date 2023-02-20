package com.yamlgameswap.back.service.common;

import com.yamlgameswap.back.entity.except.DefinitionException;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.ErrorEnum;
import com.yamlgameswap.back.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * 全局统一处理异常
 */
@Slf4j(topic = "service.ExceptionService")
@ControllerAdvice
public class ExceptionService {

    @Autowired
    private ResultService resultService;

    @Autowired
    private RedisService redisService;

    /**
     * 统一自定义异常管理
     * */
    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public Result DefineExceptionHandler(DefinitionException e) {
        String errorTag = ErrorEnum.getCodeMsgMap().get(e.getErrorCode());
        Result<Map<String, String>> result = resultService.getFail();
        result.setCode(e.getErrorCode());
        try {
            result.setMessage(redisService.getResponseMsg("yamlgameswap_" + e.getLanguage(), errorTag));
        } catch (Exception exception) {
            log.error("error:", exception);
            result.setMessage(redisService.getResponseMsg("yamlgameswap_" + e.getLanguage(), ErrorEnum.ip_repeat_reginster.getValue()));
        }

        result.setData(null);
        return result;
    }

}
