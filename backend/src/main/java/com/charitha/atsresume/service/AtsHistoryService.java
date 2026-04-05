package com.charitha.atsresume.service;

import com.charitha.atsresume.model.AtsResultEntity;
import com.charitha.atsresume.repository.AtsResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class AtsHistoryService {

    @Autowired
    private AtsResultRepository atsResultRepository;

    public List<AtsResultEntity> getAllHistory() {
        return atsResultRepository.findAll();
    }

    public List<AtsResultEntity> getHistoryByResumeId(String resumeId) {
        return atsResultRepository.findByResumeId(resumeId);
    }

    public Page<AtsResultEntity> getAllHistory(Pageable pageable) {
        return atsResultRepository.findAll(pageable);
    }

    public Page<AtsResultEntity> getHistoryByResumeId(String resumeId, Pageable pageable) {
        return atsResultRepository.findByResumeId(resumeId, pageable);
    }

    public Page<AtsResultEntity> getHistoryByUserId(Long userId, Pageable pageable) {
        return atsResultRepository.findByUserId(userId, pageable);
    }
}