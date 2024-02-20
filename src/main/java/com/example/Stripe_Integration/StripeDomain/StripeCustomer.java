package com.example.Stripe_Integration.StripeDomain;

import java.util.Date;

public interface StripeCustomer {

    String getStripeCustomerName();

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    void setStripeCustomerName(String stripeCustomerName);

    String getStripeCustomerId();

    void setStripeCustomerId(String stripeCustomerId);

    String getStripeResponseId();

    void setStripeResponseId(String stripeResponseId);

    String getObject();

    void setObject(String object);

    Boolean getLiveMode();

    void setLiveMode(Boolean liveMode);

    Date getCreatedTimeStamp();

    void setCreatedTimeStamp(Date createdTimeStamp);

    Integer getAccountBalance();

    void setAccountBalance(Integer integer);

    String getCurrency();

    void setCurrency(String currency);

    String getDefaultSource();

    void setDefaultSource(String defaultSource);

    Boolean getDelinquent();

    void setDelinquent(Boolean delinquent);

    String getDescription();

    void setDescription(String description);

    String getEmailId();

    void setEmailId(String emailId);

    String getUserId();

    void setUserId(String userId);

    String getUserType();

    void setUserType(String userType);

    public Date getInsertionTime();

    public void setInsertionTime(Date insertionTime);


    void cleanUp();
}
