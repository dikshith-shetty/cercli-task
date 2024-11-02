package com.cercli.repository;

import com.cercli.entity.TimeOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeOffRepository extends JpaRepository<TimeOff, Long> {
    @Query("SELECT t FROM TimeOff t " +
            "WHERE t.employeeId = :employeeId " +
            "AND ((t.startDate <= :endDate AND t.endDate >= :startDate))")
    List<TimeOff> findOverlappingRequests(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    List<TimeOff> findByEmployeeId(Long empId);
}
