package org.zerock.web_workbook_b01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebWorkbookB01Application {

    public static void main(String[] args) {
        SpringApplication.run(WebWorkbookB01Application.class, args);
    }

}
