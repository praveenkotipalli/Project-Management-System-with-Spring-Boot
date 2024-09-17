package com.praveen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

    Subscription findByUserId(Long userId);
}