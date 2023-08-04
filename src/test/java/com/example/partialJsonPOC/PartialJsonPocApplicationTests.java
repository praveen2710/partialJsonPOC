package com.example.partialJsonPOC;

import com.example.partialJsonPOC.Atlas.AtlasResponse;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class PartialJsonPocApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public  void convertJsonToPojo() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
		objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
		AtlasResponse response = objectMapper.readValue(new File("src/test/resources/atlasValidResponse.json"), AtlasResponse.class);
		System.out.println(response.getGtin());
		System.out.println(response.getLocation());
		System.out.println(response.getType());
	}

}
