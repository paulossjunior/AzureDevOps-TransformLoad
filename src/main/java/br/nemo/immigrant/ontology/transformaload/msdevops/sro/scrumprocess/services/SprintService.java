
package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.services;

import br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.exception.ScrumProcessExceptionNotFound;
import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumProcess;
import br.nemo.immigrant.ontology.entity.scrumprocess.models.Sprint;
import br.nemo.immigrant.ontology.entity.scrumprocess.repositories.ScrumProcessRepository;
import br.nemo.immigrant.ontology.entity.scrumprocess.repositories.SprintRepository;
import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SprintService {


    @Autowired
    private SprintRepository repository;

    @Autowired
    private ScrumProcessRepository scrumProcessRepository;


    @Autowired
    private ScrumProjectService scrumProjectService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void process(ConsumerRecord<String, String> payload,Mapper<Sprint> mapper) {

        try{
            String element = payload.value();

            Sprint sprint = mapper.map(element);

            //Buscar o ScrumProcess baseado no Scrum Project
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(element);
            String externalId = rootNode.path("additional_properties").path("project").path("id").asText();

            /*
            ScrumProcess scrumProcess = scrumProcessRepository.findByscrumprojectExternalId(externalId);

            if (scrumProcess == null){
                log.error ("Scrum Process Not Found");
                throw new ScrumProcessExceptionNotFound();
            }

            // isso é requerido
            sprint.setScrumprocess(scrumProcess);
            */
            this.save(sprint);

        }
        catch (ScrumProcessExceptionNotFound  e){
            log.error("Sprintservice "+e.getMessage());

            kafkaTemplate.send("application.msazuredevops.interaction", payload.value());
        }

        catch (Exception e ){
            log.error("Exception type: " + e.getClass().getName());
            log.error(e.getMessage());
        }
    }



    private void save (Sprint sprint) {

        this.repository.save(sprint);

        log.info("Saving");
    }

}
