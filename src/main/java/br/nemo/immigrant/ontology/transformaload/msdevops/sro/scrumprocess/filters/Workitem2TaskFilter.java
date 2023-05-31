    package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.filters;

    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class Workitem2TaskFilter {

    public Boolean isValid (String element) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(element);

        return (rootNode.path("fields").path("System.WorkItemType").asText().equals("Task")
                )? true : false;


    }
}
