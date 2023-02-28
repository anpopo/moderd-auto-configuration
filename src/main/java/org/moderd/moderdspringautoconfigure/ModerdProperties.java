package org.moderd.moderdspringautoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application properties 에서 사용자가 값을 입력하는 경우 사용되는 configuration properties
 */
@ConfigurationProperties(prefix = "moderd")
public class ModerdProperties {

    private String basePackage;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}
