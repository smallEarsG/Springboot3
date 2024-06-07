package org.example.h5;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//的作用表明Test测试类要使用注入的类，比如@Autowired注入的类
//    有了@RunWith(SpringRunner.class)这些类才能实例化到spring容器中，自动注入才能生效
@RunWith(SpringRunner.class)
@SpringBootTest
class H5ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("=====guo====");
    }

}
