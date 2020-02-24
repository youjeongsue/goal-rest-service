package com.goal.restservice.repository;

import com.goal.restservice.domain.Follow;
import com.goal.restservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByMasterId(Long masterId);
    List<Follow> findBySlaveId(Long slaveId);

}
