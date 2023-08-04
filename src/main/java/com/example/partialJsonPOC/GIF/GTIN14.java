package com.example.partialJsonPOC.GIF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GTIN14 {
    private String GTIN14;
    private int qty;
}
