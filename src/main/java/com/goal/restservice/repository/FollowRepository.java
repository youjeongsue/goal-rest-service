package com.goal.restservice.repository;

import com.goal.restservice.domain.Follow;
import com.goal.restservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
