package com.vignatrics.Abdm.service;

import com.vignatrics.Abdm.dto.PatientShareDto;

public interface PatientShareService {

    public int patientSahre(PatientShareDto dto) throws Exception;
    public int updatePatientShare(PatientShareDto dto) throws Exception;
}


