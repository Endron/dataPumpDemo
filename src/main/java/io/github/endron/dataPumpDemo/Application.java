package io.github.endron.dataPumpDemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringApplicationConfiguration
@EnableBatchProcessing
@EnableJpaRepositories
@EnableTransactionManagement
public class Application {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private InsertDataRepository repository;

    @Value("${importFile}")
    private Resource importResource;

    @Value("${splitRegex}")
    private Optional<String> splitRegex;

    @Value("${chunkSize}")
    private Optional<Integer> chunkSize;

    public static void main(final String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @StepScope
    public ItemReader<String> itemReader() throws IOException {
        final Path importPath = importResource.getFile().toPath();
        final Iterator<String> iterator = Files.lines(importPath).iterator();

        return new IteratorItemReader<>(iterator);
    }

    @Bean
    public ItemProcessor<String, InsertData> itemProcessor() {
        return string -> {
            final String[] array = string.split(splitRegex.orElse(";"));

            final InsertData insertData = new InsertData();
            insertData.setFieldA(array[0]);
            insertData.setFieldB(array[1]);
            insertData.setFieldC(array[2]);

            return insertData;
        };
    }

    @Bean
    @StepScope
    public Step importStep() throws IOException {
        return stepBuilderFactory.get("importStep")
                .<String, InsertData>chunk(chunkSize.orElse(200))
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(list -> list.forEach(repository::save))
                .build();
    }

    @Bean
    @Scope(AbstractBeanFactory.SCOPE_PROTOTYPE)
    public Job importJob() throws IOException {
        return jobBuilderFactory.get("importJob")
                .flow(importStep())
                .end()
                .build();
    }
}
