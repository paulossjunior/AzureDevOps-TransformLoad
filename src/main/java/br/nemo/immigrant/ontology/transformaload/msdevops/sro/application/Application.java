package br.nemo.immigrant.ontology.transformaload.msdevops.sro.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = {"br.nemo.immigrant.ontology.transformaload.msdevops.sro.*"})
@EntityScan(basePackages = {"br.nemo.immigrant.ontology.entity.*"})
@EnableJpaRepositories(basePackages = {"br.nemo.immigrant.ontology.entity.*"})

public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
