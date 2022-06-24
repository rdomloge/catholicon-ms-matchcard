package com.domloge.catholicon.catholiconmsmatchcard.config;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.domloge.catholicon.catholiconmsmatchcard.ZonedDateTimeReadConverter;
import com.domloge.catholicon.catholiconmsmatchcard.ZonedDateTimeWriteConverter;

@Configuration
public class ConverterConfig {
    private List<Converter<?,?>> converters = new ArrayList<Converter<?,?>>();

    @Bean
    public MongoCustomConversions customConversions() {
        converters.add(new ZonedDateTimeReadConverter());
        converters.add(new ZonedDateTimeWriteConverter());
        return new MongoCustomConversions(converters);
    }
}