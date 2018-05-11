package com.community.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.community.common.constant.Constant.FUTURE_TIMEOUT;

public class FutureUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FutureUtils.class);
    public static <T> T get(Future<T> tFuture) {
        if (null == tFuture){
            return null;
        }
        T t = null;
        try {
            t = tFuture.get(FUTURE_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("method[get],e=",e);
        } catch (ExecutionException e) {
            LOGGER.error("method[get],e=",e);
        } catch (TimeoutException e) {
            LOGGER.error("method[get],e=",e);
        }
        return t;
    }
}
