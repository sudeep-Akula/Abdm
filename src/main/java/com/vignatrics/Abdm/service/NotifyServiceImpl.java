package com.vignatrics.Abdm.service;

import com.vignatrics.Abdm.dto.NotifyDto;
import com.vignatrics.Abdm.entity.CareContext;
import com.vignatrics.Abdm.entity.Notify;
import com.vignatrics.Abdm.repo.NotifyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotifyServiceImpl implements NotifyService{

    @Autowired
    NotifyRepo notifyRepo;

    @Override
    public String notify(NotifyDto notifydto) {

        Notify notify = new Notify();
        List<CareContext> careContextLst = new ArrayList<>();

        notify.setPatientId(notifydto.getPatientId());
        notify.setConsentId(notifydto.getConsentId());
        notify.setCreatedAt(notifydto.getCreatedAt());
        notify.setHiTypes(notifydto.getHiTypes());

        careContextLst = notifydto.getCareContextsdto().stream().map(x ->{
            CareContext careContext1 = new CareContext();
            careContext1.setPatientReference(x.getPatientReference());
            careContext1.setCareContextReference(x.getCareContextReference());
            return careContext1;
        }).collect(Collectors.toList());

        notify.setCareContexts(careContextLst);

        notifyRepo.save(notify);

        return "The data is saved";
    }
}
