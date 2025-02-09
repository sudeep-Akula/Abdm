package com.vignatrics.Abdm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="notify",schema = "public")
public class Notify {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String consentId;

    private String createdAt;

    private String patientId;
    @ElementCollection
    private List<CareContext> careContexts;

    private List<String> hiTypes;
}
