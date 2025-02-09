package com.vignatrics.Abdm.service;

import com.vignatrics.Abdm.dto.PatientShareDto;
import com.vignatrics.Abdm.entity.Patientshare;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PatientShareService {

    public int patientSahre(PatientShareDto dto) throws Exception;
    public int updatePatientShare(PatientShareDto dto) throws Exception;

//    public List<Patientshare> findByabhaNumber(String id) throws Exception;

    int profileOnShare(PatientShareDto patientsharedto) throws Exception;
}

