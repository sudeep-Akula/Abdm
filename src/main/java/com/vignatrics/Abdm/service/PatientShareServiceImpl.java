package com.vignatrics.Abdm.service;

import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;
import com.vignatrics.Abdm.repo.PatientShareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PatientShareServiceImpl implements PatientShareService{

    @Autowired
    PatientShareRepo patientShareRepo;

    @Override
    public String patientSahre(PatientShareDto dto) {

        String msg = "Patient Details are saved";
        if (Objects.nonNull(dto)) {
            Patientshare patientShare = new Patientshare();

            patientShare.setAbhaNumber(dto.getAbhaNumber());
            patientShare.setName(dto.getName());
            patientShare.setAbhaAddress(dto.getAbhaAddress());

            patientShareRepo.save(patientShare);

            return msg;
        }

        return "Patient Details are not saved";
    }
}
