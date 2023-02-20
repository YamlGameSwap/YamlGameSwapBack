package com.yamlgameswap.back.service.common;

import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "service.ResultService")
@Service
public class ResultService {

    public Result getFail(){
        Result result = new Result();
        result.setStatus(StatusEnum.FAIL.getStatus());
        return result;
    }
}
