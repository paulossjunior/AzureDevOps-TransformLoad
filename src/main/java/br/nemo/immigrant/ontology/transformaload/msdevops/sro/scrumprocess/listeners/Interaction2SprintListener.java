
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.listeners;


import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.filters.Interaction2SprintFilter;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers.Interaction2SprintMapper;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services.SprintService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Interaction2SprintListener {


    @Autowired
    private Interaction2SprintFilter filter;

    @Autowired
    private Interaction2SprintMapper mapper;

    @Autowired
    private SprintService service;



    @KafkaListener(topics = "application.msazuredevops.interaction", groupId = "interaction2sprint-group", concurrency = "2")
    public void consume(ConsumerRecord<String, String> payload) {

        try{

            String data = payload.value();

            if (filter.isValid(data)){

                this.service.process(payload, mapper);
            }

        }
        catch (Exception e ){
            log.error("EXCEPTION" + e.getMessage());

        }
    }
}
