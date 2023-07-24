package com.example.partialJsonPOC;

import com.example.partialJsonPOC.SIA.GIFDemand;
import com.example.partialJsonPOC.SIA.GtinTags;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) throws IOException {
        String gifResponseString  = callDummyGIF();
        System.out.println(gifResponseString);
        vizpickToSIAToGIF(gifResponseString);
    }

    public static String  callDummyGIF() throws IOException {
        URL url = new URL("http://localhost:8080/gif");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        int status = con.getResponseCode();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }

    private static void vizpickToSIAToGIF(String gifResponseJson) throws IOException {
//        String data = new String(Files.readAllBytes(Paths.get("/Users/praveen/IdeaProjects/partialJsonPOC/src/main/java/com/example/partialJsonPOC/sampleResponse.json")));
        GIFDemand demand = extractDemandFromResponse(gifResponseJson);
//        System.out.println(demand.getDemand().get(0).getGtin14());
        String newResp = extractNonDemandSectionsFromResponse(gifResponseJson);
//        System.out.println(newResp);
        List<GtinTags> tagsForGtins = dummyDBResponse(demand);
        String vizpickResp = serializedResponse(tagsForGtins,newResp);
        System.out.println(vizpickResp);
    }

    private static List<GtinTags> dummyDBResponse(GIFDemand demand){
        GtinTags gtinTagsOne = GtinTags.builder()
                .gtin14("234234")
                .demandQty(14)
                .tags(Arrays.asList(Arrays.asList(Tag.builder().tag("T6465").qty(12).build(),Tag.builder().tag("T9323").qty(4).build())))
                .build();

        return  Arrays.asList(gtinTagsOne);
    }

    private static String serializedResponse(List<GtinTags> tagsForGtins,String passThrough) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(passThrough);
        ((ObjectNode)node).put("pick",objectMapper.writeValueAsString(tagsForGtins));
        return objectMapper.writeValueAsString(node);
    }

    private static GIFDemand extractDemandFromResponse(String jsonResp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // this will ignore other fields in json
        GIFDemand demand = objectMapper.readValue(jsonResp, GIFDemand.class);
        return demand;
    }

    private static String extractNonDemandSectionsFromResponse(String jsonResp) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readTree(jsonResp);
        ((ObjectNode) node).remove("demand");
        return objectMapper.writeValueAsString(node);
    }
}
