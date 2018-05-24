import com.community.common.web.handler.TestHandler;
import org.apache.commons.lang3.RandomStringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ComponentScan(basePackages = {
        "com.community.config",
        "com.community.dao.mapper",
        "com.community.service",
        "com.community.manager",
        "com.community.web"
})
@MapperScan(basePackages = "com.community.dao.mapper")
@SpringBootApplication
public class ApiCommunityApplication {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("*******start*****");
                List<WebSocketSession> webSocketSessionList = TestHandler.socketSessionList;
                for (WebSocketSession sessionTemp : webSocketSessionList){
                    TextMessage textMessage = new TextMessage("sdfsfds" + RandomStringUtils.randomAlphanumeric(10000));
                    try {
                        sessionTemp.sendMessage(textMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("*******start*****");
            }
        }, 1, 1, TimeUnit.SECONDS);

        SpringApplication.run(ApiCommunityApplication.class, args);
    }
}
