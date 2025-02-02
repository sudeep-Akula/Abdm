package com.vignatrics.Abdm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Patientshare")
public class Patientshare {
    @Id
   private String abhaNumber;
   private String abhaAddress;
   private String name;

}
