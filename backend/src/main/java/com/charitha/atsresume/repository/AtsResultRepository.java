package com.charitha.atsresume.repository;

import com.charitha.atsresume.model.AtsResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtsResultRepository extends JpaRepository<AtsResultEntity, Long> {

    List<AtsResultEntity> findByResumeId(String resumeId);

    Page<AtsResultEntity> findByResumeId(String resumeId, Pageable pageable);

    Page<AtsResultEntity> findByUserId(Long userId, Pageable pageable);
}