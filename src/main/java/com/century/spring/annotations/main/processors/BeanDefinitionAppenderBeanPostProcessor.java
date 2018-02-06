package com.century.spring.annotations.main.processors;

import com.century.spring.annotations.Main;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanDefinitionAppenderBeanPostProcessor implements BeanPostProcessor {

  @Autowired
  private ConfigurableListableBeanFactory factory;

  //trying to repair bean definition, initialized over java configs
  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    Arrays.stream(factory.getBeanDefinitionNames()).parallel().forEach(name -> {
      if (neededMainBehavior(bean)) {
        BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
        if (Objects.isNull(beanDefinition.getBeanClassName())) {
          beanDefinition.setBeanClassName(bean.getClass().getCanonicalName());
        }
      }
    });

    return bean;
  }

  private boolean neededMainBehavior(Object bean) {
    return Arrays.stream(bean.getClass().getMethods())
        .anyMatch(method -> method.isAnnotationPresent(Main.class));
  }


  //default behavior
  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }


}
