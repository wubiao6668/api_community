import com.community.manager.WsChannelManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

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
//        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("*******start*****");
//                List<WebSocketSession> webSocketSessionList = WebSocketWsChannelHandler.socketSessionList;
//                for (WebSocketSession sessionTemp : webSocketSessionList) {
//                    TextMessage textMessage = new TextMessage("sdfsfds" + RandomStringUtils.randomAlphanumeric(10000));
//                    try {
//                        sessionTemp.sendMessage(textMessage);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("*******start*****");
//            }
//        }, 1, 1, TimeUnit.SECONDS);

        SpringApplication.run(ApiCommunityApplication.class, args);
    }
}
