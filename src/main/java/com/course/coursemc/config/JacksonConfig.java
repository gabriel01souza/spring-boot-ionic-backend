package com.course.coursemc.config;

import com.course.coursemc.domain.PaymentComBoleto;
import com.course.coursemc.domain.PaymentComCard;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder(){
            public void configure(ObjectMapper objectMapper){
                objectMapper.registerSubtypes(PaymentComCard.class);
                objectMapper.registerSubtypes(PaymentComBoleto.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
