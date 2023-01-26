package org.zerock.web_workbook_b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class SampleJsonController {
    @GetMapping("/helloArr")
    public String[] helloArr(){
        log.info("helloArr........");
        return new String[]{"AAA"};
    }

}
