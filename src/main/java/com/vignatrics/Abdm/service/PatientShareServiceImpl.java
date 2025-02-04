package com.vignatrics.Abdm.service;

import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;
import com.vignatrics.Abdm.repo.PatientShareRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PatientShareServiceImpl implements PatientShareService{

    @Autowired
    PatientShareRepo patientShareRepo;

    @Override
    public int patientSahre(PatientShareDto dto) throws Exception {
        int i=0;
        try {
            if (Objects.nonNull(dto)) {
                Patientshare patientShare = new Patientshare();
                patientShare.setAbhaNumber(dto.getAbhaNumber());
                patientShare.setName(dto.getName());
                patientShare.setAbhaAddress(dto.getAbhaAddress());
                //patientShare.setResponsedata(dto.getResponsedata());
                patientShareRepo.save(patientShare);

                i= 1;
            }
        }catch (Exception e){
           throw  new Exception(e.getMessage());
        }
        return i;
    }

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public int updatePatientShare(PatientShareDto dto) throws Exception {

        try {
            Patientshare patientshare = entityManager.find(Patientshare.class, dto.getAbhaNumber());
            if (patientshare != null) {
                patientshare.setResponsedata(dto.getResponsedata());
                entityManager.merge(patientshare);
                return 1;
            } else {
               throw new Exception("No Records Updated");
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
