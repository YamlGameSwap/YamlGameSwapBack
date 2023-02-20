package com.yamlgameswap.back.mapper;

import com.yamlgameswap.back.entity.user.DanmuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DanmuMapper {
    List<DanmuEntity> findSectionAllDanmus(@Param("sectionToken") String sectionToken);

    List<DanmuEntity> findSectionUserDanmus(@Param("sectionToken") String sectionToken, @Param("userToken") String userToken);
}
