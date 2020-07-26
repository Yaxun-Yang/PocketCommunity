package org.yyx.pocket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("org.yyx.pocket.persistence")//mybaits绑定
@CrossOrigin //表示允许跨域访问
public class PocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(PocketApplication.class, args);
    }

}
