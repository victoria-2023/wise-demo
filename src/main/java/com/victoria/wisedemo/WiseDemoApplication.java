package com.victoria.wisedemo;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class WiseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WiseDemoApplication.class, args);
    }

    /**
     * Optional: logs every @RequestMapping at startup so you can confirm
     * that /api/validate is being scanned and mapped.
     */
    @Bean
    ApplicationRunner logAllMappings(RequestMappingHandlerMapping mapping) {
        return args -> {
            System.out.println("=== Listing all Spring MVC mappings ===");
            mapping.getHandlerMethods().forEach((info, method) ->
                System.out.println(info + " → " + method));
            System.out.println("=== End mappings ===");
        };
    }
}
