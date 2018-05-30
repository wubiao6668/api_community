package com.community.common.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class RequestUtils {
    public static Map<String, String> queryStringToParamMap(String queryString) {
        Map<String, String> paramMap = Maps.newHashMap();
        if (StringUtils.isBlank(queryString)) {
            return paramMap;
        }
        String[] queryParams = queryString.split("&");
        for (String queryParam : queryParams) {
            String[] paramKeyValue = queryParam.split("=");
            String key = paramKeyValue[0];
            String value = null;
            if (paramKeyValue.length > 1) {
                value = paramKeyValue[1];
            }
            paramMap.put(key, value);
        }
        return paramMap;
    }
}
