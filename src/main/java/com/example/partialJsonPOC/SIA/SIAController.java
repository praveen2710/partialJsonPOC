package com.example.partialJsonPOC.SIA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class SIAController {

    @Autowired
    MockCassandraCall cassandraCall;

    @Autowired
    ObjectMapper mapper;

    @GetMapping("/sia")
    public String vizpickWorkflow() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String gifUrl = "http://localhost:8080/gif";
        ResponseEntity<String> response = restTemplate.getForEntity(gifUrl, String.class);
//        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = extractPassThroughJsonContent(response.getBody());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // this will ignore other fields in json
        GIFDemand demand = mapper.readValue(response.getBody(), GIFDemand.class);
        List<GtinTags> tagsForGtins = cassandraCall.findTagsForGtins(demand);
        ((ObjectNode)node).putPOJO("pick",tagsForGtins);
        return mapper.writeValueAsString(node);
    }

    private JsonNode extractPassThroughJsonContent(String response) throws JsonProcessingException {
        JsonNode node = mapper.readTree(response);
        ((ObjectNode) node).remove("demand");
        return node;
    }
}
