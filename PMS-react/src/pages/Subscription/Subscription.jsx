import React from "react";
import SubscriptionCard from "./SubscriptionCard";
import { useSelector } from "react-redux";

const Subscription = () => {
  const {subscription} =useSelector(store=>store)
  const paidPlan = [
    "Add unlimited projects",
    "Access to live chat",
    "Add unlimited team members",
    "Advanced Reporting",
    "Priority support",
    "Customization options",
    "Intergration support",
    "Advanced security",
    "Training and Resources",
    "Access control",
    "Custom workflows",
  ];
  const annualPlan = [
    "Add unlimited projects",
    "Access to live chat",
    "Add unlimited team members",
    "Advanced Reporting",
    "Priority support",
    "Everything which monthly plan has",
  ];
  const freePlan = [
    "Add only 3 projects",
    "Basic Task Management",
    "Projet Collaboration",
    "Basic Reporting",
    "Email Notifications",
    "Basic access control",
  ];
  return (
    <div className="p-10">
      <h1 className="text-5xl font-semibold py-5 pb-16 text-center">Pricing</h1>
      <div className="flex flex-col lg:flex-row justify-center items-center gap-20">
        <SubscriptionCard
          data={{
            planName: "Free",
            features: freePlan,
            planType: "FREE",
            price: 0,
            buttonName: subscription.userSubcription?.planType=="FREE" ? "Current Plan" : "Get Started",
          }}
        />
        <SubscriptionCard data={{
            planName: "Monthly Paid Plan",
            features: paidPlan,
            planType: "MONTHLY",
            price: 9.62,
            buttonName: subscription.userSubcription?.planType=="MONTHLY"  ? "Current Plan" : "Get Started",
          }} />
        <SubscriptionCard data={{
            planName: "Annual Paid Plan",
            features: annualPlan,
            planType: "ANNUAL",
            price: 80.80,
            buttonName: subscription.userSubcription?.planType=="ANNUAL"  ? "Current Plan" : "Get Started",
          }}/>
      </div>
    </div>
  );
};

export default Subscription;
