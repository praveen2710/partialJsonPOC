package com.example.partialJsonPOC.SIA;

import com.example.partialJsonPOC.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GtinTags {

    private int demandQty;
    @JsonIgnore
    private String gtin14;
    private List<List<Tag>> tags;
}
