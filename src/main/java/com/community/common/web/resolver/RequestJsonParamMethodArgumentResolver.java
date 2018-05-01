package com.community.common.web.resolver;


import com.community.common.annotations.RequestJsonParam;
import com.community.domain.core.MapWrapper;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import javax.servlet.ServletException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RequestJsonParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestJsonParamMethodArgumentResolver.class);

    private ObjectMapper mapper = new ObjectMapper();

    public RequestJsonParamMethodArgumentResolver() {
        super(null);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(RequestJsonParam.class)) {
            return true;
        }
        return false;
    }

    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        RequestJsonParam annotation = parameter.getParameterAnnotation(RequestJsonParam.class);
        return new RequestJsonParamNamedValueInfo(annotation);

    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest) throws Exception {
        String[] paramValues = webRequest.getParameterValues(name);
        Class<?> paramType = parameter.getParameterType();
        if (paramValues == null) {
            return null;
        }
        try {
            if (paramValues.length == 1) {
                String paramJson = paramValues[0];
                Type type = parameter.getGenericParameterType();
                if (MapWrapper.class.isAssignableFrom(paramType)) {
                    MapWrapper<?, ?> jsonMap = (MapWrapper<?, ?>) paramType.newInstance();
                    MapType mapType = (MapType) getJavaType(HashMap.class);
                    if (type instanceof ParameterizedType) {
                        mapType = (MapType) mapType.narrowKey((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]);
                        mapType = (MapType) mapType.narrowContentsBy((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[1]);
                    }
                    jsonMap.setInnerMap(mapper.<Map>readValue(paramJson, mapType));
                    return jsonMap;
                }
                JavaType javaType = getJavaType(paramType);
                if (Collection.class.isAssignableFrom(paramType)) {
                    javaType = javaType.narrowContentsBy((Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0]);
                }
                return mapper.readValue(paramValues[0], javaType);
            }

        } catch (Exception e) {
            throw new JsonMappingException("Could not read request json parameter", e);
        }

        throw new UnsupportedOperationException(
                "too many request json parameter '" + name + "' for method parameter type [" + paramType + "], only support one json parameter");
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.type(clazz);
    }

    @Override
    protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
        String paramType = parameter.getParameterType().getName();
        throw new ServletRequestBindingException(
                "Missing request json parameter '" + paramName + "' for method parameter type [" + paramType + "]");
    }


    private class RequestJsonParamNamedValueInfo extends NamedValueInfo {

        private RequestJsonParamNamedValueInfo() {
            super("", false, null);
        }

        private RequestJsonParamNamedValueInfo(RequestJsonParam annotation) {
            super(annotation.value(), annotation.required(), null);
        }
    }

}
