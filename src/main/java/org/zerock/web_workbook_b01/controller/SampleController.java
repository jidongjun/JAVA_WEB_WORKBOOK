package org.zerock.web_workbook_b01.controller;

import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model){
        log.info("hello.........");
        model.addAttribute("msg","Hello world");
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");
        model.addAttribute("list",list);
    }
}
