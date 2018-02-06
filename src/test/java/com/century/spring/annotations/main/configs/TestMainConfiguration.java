package com.century.spring.annotations.main.configs;

import com.century.spring.annotations.main.listeners.MainRunnerListener;
import com.century.spring.annotations.main.processors.BeanDefinitionAppenderBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.century.spring.annotations.main.services")
public class TestMainConfiguration {

  @Bean
  public MainRunnerListener mainRunnerListener() {
    return new MainRunnerListener();
  }

  @Bean
  public BeanDefinitionAppenderBeanPostProcessor beanDefinitionAppenderBeanPostProcessor() {
    return new BeanDefinitionAppenderBeanPostProcessor();
  }

}
