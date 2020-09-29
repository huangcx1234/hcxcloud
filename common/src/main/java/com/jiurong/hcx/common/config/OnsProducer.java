package com.jiurong.hcx.common.config;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;


/**
 * @author hcx
 * @date 2019/3/27
 * explain:
 */
@Slf4j
public class OnsProducer {

    private String topic;

    private String tag;

    private Producer producer;

    public OnsProducer(String groupId, String accessKey, String secretKey, String sendMsgTimeoutMillis, String onsAddr, String topic, String tag) {
        Properties properties = new Properties();
        properties.setProperty(PropertyKeyConst.GROUP_ID, groupId);
        properties.put(PropertyKeyConst.AccessKey, accessKey);
        properties.put(PropertyKeyConst.SecretKey, secretKey);
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis);
        properties.put(PropertyKeyConst.NAMESRV_ADDR, onsAddr);

        producer = ONSFactory.createProducer(properties);
        producer.start();
        this.topic = topic;
        this.tag = tag;
        log.info("Producer Started {" + "topic:" + topic + ",tag:" + tag + "}");
    }

    /**
     * 同步发送（可靠性高、发送速度慢）
     *
     * @param body 内容
     * @return
     */
    public boolean send(String body, String key) {
        try {
            Message message = new Message(topic, tag, body.getBytes("UTF-8"));
            message.setKey(key);
            producer.send(message);
            return true;
        } catch (Exception e) {
            log.error("往消息队列发送失败{" + "tag:" + tag + ",body:" + body + "}");
            return false;
        }
    }

    /**
     * 单向发送（可靠性低、发送速度快）
     *
     * @param body 内容
     */
    public void sendOneway(String body, String key) {
        try {
            Message message = new Message(topic, tag, body.getBytes("UTF-8"));
            message.setKey(key);
            producer.sendOneway(message);
        } catch (Exception e) {
            log.error("往消息队列发送失败{" + "tag:" + tag + ",body:" + body + "}");
        }
    }
}
