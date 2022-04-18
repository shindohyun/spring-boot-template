package com.shindohyun.template.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/securityTest")
@Controller
public class SecurityTestController {
  @GetMapping("/boardList")
  public void boardList() {
    log.info("list: access to all");
  }

  @GetMapping("/boardRegister")
  public void boardRegisterForm() {
    log.info("registerForm: access to member");
  }

  @GetMapping("/noticeList")
  public void noticeList() {
    log.info("list: access to all");
  }

  @GetMapping("/noticeRegister")
  public void noticeRegisterForm() {
    log.info("registerForm: access to admin");
  }

  @GetMapping("/accessError")
  public void accessDenied(Authentication auth, Model model) {
    log.info("access Denied: " + auth);

    model.addAttribute("msg", "Access Denied");
  }
}
