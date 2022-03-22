package com.spring.cloud.inventory.service.impl;

import com.spring.cloud.commom.inventory.dto.InventoryDTO;
import com.spring.cloud.commom.inventory.entity.InventoryDO;
import com.spring.cloud.inventory.mapper.InventoryMapper;
import com.spring.cloud.inventory.service.InventoryService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * InventoryServiceImpl.
 *
 * @author xuweizhi
 */
@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryServiceImpl.class);

    private final InventoryMapper inventoryMapper;

    @Autowired(required = false)
    public InventoryServiceImpl(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    /**
     * 扣减库存操作.
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true
     */
    @Override
    @Transactional
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean decrease(InventoryDTO inventoryDTO) {
        LOGGER.info("==========try扣减库存decrease===========");
        // 从打印日志可以看出，Hmily 超时解决了悬挂空回滚问题: 下游业务超时，导致上游认为失败，上下游同时 cancel
        // 但是此时,下游业务有可能执行成功，但是 cancel 已经无法回滚数据
        // try confirm cancel 方法没有做幂等，要禁止重试
        // 空回滚：下游超时，上下游同时 cancel,但是下游有可能出现 try 失败的情况
        // 悬挂: 下游超时, 上下游同时 cancel,但是下游有可能出现 try 成功的情况，导致数据不一致
        // try {
        //     Thread.sleep(61000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        // inventoryMapper.decrease(inventoryDTO);
        // throw new RuntimeException("xxx");
        return true;
    }

    @Override
    public Boolean testDecrease(InventoryDTO inventoryDTO) {
        inventoryMapper.testDecrease(inventoryDTO);
        return true;
    }

    /**
     * 获取商品库存信息.
     *
     * @param productId 商品id
     * @return InventoryDO
     */
    @Override
    public InventoryDO findByProductId(String productId) {
        return inventoryMapper.findByProductId(productId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    @Transactional
    public Boolean mockWithTryException(InventoryDTO inventoryDTO) {
        //这里是模拟异常所以就直接抛出异常了
        throw new HmilyRuntimeException("库存扣减异常！");
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    @Transactional(rollbackFor = Exception.class)
    public Boolean mockWithTryTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当前线程暂停10秒
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========springcloud调用扣减库存mockWithTryTimeout===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存不足");
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodTimeout(InventoryDTO inventoryDTO) {
        try {
            //模拟延迟 当前线程暂停11秒
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("==========Springcloud调用扣减库存确认方法===========");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean confirmMethodException(InventoryDTO inventoryDTO) {
        LOGGER.info("==========Springcloud调用扣减库存确认方法===========");
        final int decrease = inventoryMapper.decrease(inventoryDTO);
        if (decrease != 1) {
            throw new HmilyRuntimeException("库存不足");
        }
        return true;
        // throw new TccRuntimeException("库存扣减确认异常！");
    }

    public Boolean confirmMethod(InventoryDTO inventoryDTO) {
        LOGGER.info("==========confirmMethod库存确认方法===========");
        return inventoryMapper.confirm(inventoryDTO) > 0;
    }

    public Boolean cancelMethod(InventoryDTO inventoryDTO) {
        LOGGER.info("==========cancelMethod库存取消方法===========");
        return inventoryMapper.cancel(inventoryDTO) > 0;
    }
}
