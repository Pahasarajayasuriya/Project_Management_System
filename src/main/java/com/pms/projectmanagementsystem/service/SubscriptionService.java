package com.pms.projectmanagementsystem.service;

import com.pms.projectmanagementsystem.model.PlanType;
import com.pms.projectmanagementsystem.model.Subscription;
import com.pms.projectmanagementsystem.model.User;

import java.util.concurrent.Flow;

public interface SubscriptionService {
    Subscription createSubscription(User user);
    Subscription getUsersSubscription(Long userId) throws Exception;
    Subscription updateSubscription(Long userId, PlanType planType);
    boolean isValid(Subscription subscription);
}
