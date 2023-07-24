package com.example.partialJsonPOC.SIA;

import com.example.partialJsonPOC.SIA.Demand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GIFDemand {
    List<Demand> demand;
}
