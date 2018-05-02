package com.community.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JacksonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtils.class);

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * json字符串转单个对象
     *
     * @param jsonStr
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonStr, Class<T> valueType) {
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            LOGGER.error("method[readValue],jsonStr:{},valueType:{}", jsonStr, valueType);
        }
        return null;
    }


    /**
     * json字符串转泛型数组
     *
     * @param jsonStr
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            LOGGER.error("method[readValue],jsonStr:{},valueTypeRef:{}", jsonStr, valueTypeRef);
        }
        return null;
    }

    /**
     * 将对象转json字符串
     *
     * @param jsonStr
     * @return
     */
    public static String toJSONString(Object jsonStr) {
        try {
            return objectMapper.writeValueAsString(jsonStr);
        } catch (Exception e) {
            LOGGER.error("method[toJSONString],jsonStr:{}", jsonStr);
        }
        return null;
    }
}
