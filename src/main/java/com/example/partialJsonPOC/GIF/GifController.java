package com.example.partialJsonPOC.GIF;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.UUID;

@RestController
public class GifController {

    @GetMapping("/gif")
    public GifResponse gifDemand() {
        GifResponse response = GifResponse.builder()
                .demand(Arrays.asList(
                            GTIN14.builder().GTIN14("234234").qty(14).build(),
                            GTIN14.builder().GTIN14("23413").qty(23).build(),
                            GTIN14.builder().GTIN14("3413").qty(4).build(),
                            GTIN14.builder().GTIN14("764453").qty(10).build()
                        )
                )
                .isPaginated(false)
                .uuid(UUID.randomUUID().toString())
                .build();
        return response;
    }
}
