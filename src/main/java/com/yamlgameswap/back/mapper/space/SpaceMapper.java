package com.yamlgameswap.back.mapper.space;

import com.yamlgameswap.back.entity.space.space.SpaceEntity;
import com.yamlgameswap.back.entity.user.user.UserEntity;
import com.yamlgameswap.back.entity.user.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SpaceMapper {
    int getCount();

    SpaceEntity getSpaceDetail(@Param("spaceToken") String spaceToken);

    List<SpaceEntity> getSpaces(@Param("offset") int offset, @Param("limit") int limit);
}
