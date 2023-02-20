package com.yamlgameswap.back.service.works;

import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.user.DanmuEntity;
import com.yamlgameswap.back.entity.user.DanmuInfo;
import com.yamlgameswap.back.enums.StatusEnum;
import com.yamlgameswap.back.mapper.DanmuMapper;
import com.yamlgameswap.back.service.works.factory.ServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service
@Slf4j(topic = "service.UserService")
public class UserService implements ServiceInter {

    @Autowired
    private DanmuMapper danmuMapper;

    /**
     * 检测数据库中该章节的弹幕是否超过 3 条
     */
    public Result<Map<String, String>> addDanmu(DanmuInfo danmuInfo, HttpServletRequest request) {
        String userToken = request.getHeader("userToken");
        List<DanmuEntity> danmuEntityList = danmuMapper.findSectionUserDanmus(danmuInfo.getSectionToken(), userToken);

        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }
}
