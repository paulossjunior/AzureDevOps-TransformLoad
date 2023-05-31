    package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers;

    import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumProject;
    import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class Project2ScrumProjectMapper  implements Mapper <ScrumProject>{

    public ScrumProject map (String element) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(element);

        String name = rootNode.path("name").asText();

        String description = rootNode.path("description").asText();

        String externalid = rootNode.path("id").asText();

        String internalid = rootNode.path("internal_uuid").asText();

        String url = rootNode.path("url").asText();


        return ScrumProject.builder().name(name).
                                      description(description).
                                      externalId(externalid).
                                      internalId(internalid).
                                      url(url).build();
    }
}
