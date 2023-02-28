import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.moderd.core.InitializingExecutor;
import org.moderd.core.ModelContainer;
import org.moderd.moderdspringautoconfigure.ModerdAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class AutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ModerdAutoConfiguration.class));

    @Test
    public void modelContainer_존재하지_않는_경우() {
        this.contextRunner.run((context -> assertThat(context).hasSingleBean(ModelContainer.class)));
    }

    @Test
    public void modelContainer_가_존재하는_경우() {
        this.contextRunner.withBean(ModelContainer.class)
            .run((context -> assertThat(context.getBean(ModelContainer.class).getClassesWithModelAnnotation()).isEmpty()));
    }


    @Configuration(proxyBeanMethods = false)
    static class ModelContainerConfig {

        @Bean
        public ModelContainer modelContainer() {
            return InitializingExecutor.execute("org.moderd.moderdspringautoconfigure");
        }

    }

}
