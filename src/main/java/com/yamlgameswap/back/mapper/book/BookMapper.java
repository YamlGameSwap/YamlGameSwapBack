package com.yamlgameswap.back.mapper.book;

import com.yamlgameswap.back.entity.book.book.BookEntity;
import com.yamlgameswap.back.entity.space.space.SpaceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    int getBookCount(
            @Param("userToken") String userToken,
            @Param("bookType") int bookType,
            @Param("contentType") int contentType
    );

    int getSpaceBookCount(
            @Param("userToken") String spaceToken,
            @Param("userToken") String userToken,
            @Param("bookType") int bookType,
            @Param("contentType") int contentType
    );

    BookEntity getBookDetail(@Param("bookToken") String bookToken);

    List<BookEntity> getBooksEntity(
            @Param("userToken") String userToken,
            @Param("bookType") int bookType,
            @Param("contentType") int contentType,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    List<BookEntity> getSpaceBooksEntity(
            @Param("userToken") String spaceToken,
            @Param("userToken") String userToken,
            @Param("bookType") int bookType,
            @Param("contentType") int contentType,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

}
