package com.vignatrics.Abdm.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;
import com.vignatrics.Abdm.repo.PatientShareRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import okhttp3.*;

@Service
public class PatientShareServiceImpl implements PatientShareService {

    @Autowired
    PatientShareRepo patientShareRepo;

    @Override
    public int patientSahre(PatientShareDto dto) throws Exception {
        int i = 0;
        try {
            if (Objects.nonNull(dto)) {
                Patientshare patientShare = new Patientshare();
                patientShare.setAbhaNumber(dto.getAbhaNumber());
                patientShare.setName(dto.getName());
                patientShare.setAbhaAddress(dto.getAbhaAddress());
                patientShare.setResponsedata(dto.getResponsedata());
                patientShare.setOauthorization(dto.getOauthorization());
                patientShare.setX_Hip_Id(dto.getX_Hip_Id());
                patientShare.setRequest_Id(dto.getRequest_Id());
                patientShare.setStatus_Code(dto.getStatus_Code());
                patientShare.setResponse_Message(dto.getResponse_Message());
                patientShareRepo.save(patientShare);

                i = 1;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return i;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public int updatePatientShare(PatientShareDto dto) throws Exception {

        try {
            Patientshare patientshare = entityManager.find(Patientshare.class, dto.getRequest_Id());
            if (patientshare != null) {
                patientshare.setStatus_Code(dto.getStatus_Code());
                patientshare.setResponse_Message(dto.getResponse_Message());
                entityManager.merge(patientshare);
                return 1;
            } else {
                throw new Exception("No Records Updated");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public String generateUniqueID() { // These is For Generate UUID
        return UUID.randomUUID().toString();
    }

    public String generateTimeStamp() { // These is for generate TimeStamp
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .withZone(ZoneOffset.UTC)
                .format(Instant.now());
    }


    @Override
    public int profileOnShare(PatientShareDto patientsharedto) throws Exception {
        // new code
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        String jsonBody = "{\r\n    \"acknowledgement\": {\r\n        \"status\": \"SUCCESS\",\r\n        \"abhaAddress\": \"" + patientsharedto.getAbhaAddress() + "\",\r\n        \"profile\": {\r\n            \"context\": \"5\",\r\n            \"tokenNumber\": \"15\",\r\n            \"expiry\": \"1800\"\r\n        }\r\n    },\r\n    \"response\": {\r\n        \"requestId\": \"" + patientsharedto.getRequest_Id() + "\"\r\n    }\r\n}";
        RequestBody body = RequestBody.create(mediaType, jsonBody);
        Request request = new Request.Builder()
                .url("https://dev.abdm.gov.in/api/hiecm/patient-share/v3/on-share")
                .method("POST", body)
                .addHeader("REQUEST-ID", generateUniqueID())
                .addHeader("TIMESTAMP", generateTimeStamp())
                .addHeader("X-CM-ID", "sbx")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", patientsharedto.getOauthorization())
                .build();
        Response response = client.newCall(request).execute();
        patientsharedto.setStatus_Code(String.valueOf(response.code()));
        patientsharedto.setResponse_Message(response.message());
        response.close();
        return patientSahre(patientsharedto);

        // ended here

    }

//    @Override
//    public List findByabhaNumber(String id) throws Exception {
//        return List.of(patientShareRepo.findById(id));
//    }

}