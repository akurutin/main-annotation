package com.century.spring.annotations.main.services;

import com.century.spring.annotations.Main;
import org.springframework.stereotype.Component;

@Component
public class TestService {
  private int i = 0;

  @Main
  public void init() {
    i = 1;
  }

  public int getI() {
    return i;
  }
}
