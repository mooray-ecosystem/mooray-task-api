package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.entity.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
