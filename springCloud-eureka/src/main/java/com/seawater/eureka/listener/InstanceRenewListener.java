package com.seawater.eureka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhouhaishui on 2018/4/26.
 */
@Configuration
public class InstanceRenewListener implements ApplicationListener<EurekaInstanceRenewedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstanceRenewListener.class);
    @Override
    public void onApplicationEvent(EurekaInstanceRenewedEvent event) {
        LOGGER.info("心跳检测服务：{}" ,event.getInstanceInfo().getAppName());
    }
}
