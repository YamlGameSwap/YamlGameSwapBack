package com.yamlgameswap.back.controller;

import com.yamlgameswap.back.entity.book.book.BookResult;
import com.yamlgameswap.back.entity.result.Result;
import com.yamlgameswap.back.enums.WorkEnum;
import com.yamlgameswap.back.service.works.BookService;
import com.yamlgameswap.back.service.works.factory.ServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j(topic = "controller.Space")
@RestController
@RequestMapping(value = "/api/book")
public class BookController {
    @Autowired
    private ServiceFactory serviceFactory;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Result<List<BookResult>> get(
            HttpServletRequest request,
            @RequestParam(required = false, name = "space_token") String spaceToken,
            @RequestParam(required = false, name = "user_token") String userToken,
            @RequestParam(required = false, name = "book_type") int bookType,
            @RequestParam(required = false, name = "content_type") int contentType,
            @RequestParam("page") Integer page
    ) {
        return ((BookService) serviceFactory.getService(WorkEnum.BOOKSERVICE.getService())).getBook(spaceToken, userToken, bookType, contentType, page, request);
    }

    @RequestMapping(value = "/getBookDetail", method = RequestMethod.GET)
    public Result<BookResult> getBookDetail(HttpServletRequest request, @RequestParam("book_token") String bookToken) {
        return ((BookService) serviceFactory.getService(WorkEnum.BOOKSERVICE.getService())).getBookDetail(bookToken, request);
    }
}