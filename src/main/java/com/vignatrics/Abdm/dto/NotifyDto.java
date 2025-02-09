package com.vignatrics.Abdm.dto;


import com.vignatrics.Abdm.entity.CareContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotifyDto {

    private String consentId;

    private String createdAt;

    private String patientId;
    private List<CareContextDto> careContextsdto;
    private List<String> hiTypes;
}
