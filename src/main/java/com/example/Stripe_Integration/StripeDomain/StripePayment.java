package com.example.Stripe_Integration.StripeDomain;

import java.util.Date;

public interface StripePayment {
    String getPrimaryId();

    void setPrimaryId(String primaryId);

    String getInvoiceId();

    void setInvoiceId(String invoiceId);

    String getChargeId();

    void setChargeId(String chargeId);

    String getUserId();

    void setUserId(String userId);

    String getTransactionType();

    void setTransactionType(String transactionType);

    String getTransactionMode();

    void setTransactionMode(String transactionMode);

    String getJoinDate();

    void setJoinDate(String joinDate);

    String getDescription();

    void setDescription(String description);

    String getAmount();

    void setAmount(String amount);

    String getCandidateName();

    void setCandidateName(String candidateName);

    String getTransactionStatus();

    void setTransactionStatus(String transactionStatus);

    String getReferenceId();

    void setReferenceId(String referenceId);

    String getProcessName();

     void setProcessName(String processName);

     String getUsedCardNumber();

     void setUsedCardNumber(String usedCardNumber);

     String getType();

     void setType(String type);

     Boolean getActive();

     void setActive(Boolean active);

     Date getInsertionTime();

     void setInsertionTime(Date insertionTime);

     Date getUpdatedTime();

     void setUpdatedTime(Date updatedTime);
}
