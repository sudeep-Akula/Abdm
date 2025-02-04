package com.vignatrics.Abdm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.service.PatientShareService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/ABDM")
public class CallBackAbdm {

    @Autowired
    PatientShareService patientService;

    @GetMapping("/v3/58")
    public void v358(@RequestBody HttpServletRequest req){


    }
    private final ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping("/api/v3/hip/patient/share")
    public ResponseEntity<Object> patientShare(@RequestBody Map<String, Object> jsonData,@RequestHeader("x-hip-id") String xipid) throws Exception {
            System.out.println("header values  "+xipid);
        int i=0;
        try {
            if (jsonData != null) {
                String jsonDataString = objectMapper.writeValueAsString(jsonData);
                Map<String, Object> profile = (Map<String, Object>) jsonData.get("profile");
                Map<String, Object> patient = (Map<String, Object>) profile.get("patient");

                PatientShareDto PatientSharedto = new PatientShareDto();

                PatientSharedto.setAbhaNumber((String) patient.get("abhaNumber"));
                PatientSharedto.setName((String) patient.get("name"));
                PatientSharedto.setAbhaAddress((String) patient.get("abhaAddress"));
                i = patientService.patientSahre(PatientSharedto);
                PatientSharedto.setResponsedata(jsonDataString);

                i = patientService.updatePatientShare(PatientSharedto);
                return new ResponseEntity<>(i, HttpStatus.OK);
            } else {
                return new ResponseEntity<> ("Please check your Api",HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
           return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        }


}
