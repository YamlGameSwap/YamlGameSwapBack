package com.yamlgameswap.back.service.works;

import com.yamlgameswap.back.entity.book.book.BookEntity;
import com.yamlgameswap.back.entity.book.book.BookResult;
import com.yamlgameswap.back.entity.except.DefinitionException;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.MessageEnum;
import com.yamlgameswap.back.enums.StatusEnum;
import com.yamlgameswap.back.mapper.book.BookFollowMapper;
import com.yamlgameswap.back.mapper.book.BookLikeMapper;
import com.yamlgameswap.back.mapper.book.BookMapper;
import com.yamlgameswap.back.mapper.user.UserMapper;
import com.yamlgameswap.back.service.works.factory.ServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j(topic = "service.BookService")
public class BookService implements ServiceInter {

    @Value("${page.bookLimit}")
    private int bookPageLimit;

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookFollowMapper bookFollowMapper;
    @Autowired
    private BookLikeMapper bookLikeMapper;
    @Autowired
    private UserMapper userMapper;

    public Result<Map<String, Object>> getBook(
            String spaceToken,
            String userToken,
            Integer bookType,
            Integer contentType,
            int page,
            HttpServletRequest request
    ) {

        /**
         * 分为 4 中情况
         *
         * 1. 默认查询所有 book
         * 2. 查询某一 space 下面的 book
         * 3. 查询某个作者的 book
         * 4. 查询某个作者在 space 下面的 book
         *
         * 在上面的 4 种情况下，又分为 4 种
         *
         * 1. 根据 bookType 类型查询
         * 2. 根据 contentType 查询
         * 3. 根据 bookType 和 contentType 查询
         * 4. 全查询
         * */
        String language = request.getHeader("language").toUpperCase();
        try {
            int spaceTokenInt = spaceToken != null && spaceToken.length() > 0 ? 1 : 0;
            int userTokenInt = userToken != null && userToken.length() > 0 ? 3 : 0;
            int intType = spaceTokenInt + userTokenInt;
            int count;
            List<BookEntity> bookEntityList = new ArrayList<>();
            switch (intType) {
                case 0: // 查询全部,
                case 3: // 查询某一作者下面的书
                    // 这两种情况是查询 Book 这张表
                    count = bookMapper.getBookCount(userToken, bookType, contentType);
                    if (count <= (page - 1) * bookPageLimit) {
                        throw new Exception("页码不对");
                    }
                    bookEntityList = bookMapper.getBooksEntity(userToken, bookType, contentType, page - 1, bookPageLimit);
                    break;
                case 1: // 查询某一 space 下面的书
                case 4: // 查询某一space 下面的某一作者的书
                    // 这两种情况是查询 SpaceBook 这张表
                    count = bookMapper.getSpaceBookCount(spaceToken, userToken, bookType, contentType);
                    if (count <= (page - 1) * bookPageLimit) {
                        throw new Exception("页码不对");
                    }
                    bookEntityList = bookMapper.getSpaceBooksEntity(spaceToken, userToken, bookType, contentType, page - 1, bookPageLimit);
                    break;
                default:
                    throw new Exception("未知参数");
            }
            // Entity to Result


            if (bookEntityList.size() > 0) {
                List<BookResult> bookResultList = getBookResultListByEntity(bookEntityList);
                Result<Map<String, Object>> result = new Result<>();
                Map<String, Object> resultMap = new HashMap<>();
                result.setCode(MessageEnum.book_get_success.getResponseCode());
                result.setMessage("success");
                resultMap.put("count", count);
                resultMap.put("data", bookResultList);
                result.setData(resultMap);
                result.setStatus(StatusEnum.SUCCESS.getStatus());
                return result;
            } else {
                throw new Exception("没有数据");
            }
        } catch (Exception e) {
            if (e.getMessage().equals("没有数据") || e.getMessage().equals("未知参数") || e.getMessage().equals("页码不对")
            ) {
                ;
            } else {
                log.error("error: ", e);
            }
            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.book_error.getResponseCode());
            throw definitionException;
        }

    }

    public Result<BookResult> getBookDetail(
            String bookToken,
            HttpServletRequest request
    ) {
        String language = request.getHeader("language").toUpperCase();
        BookEntity bookEntity = bookMapper.getBookDetail(bookToken);
        if (bookEntity != null) {
            BookResult bookResult = getBookResultByEntity(bookEntity);
            Result<BookResult> result = new Result<>();
            result.setCode(MessageEnum.book_detail_get_success.getResponseCode());
            result.setMessage("success");
            result.setData(bookResult);
            result.setStatus(StatusEnum.SUCCESS.getStatus());
            return result;
        } else {
            DefinitionException definitionException = new DefinitionException();
            definitionException.setLanguage(language);
            definitionException.setErrorCode(MessageEnum.book_detail_error.getResponseCode());
            throw definitionException;
        }
    }

    public List<BookResult> getBookResultListByEntity(List<BookEntity> bookEntityList) {
        List<BookResult> bookResultList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {
            BookResult bookResult = getBookResultByEntity(bookEntity);
            if (bookResult != null) {
                bookResultList.add(bookResult);
            }
        }
        return bookResultList;
    }

    public BookResult getBookResultByEntity(BookEntity bookEntity) {
        try {
            BookResult bookResult = new BookResult();
            String bookToken = bookEntity.getBookToken();
            int bookFollow = bookFollowMapper.getBookFollow(bookToken);
            int bookLike = bookLikeMapper.getBookLike(bookToken);
            String address = userMapper.getAddressByUserToken(bookToken);

            bookResult.setLike(bookLike);
            bookResult.setFollow(bookFollow);
            bookResult.setAddress(address);
            bookResult.setTitle(bookEntity.getTitle());
            bookResult.setIntro(bookEntity.getIntro());
            bookResult.setTwitter(bookEntity.getTwitter());
            bookResult.setDiscord(bookEntity.getDiscord());
            bookResult.setTelegram(bookEntity.getTelegram());
            bookResult.setWebsite(bookEntity.getWebsite());

            bookResult.setBookType(bookEntity.getBookType());
            bookResult.setContentType(bookEntity.getContentType());
            bookResult.setBook_banner_url(bookEntity.getBookBannerUrl());
            bookResult.setBook_token(bookEntity.getBookToken());
            bookResult.setToken_name(bookEntity.getTokenName());
            bookResult.setToken_contract(bookEntity.getTokenContract());

            return bookResult;
        } catch (Exception e) {
            return null;
        }

    }
}
