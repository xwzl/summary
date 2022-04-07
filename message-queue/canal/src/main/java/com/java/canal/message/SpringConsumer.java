package com.java.canal.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.canal.constants.CanalConstant;
import com.java.canal.domain.CanalMessage;
import com.java.canal.domain.InventoryDTO;
import com.java.canal.message.impl.InventoryElasticsearchHandler;
import com.java.canal.message.impl.RedisElasticsearchHandler;
import com.java.tool.utils.JsonUtil;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Canal Message 消费
 *
 * @author xuweizhi
 * @since 2022/04/07 11:44
 */
@Component
@RocketMQMessageListener(consumerGroup = "sync_inventory", topic = "cloud_stock_inventory")
public class SpringConsumer implements RocketMQListener<String> {

    @Resource
    private InventoryElasticsearchHandler inventoryElasticsearchHandler;

    @Resource
    private RedisElasticsearchHandler redisElasticsearchHandler;

    @Override
    public void onMessage(String message) {
        CanalMessage<InventoryDTO> canalMessage = JsonUtil.fromJson(message, new TypeReference<CanalMessage<InventoryDTO>>() {
        });
        if (canalMessage != null) {
            if (CanalConstant.INSERT.equals(canalMessage.getType())) {
                inventoryElasticsearchHandler.insert(canalMessage.getData());
                redisElasticsearchHandler.insert(canalMessage.getData());
            } else if (CanalConstant.UPDATE.equals(canalMessage.getType())) {
                inventoryElasticsearchHandler.update(canalMessage.getData());
                redisElasticsearchHandler.update(canalMessage.getData());
            } else if (CanalConstant.DELETE.equals(canalMessage.getType())) {
                inventoryElasticsearchHandler.delete(canalMessage.getData());
                redisElasticsearchHandler.delete(canalMessage.getData());
            }
            System.out.println("Received message : " + canalMessage);
        }
    }

}
