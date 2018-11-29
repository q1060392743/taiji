package cn.com.taiji.taiji;

import cn.com.taiji.taiji.config.MyLocaleResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

@SpringBootApplication
public class TaijiApplication {
    //basename对应上文中配置文件写入的值
    @Value(value = "${spring.messages.basename}")
    private String basename;

    public static void main(String[] args) {
        SpringApplication.run(TaijiApplication.class, args);
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //获取国际化properties文件
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(basename);
        return messageSource;
    }

}