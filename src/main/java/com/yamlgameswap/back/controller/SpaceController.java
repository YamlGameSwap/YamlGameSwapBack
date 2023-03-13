package com.yamlgameswap.back.controller;

import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.space.space.SpaceResult;
import com.yamlgameswap.back.enums.WorkEnum;
import com.yamlgameswap.back.service.works.SpaceService;
import com.yamlgameswap.back.service.works.factory.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j(topic = "controller.Space")
@RestController
@RequestMapping(value = "/api/space")
public class SpaceController {
    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<Map<Integer, SpaceResult>> get(HttpServletRequest request, @RequestParam("page") Integer page) {
        return ((SpaceService) serviceFactory.getService(WorkEnum.SPACESERVICE.getService())).getSpace(page, request);
    }

    @RequestMapping(value = "/getSpaceDetail", method = RequestMethod.GET)
    public Result<SpaceResult> getSpaceDetail(HttpServletRequest request, @RequestParam("space_token") String spaceToken) {
        return ((SpaceService) serviceFactory.getService(WorkEnum.SPACESERVICE.getService())).getSpaceDetail(spaceToken, request);
    }
}
