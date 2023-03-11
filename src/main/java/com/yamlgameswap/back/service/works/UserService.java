package com.yamlgameswap.back.service.works;

import com.yamlgameswap.back.entity.except.DefinitionException;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.entity.user.danmu.DanmuEntity;
import com.yamlgameswap.back.entity.user.danmu.DanmuInfo;
import com.yamlgameswap.back.entity.user.user.UserEntity;
import com.yamlgameswap.back.entity.user.user.UserInfo;
import com.yamlgameswap.back.enums.MessageEnum;
import com.yamlgameswap.back.enums.StatusEnum;
import com.yamlgameswap.back.mapper.user.DanmuMapper;
import com.yamlgameswap.back.mapper.user.UserMapper;
import com.yamlgameswap.back.service.common.RedisService;
import com.yamlgameswap.back.service.works.factory.ServiceInter;
import com.yamlgameswap.back.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j(topic = "service.UserService")
public class UserService implements ServiceInter {

    @Value("${redis.languagekey}")
    private String languagekey;

    @Autowired
    private DanmuMapper danmuMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private EncryptUtil encryptUtil;

    public Result<Map<String, String>> login(UserInfo userInfo, HttpServletRequest request) {

        /**
         * 检验参数，看是否符合规范
         * password 长度不能超过 16
         * */
        String language = request.getHeader("language").toUpperCase();

        if (userInfo.getPassword() == null ||
                userInfo.getUserAddress() == null ||
                userInfo.getPassword().length() == 0 ||
                userInfo.getUserAddress().length() == 0 ||
                userInfo.getPassword().length() > 16) {

            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.user_data_error_reginster.getCode());
            throw definitionException;
        }

        /**
         * 1. 查询是否有相同的地址
         * 2. 返回结果
         * */

        try {
            UserEntity userEntity = userMapper.findUserInfoByAddress(userInfo.getUserAddress());
            String userToken;

            if (userEntity == null || userEntity.getId() == null) {
                /**
                 * 注册
                 * */
                // 生成 userToken
                String _userToken = encryptUtil.getUserToken(userInfo.getUserAddress() + userInfo.getPassword());
                userInfo.setUserToken(encryptUtil.getUserToken(userInfo.getUserAddress() + userInfo.getPassword()));
                int success = userMapper.insertUser(userInfo);
                if (success > 0) {
                    userToken = _userToken;
                } else {
                    throw new Exception("注册失败");
                }
            } else {
                /**
                 * 检查密码是否一致
                 * */
                if (userEntity.getPassword().equals(userInfo.getPassword())) {
                    userToken = userEntity.getUserToken();
                } else {
                    throw new Exception("密码错误");
                }
            }

            // 成功登录后，向 redis 注册过期时间
            // 24 小时，一个 IP 只允许注册一次
            String uri = request.getRequestURI();
            String IP = request.getRemoteHost();
            String key = IP + "_" + uri;
            redisService.setStringTime(key, "%", 10, TimeUnit.SECONDS);

            // 成功 返回前端
            Result<Map<String, String>> result = new Result<>();
            result.setCode(MessageEnum.login_success.getResponseCode());
            result.setMessage(StatusEnum.SUCCESS.getStatus());
            result.setMessage(redisService.getResponseMsg(languagekey + language, MessageEnum.login_success.getValue()));
            Map<String, String> responseData = new HashMap<>();
            responseData.put("user_token", userToken);
            result.setData(responseData);
            result.setStatus(StatusEnum.SUCCESS.getStatus());
            return result;

        } catch (Exception e) {
            if (e.getMessage().equals("密码错误") || e.getMessage().equals("注册失败")) {
                ;
            } else {
                log.error("error: ", e);
            }
            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.user_login_fail.getCode());
            throw definitionException;
        }

    }

    public Result<Map<String, String>> addLike(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }

    public Result<Map<String, String>> deleteLike(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }

    public Result<Map<String, String>> addCollect(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }

    public Result<Map<String, String>> deleteCollect(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }

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

    public Result<Map<String, String>> addComment(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }

    public Result<Map<String, String>> info(DanmuInfo danmuInfo, HttpServletRequest request) {
        Result<Map<String, String>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setStatus(StatusEnum.SUCCESS.getStatus());
        return result;
    }
}
