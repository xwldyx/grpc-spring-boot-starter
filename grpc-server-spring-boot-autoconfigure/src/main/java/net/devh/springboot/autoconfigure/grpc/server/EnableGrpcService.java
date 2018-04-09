/*
 * Copyright (c) 2018.
 * 项目名称：examples
 * 文件名称：EnableGrpcService.java
 * Date：18-4-8 上午10:07
 * Author：boni
 */

package net.devh.springboot.autoconfigure.grpc.server;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface EnableGrpcService {
    boolean value() default true;
}
