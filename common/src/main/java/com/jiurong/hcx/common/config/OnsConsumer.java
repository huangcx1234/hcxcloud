package com.jiurong.hcx.common.config;

import com.aliyun.openservices.ons.api.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;


/**
 * @author hcx
 * @date 2019/3/27
 * explain:
 */
@Slf4j
public class OnsConsumer {

    private Consumer consumer;

    public OnsConsumer(String groupId, String accessKey, String secretKey, String onsAddr, String messageModel) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.GROUP_ID, groupId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        properties.put(PropertyKeyConst.NAMESRV_ADDR, onsAddr);
        properties.put(PropertyKeyConst.MessageModel, messageModel);
        consumer = ONSFactory.createConsumer(properties);
    }

    public void subscribe(String topic, String tag, MessageListener messageListener) {
        consumer.subscribe(topic, tag, messageListener);
        consumer.start();
        log.info("Consumer Started {" + "topic:" + topic + ",tag:" + tag + "}");
    }
}
