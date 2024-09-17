package com.praveen.service;

import com.praveen.model.PlanType;
import com.praveen.model.Subscription;
import com.praveen.model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}

