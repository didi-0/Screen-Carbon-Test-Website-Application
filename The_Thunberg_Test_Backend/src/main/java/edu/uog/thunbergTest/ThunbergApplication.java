package edu.uog.thunbergTest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.uog.thunbergTest.mapper")
public class ThunbergApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThunbergApplication.class, args);
    }

}
