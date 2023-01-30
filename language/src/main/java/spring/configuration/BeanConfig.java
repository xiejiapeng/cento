package spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.model.Student;

@Configuration
public class BeanConfig {
    @Bean("alice")
    public Student alice(){
        return new Student("alice", 20);
    }

    @Bean("bob")
    public Student bob(){
        return new Student("bob", 16);
    }
}
