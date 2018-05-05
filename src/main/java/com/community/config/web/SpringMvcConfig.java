package com.community.config.web;

import com.community.common.web.resolver.RequestJsonParamMethodArgumentResolver;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class SpringMvcConfig extends WebMvcConfigurationSupport {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new RequestJsonParamMethodArgumentResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        if (CollectionUtils.isNotEmpty(converters)) {
            for (HttpMessageConverter messageConverter : converters) {
                if (messageConverter instanceof MappingJackson2HttpMessageConverter) {
                    ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) messageConverter).getObjectMapper();
                    if (null != objectMapper) {
                        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    }
                }
            }
        }
        super.extendMessageConverters(converters);
    }
}
