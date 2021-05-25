package com.java.dubbo.my.api.impl;

import com.java.dubbo.my.api.BookService;
import com.java.dubbo.my.model.BookVO;
import com.java.dubbo.my.utils.BookDataUtil;
import com.java.tool.model.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 书服务接口实现
 *
 * @author xuweizhi
 * @since 2021/05/25 14:47
 */
@Slf4j
public class BookServiceImpl implements BookService {

    @Override
    public Integer selectBookCount() {
        return BookDataUtil.books.size();
    }

    @Override
    public ResultVO<BookVO> getBookById(String id) {
        return ResultVO.success(BookDataUtil.books.get(id));
    }

    @Override
    public String helloService(String content) {
        log.info("Client content {}", content);
        return "service : " + content;
    }
}

