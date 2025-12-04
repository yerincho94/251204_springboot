package kr.java.springboot.config;

import kr.java.springboot.props.AppProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AppProps.class)
public class PropsConfig {
}
