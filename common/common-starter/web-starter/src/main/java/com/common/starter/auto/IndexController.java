package com.common.starter.auto;

import com.common.starter.auto.properties.HelloProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xuweizhi
 * @since 2021/07/30 12:14
 */
@RestController
public class IndexController {

    private HelloProperties helloProperties;

    public IndexController(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    @RequestMapping("/")
    public String index() {
        return helloProperties.getName() + "欢迎您";
    }

}
