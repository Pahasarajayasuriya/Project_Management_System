package com.pms.projectmanagementsystem.service;

import com.pms.projectmanagementsystem.model.PlanType;
import com.pms.projectmanagementsystem.model.Subscription;
import com.pms.projectmanagementsystem.model.User;
import com.pms.projectmanagementsystem.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Autowired
    private UserService userService;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setPlanType(PlanType.FREE);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);

        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription getUsersSubscription(Long userId) throws Exception {
        Subscription subscription= subscriptionRepository.findByUserId(userId);
        if(!isValid(subscription)){
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long userId, PlanType planType) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANNUALLY)){
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }else if(planType.equals(PlanType.MONTHLY)){
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if(subscription.getPlanType().equals(PlanType.FREE)){
            return true;
        }
        LocalDate endDate= subscription.getSubscriptionEndDate();
        LocalDate currentDate = LocalDate.now();


        return endDate.isAfter(currentDate) || endDate.isEqual(currentDate);
    }
}
