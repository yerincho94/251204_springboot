package kr.java.springboot.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app")
public class AppProps {
    private String msg;
    private int year;
    private List<String> skill;
}
