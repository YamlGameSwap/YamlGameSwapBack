package com.yamlgameswap.back.mapper.book;

import com.yamlgameswap.back.entity.book.book.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookLikeMapper {
    int getBookLike(
            @Param("bookToken") String bookToken
    );

}
