package com.summary.elasticsearch.controller;

import com.java.tool.model.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

/**
 * swagger-ui.html
 *
 * @author xuweizhi
 * @since 2021/07/19 18:17
 */
@RestController
@RequestMapping("swagger")
@Api(tags = "Swagger 模板测试")
public class SwaggerController {


    /**
     * 返回任意值
     *
     * @return 返回值
     */
    @GetMapping("returnIntValue")
    public ResultVO<Integer> returnIntValue() {
        SecureRandom secureRandom = new SecureRandom();
        return new ResultVO<>(secureRandom.nextInt(100));
    }
}
