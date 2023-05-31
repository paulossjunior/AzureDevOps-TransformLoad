
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services;

import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumDevelopmentTask;
import br.nemo.immigrant.ontology.entity.scrumprocess.repositories.ScrumDevelopmentTaskRepository;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScrumDevelopmentTaskService {


    @Autowired
    private ScrumDevelopmentTaskRepository repository;



    public void process(ConsumerRecord<String, String> payload,Mapper<ScrumDevelopmentTask> mapper) {

        try{

            ScrumDevelopmentTask scrumdevelopmenttask = mapper.map(payload.value());

            this.save(scrumdevelopmenttask);

        }catch (Exception e ){
            log.error(e.getMessage());
        }
    }

    private void save (ScrumDevelopmentTask scrumdevelopmenttask) {



        this.repository.save(scrumdevelopmenttask);

        log.info("Saving");
    }

}
