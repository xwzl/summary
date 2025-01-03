//package com.summary.elasticsearch.controller;
//
//import com.java.tool.model.vo.ResultVO;
//import com.summary.elasticsearch.domain.model.BookDO;
//import com.summary.elasticsearch.domain.vo.BookVO;
//import com.summary.elasticsearch.repository.BookRepository;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.data.elasticsearch.core.SearchHit;
//import org.springframework.data.elasticsearch.core.SearchHits;
//import org.springframework.data.elasticsearch.core.query.Criteria;
//import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
//import org.springframework.data.elasticsearch.core.query.Query;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import jakarta.annotation.Resource;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * ElasticSearch 测试
// *
// * @author xuweizhi
// * @since 2021/07/19 19:12
// */
//@Slf4j
//@RestController
//@Api(tags = "elasticsearch")
//@RequestMapping("elasticsearch")
//public class ElasticSearchController {
//
//    @Resource
//    private BookRepository bookRepository;
//
////    @Resource
////    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    /**
//     * 查询数据
//     *
//     * @return 返回值
//     */
//    @ApiOperation("查询list")
//    @GetMapping("search")
//    public ResultVO<List<BookDO>> search() {
//        Query query = new CriteriaQuery(new Criteria());
//        SearchHits<BookDO> search = elasticsearchRestTemplate.search(query, BookDO.class);
//        log.info(search.toString());
//        return new ResultVO<>(search.stream().map(SearchHit::getContent).collect(Collectors.toList()));
//    }
//
//    /**
//     * 插入数据
//     *
//     * @param bookVO 名称
//     * @return 返回值
//     */
//    @GetMapping("save")
//    @ApiOperation("插入数据")
//    public BookDO save(BookVO bookVO) {
//        BookDO bookDO = new BookDO();
//        BeanUtils.copyProperties(bookVO, bookDO);
//        return elasticsearchRestTemplate.save(bookDO);
//    }
//
//    /**
//     * 判断索引是否存在
//     *
//     * @param bookDO 索引名称
//     * @return 返回值
//     */
//    @ApiOperation("是否存在")
//    @GetMapping("indexExists")
//    public ResultVO<Optional<BookDO>> indexExists(BookDO bookDO) {
//        Optional<BookDO> book = bookRepository.findByAuthor(bookDO.getAuthor());
//        log.info(bookRepository.findAllByAuthor(bookDO.getAuthor()).toString());
//        return new ResultVO<>(book);
//    }
//}
