package com.yamlgameswap.back.mapper.user;

import com.yamlgameswap.back.entity.user.danmu.DanmuEntity;
import com.yamlgameswap.back.entity.user.user.UserEntity;
import com.yamlgameswap.back.entity.user.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    UserEntity findUserInfoByAddress(@Param("userAddress") String userAddress);

    UserEntity findUserInfoByToken(@Param("userToken") String userToken);

    int insertUser(UserInfo userInfo);

    String getAddressByUserToken(@Param("userToken") String userToken);
}
