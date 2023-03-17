package com.yamlgameswap.back.mapper.book;

import com.yamlgameswap.back.entity.book.book.BookEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    int getBookCount(
            @Param("userToken") String userToken,
            @Param("bookType") Integer bookType,
            @Param("contentType") Integer contentType
    );

    int getSpaceBookCount(
            @Param("spaceToken") String spaceToken,
            @Param("userToken") String userToken,
            @Param("bookType") Integer bookType,
            @Param("contentType") Integer contentType
    );

    BookEntity getBookDetail(@Param("bookToken") String bookToken);

    List<BookEntity> getBooksEntity(
            @Param("userToken") String userToken,
            @Param("bookType") Integer bookType,
            @Param("contentType") Integer contentType,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    List<BookEntity> getSpaceBooksEntity(
            @Param("spaceToken") String spaceToken,
            @Param("userToken") String userToken,
            @Param("bookType") Integer bookType,
            @Param("contentType") Integer contentType,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

}
