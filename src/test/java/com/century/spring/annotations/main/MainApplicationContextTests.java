package com.century.spring.annotations.main;

import com.century.spring.annotations.main.configs.TestMainConfiguration;
import com.century.spring.annotations.main.services.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestMainConfiguration.class)
public class MainApplicationContextTests {
  @Autowired
  private TestService testService;

  @Test
  public void testMainRunner() {
    int actualI = 1;
    Assert.assertEquals(actualI, testService.getI());
  }
}
