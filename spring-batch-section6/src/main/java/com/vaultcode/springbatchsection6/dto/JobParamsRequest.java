package com.vaultcode.springbatchsection6.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobParamsRequest {
    private String paramKey;
    private String paramValue;
}
