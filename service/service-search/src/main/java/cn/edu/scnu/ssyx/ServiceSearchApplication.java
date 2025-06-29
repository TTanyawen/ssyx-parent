package cn.edu.scnu.ssyx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//取消数据源自动配置,当前连接ES，不是连接MySQL(因为在Service模块中配置了连接MySql如果不取消会被自动连接)
@EnableDiscoveryClient
@EnableFeignClients  //远程调用
public class ServiceSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSearchApplication.class, args);
    }
}