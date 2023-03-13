package com.yamlgameswap.back.service.works;

import com.yamlgameswap.back.entity.except.DefinitionException;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.space.space.SpaceEntity;
import com.yamlgameswap.back.entity.space.space.SpaceResult;
import com.yamlgameswap.back.enums.MessageEnum;
import com.yamlgameswap.back.enums.StatusEnum;
import com.yamlgameswap.back.mapper.space.FollowMapper;
import com.yamlgameswap.back.mapper.space.SpaceMapper;
import com.yamlgameswap.back.service.works.factory.ServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j(topic = "service.SpaceService")
public class SpaceService implements ServiceInter {
    @Value("${page.sapceLimit}")
    private int spacePageLimit;

    @Autowired
    private SpaceMapper spaceMapper;
    @Autowired
    private FollowMapper followMapper;

    public Result<Map<Integer, SpaceResult>> getSpace(int page, HttpServletRequest request) {
        /**
         * 获取 space 相关信息
         * 1. 先获得全部数量
         * 2. 再获取具体的参数
         * */
        String language = request.getHeader("language").toUpperCase();
        try {

            int count = spaceMapper.getCount();
            if (count < (page - 1) * spacePageLimit) {
                throw new Exception("页码不对");
            }
            List<SpaceEntity> spaceEntityList = spaceMapper.getSpaces(page - 1, spacePageLimit);
            if (spaceEntityList.size() == 0) {
                throw new Exception("信息为空");
            }
            Result<Map<Integer, SpaceResult>> result = new Result<>();
            result.setCode(200);
            result.setMessage("success");
            result.setStatus(StatusEnum.SUCCESS.getStatus());
            result.setData(this.getSpaceResultList(spaceEntityList));
            return result;
        } catch (Exception e) {
            if (e.getMessage().equals("页码不对") || e.getMessage().equals("信息为空")) {
                ;
            } else {
                log.error("error: ", e);
            }
            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.space_error.getResponseCode());
            throw definitionException;
        }

    }

    public Result<SpaceResult> getSpaceDetail(String spaceToken, HttpServletRequest request) {
        /**
         * 获取 spaceDetail 相关信息
         * */
        String language = request.getHeader("language").toUpperCase();
        try {
            SpaceEntity spaceEntity = spaceMapper.getSpaceDetail(spaceToken);
            if (spaceEntity != null) {
                SpaceResult spaceResult = getSpaceResult(spaceEntity);
                Result<SpaceResult> result = new Result<>();
                result.setCode(200);
                result.setMessage("success");
                result.setData(spaceResult);
                result.setStatus(StatusEnum.SUCCESS.getStatus());
                return result;
            } else {
                throw new Exception("获取信息失败");
            }
        } catch (Exception e) {
            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.space_detail_error.getResponseCode());
            throw definitionException;
        }

    }

    public Map<Integer, SpaceResult> getSpaceResultList(List<SpaceEntity> spaceEntityList) {
        Map<Integer, SpaceResult> spaceResultMap = new HashMap<>();
        int i = 1;
        for (SpaceEntity spaceEntity : spaceEntityList) {
            SpaceResult spaceResult = getSpaceResult(spaceEntity);
            spaceResultMap.put(i, spaceResult);
            i++;
        }
        return spaceResultMap;
    }

    public SpaceResult getSpaceResult(SpaceEntity spaceEntity) {
        int follow = followMapper.getFollowBySpaceToken(spaceEntity.getSpaceToken());
        SpaceResult spaceResult = new SpaceResult();
        spaceResult.setTitle(spaceEntity.getTitle());
        spaceResult.setIntro(spaceEntity.getIntro());
        spaceResult.setTwitter(spaceEntity.getTwitter());
        spaceResult.setTelegram(spaceEntity.getTelegram());
        spaceResult.setDiscord(spaceEntity.getDiscord());
        spaceResult.setWebsite(spaceEntity.getWebsite());
        spaceResult.setToken_name(spaceEntity.getTokenName());
        spaceResult.setToken_contract(spaceEntity.getTokenContract());
        spaceResult.setFollow(follow);
        spaceResult.setSpace_image_url(spaceEntity.getSpaceImageUrl());
        spaceResult.setSpace_banner_url(spaceEntity.getSpaceBannerUrl());
        return spaceResult;
    }
}
