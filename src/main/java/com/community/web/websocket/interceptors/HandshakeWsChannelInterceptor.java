package com.community.web.websocket.interceptors;

import com.community.common.constant.Constant;
import com.community.common.util.RequestUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HandshakeWsChannelInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String queryString = request.getURI().getQuery();
        Map<String, String> paramMap = RequestUtils.queryStringToParamMap(queryString);
        Long userId = NumberUtils.toLong(paramMap.get(Constant.USER_ID_KEY), 0);
        if (0 == userId) {
            return false;
        }
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            attributes.put(entry.getKey(), entry.getValue());
        }
        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
