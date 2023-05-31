    package br.nemo.immigrant.ontology.transformaload.msdevops.sro.scrumprocess.mappers;

    import br.nemo.immigrant.ontology.entity.scrumprocess.models.ScrumDevelopmentTask;
    import br.nemo.immigrant.ontology.transformaload.msdevops.sro.util.Mapper;
    import com.fasterxml.jackson.databind.JsonNode;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class Workitem2TaskMapper  implements Mapper <ScrumDevelopmentTask>{

    public ScrumDevelopmentTask map (String element) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode rootNode = objectMapper.readTree(element);

        String externalid = rootNode.path("id").asText();

        String internalid = rootNode.path("internal_uuid").asText();

        String name = rootNode.path("fields").path("System.Title").asText();

        String createdDate = rootNode.path("fields").path("System.CreatedDate").asText();

        String createdBy = rootNode.path("fields").path("System.CreatedBy").asText();

        String activatedBy = rootNode.path("fields").path("Microsoft.VSTS.Common.ActivatedBy").asText();

        String activatedDate = rootNode.path("fields").path("Microsoft.VSTS.Common.ActivatedDate").asText();

        String revisedDate = rootNode.path("fields").path("System.RevisedDate").asText();

        String stateChangeDate = rootNode.path("fields").path("System.RevisedDate").asText();

        // Sprint
        String iteration = rootNode.path("fields").path("Microsoft.VSTS.Common.StateChangeDate").asText();

        //User Story ou EPIC
        String parent = rootNode.path("fields").path("System.Parent").asText();

        //Tipo de tarefa

        String activity = rootNode.path("fields").path("Microsoft.VSTS.Common.Activity").asText();

        String priority = rootNode.path("fields").path("Microsoft.VSTS.Common.Priority").asText();

        //Estado
        String state = rootNode.path("fields").path("System.State").asText();



        String tags = rootNode.path("fields").path("System.Tags").asText();

        return ScrumDevelopmentTask.builder().
                externalId(externalid).
                internalId(internalid).
                name(name).
                build();
    }
}
