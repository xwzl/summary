package com.summar.elasticsearch.controller;

import com.java.tool.model.vo.ResultVO;
import com.summar.elasticsearch.domain.BookDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ElasticSearch 测试
 *
 * @author xuweizhi
 * @since 2021/07/19 19:12
 */
@RestController
@Api(tags = "elasticsearch")
@RequestMapping("elasticsearch")
public class ElasticSearchController {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;


    /**
     * 查询数据
     *
     * @return 返回值
     */
    @ApiOperation("查询list")
    @GetMapping("search")
    public ResultVO<SearchHits<BookDO>> search() {
        Query query = new CriteriaQuery(new Criteria());
        SearchHits<BookDO> search = elasticsearchRestTemplate.search(query, BookDO.class);
        return new ResultVO<>(search);
    }

    /**
     * 插入数据
     *
     * @param name 名称
     * @return 返回值
     */
    @GetMapping("save")
    @ApiOperation("插入数据")
    public BookDO save(String name) {
        BookDO bookDO = new BookDO();
        bookDO.setBookName(name);

        return elasticsearchRestTemplate.save(bookDO);
    }
}
