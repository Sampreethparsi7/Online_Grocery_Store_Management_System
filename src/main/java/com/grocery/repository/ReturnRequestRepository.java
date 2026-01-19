package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.ReturnRequest;

public interface ReturnRequestRepository extends JpaRepository<ReturnRequest, Long> {
    boolean existsByOrderOrderIdAndStatusIn(Long orderId, List<String> statuses);
    List<ReturnRequest> findByPickupExecutiveIsNull();
    List<ReturnRequest> findByPickupExecutiveIsNotNull();
}