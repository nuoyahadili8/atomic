package com.github.al.registration.listener;

import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.log4j.Log4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/12 19:27
 * @Modified By:
 */
@Configuration
@Log4j
@EnableScheduling
public class EurekaInstanceCanceledListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //服务挂掉自动通知
        if(applicationEvent instanceof EurekaInstanceCanceledEvent){
            EurekaInstanceCanceledEvent event= (EurekaInstanceCanceledEvent) applicationEvent;
            //获取当前Eureka实例中的节点信息
            PeerAwareInstanceRegistry registry= EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications=registry.getApplications();
            //遍历获取已注册节点中与当前失效节点ID一致的节点信息
            applications.getRegisteredApplications().forEach(registerdApplication ->{
                registerdApplication.getInstances().forEach(instanceInfo -> {
                    if(instanceInfo.getInstanceId().equals(event.getServerId())){
                        log.info("服务"+ instanceInfo.getAppName() + "挂掉了...");
                    }
                });
            });
        }

        if(applicationEvent instanceof EurekaInstanceRegisteredEvent){
            EurekaInstanceRegisteredEvent event= (EurekaInstanceRegisteredEvent) applicationEvent;
            log.info("服务："+event.getInstanceInfo().getAppName()+"注册成功了...");
        }

        if(applicationEvent instanceof EurekaInstanceRenewedEvent){
            EurekaInstanceRenewedEvent event = (EurekaInstanceRenewedEvent) applicationEvent;
            log.info("心跳检测服务："+event.getInstanceInfo().getAppName()+"...");
        }

        if(applicationEvent instanceof EurekaRegistryAvailableEvent){
            EurekaRegistryAvailableEvent event = (EurekaRegistryAvailableEvent) applicationEvent;
            log.info("服务 Aualiable!");
        }
    }
}
