package com.spring.cloud.gateway.config;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 业务断言
 *
 * @author xuweizhi
 * @since 2021/10/21 10:56
 */
public class BusinessRoutePredicateFactory extends AbstractRoutePredicateFactory<BusinessRoutePredicateFactory.Config> {

    /**
     * Type key.
     */
    public static final String TYPE_KEY = "type";

    /**
     * Data key.
     */
    public static final String DATA_KEY = "data";

    /**
     * Time key.
     */
    public static final String TIME_KEY = "time";

    /**
     * Business
     */
    private static final String BUSINESS_HEADER = "Business";

    public BusinessRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(TYPE_KEY, DATA_KEY, TIME_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                HttpHeaders headers = exchange.getRequest().getHeaders();
                List<String> nameList = headers.get(BUSINESS_HEADER);
                if (CollectionUtils.isEmpty(nameList)) {
                    return false;
                }
                for (String name : nameList) {
                    if (name.matches(config.type)) {
                        return true;
                    }
                }
                return false;
            }


            @Override
            public String toString() {
                return String.format("Cookie: name=%s regexp=%s time=%s", config.type, config.data, config.time);
            }
        };
    }

    public static class Config {

        private String type;

        private String data;

        private String time;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
