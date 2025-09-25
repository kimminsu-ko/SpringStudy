package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        statefulService1.order("userA",10000);
        //싱글톤이니 service1의 price 값 변경이 되어버림
        statefulService2.order("userB",20000);

        int price = statefulService1.getPrice();
        //2만원 나옴
        System.out.println("Price is " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        //싱글톤은 상태가 없이 무상태로 관리해야함.
    }
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}