    package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers;

    import br.nemo.immigrant.ontology.entity.scrumprocess.models.Sprint;
    import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.DateUtil;
    import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.stereotype.Component;

    import java.time.LocalDate;

    @Component
    public class Interaction2SprintMapper  implements Mapper <Sprint>{

    public Sprint map (String element) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(element);

        String name = rootNode.path("name").asText();

        String url = rootNode.path("url").asText();

        String externalid = rootNode.path("id").asText();

        String internalid = rootNode.path("internal_uuid").asText();

        LocalDate startDate = DateUtil.createLocalDate(
                rootNode.path("attributes").path("start_date").path("day").asText(),
                rootNode.path("attributes").path("start_date").path("month").asText(),
                rootNode.path("attributes").path("start_date").path("year").asText()
        );

        LocalDate endDate = DateUtil.createLocalDate(
                rootNode.path("attributes").path("finish_date").path("day").asText(),
                rootNode.path("attributes").path("finish_date").path("month").asText(),
                rootNode.path("attributes").path("finish_date").path("year").asText()
        );


        return Sprint.builder().name(name).
                                url(url).
                                startDate(startDate).
                                endDate(endDate).
                                externalId(externalid).
                                internalId(internalid).build();
    }
}
