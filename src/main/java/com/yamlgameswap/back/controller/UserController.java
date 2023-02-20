package com.yamlgameswap.back.controller;

import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.user.DanmuInfo;
import com.yamlgameswap.back.enums.WorkEnum;
import com.yamlgameswap.back.service.works.UserService;
import com.yamlgameswap.back.service.works.factory.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j(topic = "controller.User")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private ServiceFactory serviceFactory;

    // 注册
    @RequestMapping(value = "/danmu", method = RequestMethod.POST)
    public Result<Map<String, String>> reginster(HttpServletRequest request, @RequestBody DanmuInfo danmuInfo) {
        return ((UserService) serviceFactory.getService(WorkEnum.USERSERVICE.getService())).addDanmu(danmuInfo, request);
    }

}
