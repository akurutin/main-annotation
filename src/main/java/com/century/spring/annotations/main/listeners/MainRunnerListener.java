package com.century.spring.annotations.main.listeners;

import com.century.spring.annotations.Main;
import java.lang.reflect.Method;
import java.util.Objects;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.MethodInvokingBean;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

@Component
public class MainRunnerListener {
  @Autowired
  private ConfigurableListableBeanFactory factory;

  @EventListener
  @SneakyThrows
  public void handleContextRefresh(ContextRefreshedEvent event) {
    ApplicationContext context = event.getApplicationContext();
    String[] names = context.getBeanDefinitionNames();
    for (String name : names) {
      BeanDefinition beanDefinition = factory.getBeanDefinition(name);
      String beanClassName = beanDefinition.getBeanClassName();
      if (Objects.isNull(beanClassName)) {
        continue;
      }
      Class<?> beanClass;
      //TODO: does not work with spring boot dev tools
      //ClassLoader classLoader = ClassLoader.getSystemClassLoader();
      ClassLoader classLoader = this.getClass().getClassLoader();
        beanClass = ClassUtils
            .resolveClassName(beanClassName, classLoader);
      Method[] methods = beanClass.getMethods();
      for (Method method : methods) {
        if (method.isAnnotationPresent(Main.class)) {
          Object bean = context.getBean(name);
          //TODO: does not work with spring boot dev tools
          ReflectionUtils.invokeMethod(method, bean);

        }
      }

    }
  }

}
