package org.moderd.moderdspringautoconfigure;

import org.moderd.core.InitializingExecutor;
import org.moderd.core.ModelContainer;
import org.moderd.core.ModerdConfigParams;
import org.moderd.core.annotation.EnableModelScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(ModelContainer.class)
@EnableConfigurationProperties(ModerdProperties.class)
public class ModerdAutoConfiguration {

    private final ModerdProperties moderdProperties;
    private final BeanFactory beanFactory;

    public ModerdAutoConfiguration(ModerdProperties moderdProperties,
        BeanFactory beanFactory) {
        this.moderdProperties = moderdProperties;
        this.beanFactory = beanFactory;
    }


    @Bean
    @ConditionalOnMissingBean
    public ModelContainer modelContainer() {
        String basePackage = moderdProperties.getBasePackage() == null
            ? beanFactory.getBean(EnableModelScan.class).scanFrom()
            : System.getProperty(ModerdConfigParams.BASE_PACKAGE);
        
        InitializingExecutor executor = new InitializingExecutor(basePackage);
        return executor.getModelContainer();
    }

}
