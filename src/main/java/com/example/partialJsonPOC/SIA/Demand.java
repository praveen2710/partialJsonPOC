package com.example.partialJsonPOC.SIA;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Demand {
    String gtin14;
    int qty;
}
