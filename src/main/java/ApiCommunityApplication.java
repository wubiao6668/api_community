import org.mybatis.spring.annotation.MapperScan;
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
        SpringApplication.run(ApiCommunityApplication.class, args);
    }
}
