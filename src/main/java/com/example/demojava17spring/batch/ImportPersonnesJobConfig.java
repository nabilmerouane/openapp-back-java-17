package com.example.demojava17spring.batch;

import com.example.demojava17spring.dto.PersonneCsv;
import com.example.demojava17spring.model.Personne;
import com.example.demojava17spring.repository.PersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportPersonnesJobConfig {

  private static final Logger log = LoggerFactory.getLogger(ImportPersonnesJobConfig.class);

  @Bean
  FlatFileItemReader<PersonneCsv> personneReader() {
    return new FlatFileItemReaderBuilder<PersonneCsv>()
        .name("personneReader")
        .resource(new ClassPathResource("personnes.csv"))
        .linesToSkip(1)
        .delimited()
        .delimiter(";")
        .names("nom", "prenom")
        .targetType(PersonneCsv.class)
        .build();
  }

  @Bean
  Job importPersonnesJob(JobRepository jobRepository, Step importPersonnesStep) {
    return new JobBuilder("importPersonnesJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .start(importPersonnesStep)
        .build();
  }

  @Bean
  Step importPersonnesStep(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      PersonneRepository personneRepository,
      FlatFileItemReader<PersonneCsv> personneReader) {
    return new StepBuilder("importPersonnesStep", jobRepository)
        .<PersonneCsv, Personne>chunk(30)
        .transactionManager(transactionManager)
        .reader(personneReader)
        .processor(
            item -> {
              Personne personne = new Personne();
              personne.setPrenom(item.prenom());
              personne.setNom(item.nom().toUpperCase());
              return personne;
            })
        .writer(chunk -> personneRepository.saveAll(chunk.getItems()))
        .build();
  }
}
