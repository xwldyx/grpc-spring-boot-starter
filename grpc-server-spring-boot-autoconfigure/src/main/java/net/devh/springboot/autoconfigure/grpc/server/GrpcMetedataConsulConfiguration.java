package net.devh.springboot.autoconfigure.grpc.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 5/17/16
 */
//@AutoConfigureAfter(GrpcServerAutoConfiguration.class)
//@ConditionalOnBean(annotation = EnableGrpcService.class, value = ConsulDiscoveryProperties.class)
public class GrpcMetedataConsulConfiguration {
/*
    @Autowired
    private ConsulDiscoveryProperties properties;

    @Autowired(required = false)
    private GrpcServerProperties grpcProperties;

    @PostConstruct
    public void init() {
        if (grpcProperties == null) {
            return;
        }
        this.properties.getTags().add("gRPC=" + grpcProperties.getPort());
    }
*/
}
