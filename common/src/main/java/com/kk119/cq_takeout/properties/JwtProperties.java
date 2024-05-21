package com.kk119.cq_takeout.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cq.jwt")
@Data
public class JwtProperties {
    private String adminSecretKey;
    private Long adminTtl;
    private String adminTokenName;

    private String userSecretKey;
    private Long userTtl;
    private String userTokenName;
}
