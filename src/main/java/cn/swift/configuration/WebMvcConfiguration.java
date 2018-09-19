package cn.swift.configuration;

import java.nio.charset.Charset;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Bean
  public Validator validator() {
    ValidatorFactory vf = Validation.byProvider(HibernateValidator.class)
        .configure()
        .failFast(true)
        .buildValidatorFactory();
    return vf.getValidator();
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
    stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
    FastJsonConfig config = new FastJsonConfig();
    config.setSerializerFeatures(SerializerFeature.PrettyFormat,
        SerializerFeature.DisableCircularReferenceDetect);
    converter.setFastJsonConfig(config);
    converters.add(stringConverter);
    converters.add(converter);
  }
  
  
}
