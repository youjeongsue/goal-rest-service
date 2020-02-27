package com.goal.restservice.repository;

import com.goal.restservice.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
  List<Follow> findByMasterId(Long masterId);

  List<Follow> findBySlaveId(Long slaveId);

  @Modifying
  @Query(
      value = "delete from follow where master_id =:master_id and slave_id =:slave_id",
      nativeQuery = true)
  void deleteFollowing(@Param("slave_id") Long slaveId, @Param("master_id") Long masterId);
}
