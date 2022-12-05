package com.damir.healthcare.services;

import com.damir.healthcare.entities.Disease;
import com.damir.healthcare.entities.Reviewing;
import com.damir.healthcare.repositories.DiseaseRepository;
import com.damir.healthcare.repositories.RecordRepository;
import com.damir.healthcare.repositories.ReviewingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DiseaseService {
    final
    ReviewingRepository reviewingRepository;
    final
    DiseaseRepository diseaseRepository;
    final
    RecordRepository recordRepository;

    public DiseaseService(ReviewingRepository reviewingRepository, DiseaseRepository diseaseRepository, RecordRepository recordRepository) {
        this.reviewingRepository = reviewingRepository;
        this.diseaseRepository = diseaseRepository;
        this.recordRepository = recordRepository;
    }

    public void saveNewDisease(Reviewing dis) {
        dis.setAuthorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        dis.setAction("add");
        dis.setReviewerEmailOne("");
        dis.setReviewerEmailTwo("");
        reviewingRepository.save(dis);
    }

    @Transactional
    public void approve(Reviewing r) {
        String curuser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (r.getReviewerEmailOne()==null || r.getReviewerEmailOne().equals("")) {
            r.setReviewerEmailOne(curuser);
            return ;
        }
        r.setReviewerEmailTwo(curuser);
        if (r.getAction().equals("delete")) {
            diseaseRepository.deleteById(r.getOriginalId());
            reviewingRepository.deleteById(r.getId());
            return ;
        }
        Disease dis;
        if (r.getAction().equals("add")) {
            dis = new Disease();
            dis.setReviewerEmailOne(r.getReviewerEmailOne());
            dis.setReviewerEmailTwo(r.getReviewerEmailTwo());
            dis.setAuthorEmail(r.getAuthorEmail());
            dis.setDescription(r.getDescription());
            dis.setPathogen(r.getPathogen());
            dis.setDiseaseCode(r.getDiseaseCode());
            diseaseRepository.save(dis);
            reviewingRepository.deleteById(r.getId());
            return ;
        }
        dis = diseaseRepository.findById(r.getOriginalId()).get();
        if (!dis.getDiseaseCode().equals(r.getDiseaseCode())) {
            recordRepository.updateDisCode(dis.getDiseaseCode(), r.getDiseaseCode());
        }
        dis.setLastModifiedBy(r.getAuthorEmail());
        dis.setDescription(r.getDescription());
        dis.setPathogen(r.getPathogen());
        dis.setDiseaseCode(r.getDiseaseCode());
        diseaseRepository.save(dis);
        reviewingRepository.deleteById(r.getId());
    }

    @Transactional
    public void deleteDisease(Integer id) {
        Disease dis = diseaseRepository.findById(id).get();
        Optional<Reviewing> r = reviewingRepository.findByOriginalIdAndAction(dis.getId(),"delete");
        if (r.isPresent()) return;
        Reviewing res = new Reviewing();
        res.setAction("delete");
        res.setDescription(dis.getDescription());
        res.setDiseaseCode(dis.getDiseaseCode());
        res.setAuthorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        res.setPathogen(dis.getPathogen());
        res.setOriginalId(dis.getId());
        reviewingRepository.save(res);
    }

    @Transactional
    public void saveModifiedDisease(Disease dis) {
        Reviewing r = new Reviewing();
        r.setAction("modify");
        r.setReviewerEmailTwo("");
        r.setReviewerEmailOne("");
        r.setDescription(dis.getDescription());
        r.setDiseaseCode(dis.getDiseaseCode());
        r.setAuthorEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        r.setPathogen(dis.getPathogen());
        r.setOriginalId(dis.getId());
        reviewingRepository.save(r);
        return ;
    }
}
