package net.devh.springboot.autoconfigure.grpc.client;

import java.lang.reflect.Field;
import java.util.List;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.google.common.collect.Lists;
import io.grpc.Channel;
import io.grpc.ClientInterceptor;
import lombok.SneakyThrows;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 5/17/16
 * ModifyBy: lwx
 */
public class GrpcClientBeanPostProcessor implements org.springframework.beans.factory.config.BeanPostProcessor {

	@Autowired
	private DefaultListableBeanFactory beanFactory;

	@Autowired
	private GrpcChannelFactory channelFactory;

	public GrpcClientBeanPostProcessor() {
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> clazz = bean.getClass();
		if(clazz.isAnnotationPresent(EnableGrpcClient.class)) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(GrpcClient.class)) {
					GrpcClient annotation = AnnotationUtils.getAnnotation(field, GrpcClient.class);
					List<ClientInterceptor> list = Lists.newArrayList();
					for (Class<? extends ClientInterceptor> clientInterceptorClass : annotation.interceptors()) {
						ClientInterceptor clientInterceptor;
						if (beanFactory.getBeanNamesForType(ClientInterceptor.class).length > 0) {
							clientInterceptor = beanFactory.getBean(clientInterceptorClass);
						} else {
							try {
								clientInterceptor = clientInterceptorClass.newInstance();
							} catch (Exception e) {
								throw new BeanCreationException("Failed to create interceptor instance", e);
							}
						}
						list.add(clientInterceptor);
					}

					Channel channel = channelFactory.createChannel(annotation.value(), list);
					Object target = getTargetBean(bean);
					ReflectionUtils.makeAccessible(field);
					ReflectionUtils.setField(field, target, channel);
				}
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@SneakyThrows
	private Object getTargetBean(Object bean) {
		Object target = bean;
		while (AopUtils.isAopProxy(target)) {
			target = ((Advised) target).getTargetSource().getTarget();
		}
		return target;
	}


}
