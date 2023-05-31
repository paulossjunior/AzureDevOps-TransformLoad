
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services;

import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumProcess;
import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumProject;
import br.nemo.immigrant.ontology.entity.scrumprocess.repositories.ScrumProcessRepository;
import br.nemo.immigrant.ontology.entity.scrumprocess.repositories.ScrumProjectRepository;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ScrumProjectService {


    @Autowired
    private ScrumProjectRepository repository;

    @Autowired
    private ScrumProcessRepository scrumprocessRepository;

    public void process(ConsumerRecord<String, String> payload,Mapper<ScrumProject> mapper) {

        try{

            ScrumProject scrumproject = mapper.map(payload.value());

            this.save(scrumproject);

        }catch (Exception e ){
            log.error(e.getMessage());
        }
    }

    private void save (ScrumProject scrumproject) {

        ScrumProcess scrumprocess = ScrumProcess.builder().build();
        scrumprocess = this.scrumprocessRepository.save(scrumprocess);
        scrumproject.setScrumprocess(scrumprocess);


        this.repository.save(scrumproject);

        log.info("Saving");
    }

}
