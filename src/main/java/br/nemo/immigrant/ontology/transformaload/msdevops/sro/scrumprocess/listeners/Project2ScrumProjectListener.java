
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.listeners;

import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.filters.Project2ScrumProjectFilter;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers.Project2ScrumProjectMapper;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services.ScrumProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Project2ScrumProjectListener {


    @Autowired
    private Project2ScrumProjectFilter filter;

    @Autowired
    private Project2ScrumProjectMapper mapper;

    @Autowired
    private ScrumProjectService service;


    @KafkaListener(topics = "application.msazuredevops.project", groupId = "project2scrumproject-group", concurrency = "2")
    public void consume(ConsumerRecord<String, String> payload) {

        try{

            String data = payload.value();

            if (filter.isValid(data)){

                this.service.process(payload, mapper);
            }

        }catch (Exception e ){
            log.error(e.getMessage());
        }
    }
}
