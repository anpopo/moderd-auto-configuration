package org.moderd.moderdspringautoconfigure;

import org.moderd.core.InitializingExecutor;
import org.moderd.core.ModelContainer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnMissingBean(ModelContainer.class)
@EnableConfigurationProperties(ModerdProperties.class)
public class ModerdAutoConfiguration {

    private final ModerdProperties moderdProperties;

    public ModerdAutoConfiguration(ModerdProperties moderdProperties) {
        this.moderdProperties = moderdProperties;
    }

    @Bean
    public ModelContainer modelContainer() {
        String basePackage = moderdProperties.getBasePackage() == null ? "" : moderdProperties.getBasePackage();
        return InitializingExecutor.execute(basePackage);
    }

}
