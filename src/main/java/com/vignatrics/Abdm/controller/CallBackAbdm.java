package com.vignatrics.Abdm.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vignatrics.Abdm.dto.CareContextDto;
import com.vignatrics.Abdm.dto.NotifyDto;
import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.service.NotifyService;
import com.vignatrics.Abdm.service.PatientShareService;

import jakarta.servlet.http.HttpServletRequest;

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
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @PostMapping("/api/v3/hip/patient/share")
    public ResponseEntity<Object> patientShare(@RequestBody Map<String, Object> jsonData,@RequestHeader Map<String, String> headers) throws Exception {
          //  System.out.println("header values  "+xipid);
        int i=0;
        try {
            if (jsonData != null && headers!=null) {
                String jsonDataString = objectMapper.writeValueAsString(jsonData);
                Map<String, Object> profile = (Map<String, Object>) jsonData.get("profile");
                Map<String, Object> patient = (Map<String, Object>) profile.get("patient");

                PatientShareDto PatientSharedto = new PatientShareDto();
                PatientSharedto.setRequest_Id(headers.get("request-id"));
                PatientSharedto.setX_Hip_Id(headers.get("x-hip-id"));
                PatientSharedto.setOauthorization(headers.get("authorization"));
                PatientSharedto.setAbhaNumber((String) patient.get("abhaNumber"));
                PatientSharedto.setName((String) patient.get("name"));
                PatientSharedto.setAbhaAddress((String) patient.get("abhaAddress"));
                PatientSharedto.setResponsedata(jsonDataString);
              //  i = patientService.patientSahre(PatientSharedto);
                i = patientService.profileOnShare(PatientSharedto);

//                i = patientService.updatePatientShare(PatientSharedto);
                return new ResponseEntity<>(i, HttpStatus.OK);
            } else {
                return new ResponseEntity<> ("Please check your Api",HttpStatus.NO_CONTENT);
            }
        }catch (Exception e){
           return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/getData/{id}")
//    public List<Patientshare> getPatientData(@PathVariable("id") String id) throws Exception {
//        return patientService.findByabhaNumber(id);
//    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request){
        return request.getSession().getId();
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
