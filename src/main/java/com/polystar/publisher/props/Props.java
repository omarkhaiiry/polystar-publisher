package com.polystar.publisher.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "props")
public class Props {

    private String analyticsServerUrl;
    private String topWordsPath;
    private String topRepeatedPath;
    private String topOnetimeRepeatedPath;

}
