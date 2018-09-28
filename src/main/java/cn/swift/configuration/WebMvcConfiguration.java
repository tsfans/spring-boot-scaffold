package cn.swift.configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Value("${httpClient.maxTotal:200}")
  private int maxTotal;
  @Value("${httpClient.defaultMaxPerRoute:100}")
  private int defaultMaxPerRoute;
  @Value("${httpClient.connectionTimeout:10000}")
  private int connectionTimeout;
  @Value("${httpClient.connectionRequestTimeout:1000}")
  private int connectionRequestTimeout;
  @Value("${httpClient.socketTimeout:30000}")
  private int socketTimeout;
  @Value("${httpClient.readTimeout:10000}")
  private int readTimeout;
  @Value("${httpClient.validateAfterInactivity:60000}")
  private int validateAfterInactivity;


  @Bean
  public RestTemplate restTemplate() {
    return restTemplate(connectionTimeout, readTimeout, connectionRequestTimeout, maxTotal,
        defaultMaxPerRoute, socketTimeout, validateAfterInactivity);
  }

  private RestTemplate restTemplate(int connectTimeout, int readTimeout,
      int connectionRequestTimeout, int maxConnections, int maxPerRoute, int soTimeout,
      int validateAfterInactivity) {
    RestTemplate restTemplate = new RestTemplate(httpRequestFactory(connectTimeout, readTimeout,
        connectionRequestTimeout, maxConnections, maxPerRoute, soTimeout, validateAfterInactivity));
    restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
    StringHttpMessageConverter stringHttpMessageConverter =
        new StringHttpMessageConverter(StandardCharsets.UTF_8);
    for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
      if (restTemplate.getMessageConverters().get(i) instanceof StringHttpMessageConverter) {
        restTemplate.getMessageConverters().remove(i);
        restTemplate.getMessageConverters().add(i, stringHttpMessageConverter);
        break;
      }
    }
    return restTemplate;
  }

  private ClientHttpRequestFactory httpRequestFactory(int connectTimeout, int readTimeout,
      int connectionRequestTimeout, int maxConnections, int maxPerRoute, int soTimeout,
      int validateAfterInactivity) {
    HttpComponentsClientHttpRequestFactory factory =
        new HttpComponentsClientHttpRequestFactory(
            httpClient(maxConnections, maxPerRoute, soTimeout, validateAfterInactivity));
    factory.setConnectTimeout(connectTimeout);
    factory.setReadTimeout(readTimeout);
    factory.setConnectionRequestTimeout(connectionRequestTimeout);
    return factory;
  }

  private HttpClient httpClient(int maxConnections, int maxPerRoute, int soTimeout,
      int validateAfterInactivity) {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(maxConnections);
    connectionManager.setDefaultMaxPerRoute(maxPerRoute);
    connectionManager.setValidateAfterInactivity(validateAfterInactivity);
    SocketConfig defaultSocketConfig = SocketConfig.custom().setSoTimeout(soTimeout).build();
    connectionManager.setDefaultSocketConfig(defaultSocketConfig);
    return HttpClients.custom().setConnectionManager(connectionManager).build();
  }

  @Bean
  public Validator validator() {
    ValidatorFactory vf = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
        .buildValidatorFactory();
    return vf.getValidator();
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    StringHttpMessageConverter stringConverter =
        new StringHttpMessageConverter(StandardCharsets.UTF_8);
    FastJsonConfig config = new FastJsonConfig();
    config.setSerializerFeatures(SerializerFeature.PrettyFormat,
        SerializerFeature.DisableCircularReferenceDetect);
    converter.setFastJsonConfig(config);
    converters.add(stringConverter);
    converters.add(converter);
  }


}
