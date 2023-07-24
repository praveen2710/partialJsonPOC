package com.example.partialJsonPOC.SIA;

import com.example.partialJsonPOC.Tag;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class MockCassandraCall {

    public List<GtinTags> findTagsForGtins(GIFDemand demand){
        GtinTags gtinTagsOne = GtinTags.builder()
                .gtin14("234234")
                .demandQty(14)
                .tags(Arrays.asList(Arrays.asList(Tag.builder().tag("T6465").qty(12).build(),Tag.builder().tag("T9323").qty(4).build())))
                .build();

        GtinTags gtinTagsTwo = GtinTags.builder()
                .gtin14("3413")
                .demandQty(4)
                .tags(Arrays.asList(Arrays.asList(Tag.builder().tag("T1246").qty(2).build(),Tag.builder().tag("T431").qty(2).build())))
                .build();


        GtinTags gtinTagsThree = GtinTags.builder()
                .gtin14("764453")
                .demandQty(10)
                .tags(Arrays.asList(Arrays.asList(Tag.builder().tag("T945").qty(3).build())))
                .build();

        return  Arrays.asList(gtinTagsOne,gtinTagsTwo,gtinTagsThree);
    }
}
