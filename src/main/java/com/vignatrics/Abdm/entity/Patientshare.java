package com.vignatrics.Abdm.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Patientshare")
public class Patientshare {

	   private String abhaNumber;
	   private String abhaAddress;
	   private String name;
	   @Lob
	   private String responsedata;
	   @Id
	   private String request_Id;
	   private String x_Hip_Id;
	   @Lob
	   private String oauthorization;
	   private String status_Code;
	   private String response_Message;
}
