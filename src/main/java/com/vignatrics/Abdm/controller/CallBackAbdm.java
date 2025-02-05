package com.vignatrics.Abdm.controller;

import com.vignatrics.Abdm.dto.CareContextDto;
import com.vignatrics.Abdm.dto.NotifyDto;
import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;
import com.vignatrics.Abdm.service.NotifyService;
import com.vignatrics.Abdm.service.PatientShareService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/ab")
public class CallBackAbdm {

    @Autowired
    PatientShareService patientService;

    @Autowired
    NotifyService notifyService;

    @GetMapping("/v3/58")
    public void v358(@RequestBody HttpServletRequest req){


    }
    @PostMapping("/api/v3/hip/patient/share")
    public String patientShare(@RequestBody Map<String, Object> jsonData) {

        if (jsonData != null) {
            // Extract fields from JSON data
            Map<String, Object> profile = (Map<String, Object>) jsonData.get("profile");
            Map<String, Object> patient = (Map<String, Object>) profile.get("patient");

            //String abhaNumber = (String) patient.get("abhaNumber");
            //String name = (String) patient.get("name");
            PatientShareDto PatientSharedto = new PatientShareDto();

            //PatientSharedto.setName((String) jsonData.get("field 9"));
            PatientSharedto.setAbhaNumber((String) patient.get("abhaNumber"));
            PatientSharedto.setName((String) patient.get("name"));
            PatientSharedto.setAbhaAddress((String) patient.get("abhaAddress"));

            patientService.patientSahre(PatientSharedto);
        }


        return "patient details arenot saved";
    }

    @PostMapping("/api/v3/consent/request/hip/notify")
    public String notify(@RequestBody Map<String, Object> jsonData) {

        Map<String, Object> notification = (Map<String, Object>) jsonData.get("notification");
        Map<String, Object> consentDetail = (Map<String, Object>) notification.get("consentDetail");
        Map<String, Object> patient = (Map<String, Object>) consentDetail.get("patient");
        List<Map<String, String>> careContexts = (List<Map<String, String>>) consentDetail.get("careContexts");

        NotifyDto dto = new NotifyDto();
        dto.setConsentId((String) consentDetail.get("consentId"));
        dto.setHiTypes((List<String>) consentDetail.get("hiTypes"));
        dto.setCreatedAt((String) consentDetail.get("createdAt"));
        dto.setPatientId((String) patient.get("id"));

        List<CareContextDto> careContextLst = careContexts.stream().map(x -> {
            CareContextDto CareCondto = new CareContextDto();
            CareCondto.setPatientReference(x.get("patientReference"));
            CareCondto.setCareContextReference(x.get("careContextReference"));
            return CareCondto;
        }).collect(Collectors.toList());
        dto.setCareContextsdto(careContextLst);

        notifyService.notify(dto);

        return null;
    }

}
