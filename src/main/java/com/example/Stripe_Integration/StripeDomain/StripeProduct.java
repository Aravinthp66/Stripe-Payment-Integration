package com.example.Stripe_Integration.StripeDomain;

import java.util.Date;

public interface StripeProduct {

    String getPrimaryId();

    void setPrimaryId(String primaryId);

    String getStripeProcess();

    void setStripeProcess(String stripeProcess);

    String getStripeProcessType();

    void setStripeProcessType(String stripeProcessType);

    String getStripeFee();

    void setStripeFee(String stripeFee);

    String getDescription();

    void setDescription(String description);

    long getPeriod();

    void setPeriod(long period);

    String getProductId();

    void setProductId(String productId);

    String getPriceId();

    void setPriceId(String priceId);

    String getCurrency();

    void setCurrency(String currency);

    Date getInsertionTime();

    void setInsertionTime(Date insertionTime);

    void setPaymentLink(String paymentLink);

    String getPaymentLink();

    String getPaymentLinkId();

    void setPaymentLinkId(String paymentLinkId);
}
