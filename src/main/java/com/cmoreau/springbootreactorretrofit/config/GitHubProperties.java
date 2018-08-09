package com.cmoreau.springbootreactorretrofit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("github.api")
@Data
public class GitHubProperties {

    private String host;

}
