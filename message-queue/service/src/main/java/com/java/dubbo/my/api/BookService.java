package com.java.dubbo.my.api;

import com.java.dubbo.my.model.BookVO;
import com.java.tool.model.vo.ResultVO;

/**
 * 书服务接口
 *
 * @author xuweizhi
 * @since 2021/05/25 13:58
 */
public interface BookService {

    /**
     * 获取 book 数量
     *
     * @return 数量
     */
    Integer selectBookCount();

    /**
     * 获取书籍信息
     *
     * @param id 主键
     * @return 返回值
     */
    ResultVO<BookVO> getBookById(String id);

    /**
     * 接口测试
     *
     * @param content 内容
     * @return 返回值
     */
    String helloService(String content);

}
