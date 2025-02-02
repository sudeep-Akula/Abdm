package com.vignatrics.Abdm.controller;

import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;
import com.vignatrics.Abdm.service.PatientShareService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/ab")
public class CallBackAbdm {

    @Autowired
    PatientShareService patientService;

    @GetMapping("/v3/58")
    public void v358(@RequestBody HttpServletRequest req){


    }
    @PostMapping("/api/v3/hip/patient/share")
    public String patientShare(@RequestBody Map<String, Object> jsonData){

if (jsonData != null){
    // Extract fields from JSON data
    Map<String, Object> profile = (Map<String, Object>) jsonData.get("profile");
    Map<String, Object> patient = (Map<String, Object>) profile.get("patient");

    //String abhaNumber = (String) patient.get("abhaNumber");
    //String name = (String) patient.get("name");
    PatientShareDto  PatientSharedto = new PatientShareDto();

    //PatientSharedto.setName((String) jsonData.get("field 9"));
    PatientSharedto.setAbhaNumber((String) patient.get("abhaNumber"));
    PatientSharedto.setName((String) patient.get("name"));
    PatientSharedto.setAbhaAddress((String) patient.get("abhaAddress"));

    patientService.patientSahre(PatientSharedto);
}


return  "patient details arenot saved";
    }
}
