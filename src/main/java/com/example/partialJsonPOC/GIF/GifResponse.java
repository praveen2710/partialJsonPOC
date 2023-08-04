package com.example.partialJsonPOC.GIF;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GifResponse {
    List<GTIN14> demand;
    boolean isPaginated;
    String uuid;
}
