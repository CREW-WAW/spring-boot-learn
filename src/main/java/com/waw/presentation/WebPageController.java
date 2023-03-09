package com.waw.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Tag(name = "Web Page", description = "웹 페이지용 Controller")
@Controller
public class WebPageController {

  @Operation(summary = "Page", description = "Index 페이지 이동")
  @GetMapping(value = "/")
  public String main() {
    return "thymeleaf/index";
  }
}
