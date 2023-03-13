package com.yamlgameswap.back.mapper.space;

import com.yamlgameswap.back.entity.space.space.SpaceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowMapper {

    Integer getFollowBySpaceToken(@Param("spaceToken") String spaceToken);
}
