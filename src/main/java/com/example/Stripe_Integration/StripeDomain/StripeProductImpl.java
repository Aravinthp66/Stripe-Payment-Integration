package com.example.Stripe_Integration.StripeDomain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Document("Stripe_Product")
public class StripeProductImpl implements StripeProduct {
    @Id
    @Field("primaryId")
    private String primaryId;

    @Field("stripe_process")
    private String stripeProcess;

    @Field("stripe_process_type")
    private String stripeProcessType;

    @Field("stripe_fee")
    private String stripeFee;

    @Field("stripe_description")
    private String description;

    @Field("stripe_period")
    private long period;

    @Field("product_id")
    private String productId;

    @Field("priceId")
    private String priceId;

    @Field("currency")
    private String currency;

    @Field("payment_link_id")
    private String paymentLinkId;

    @Field("payment_link")
    private String paymentLink;

    @Field("insertion_time")
    private Date insertionTime;

    public String getPaymentLinkId() {
        return paymentLinkId;
    }

    public void setPaymentLinkId(String paymentLinkId) {
        this.paymentLinkId = paymentLinkId;
    }

    @Override
    public String getPaymentLink() {
        return paymentLink;
    }

    @Override
    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    public String getStripeProcess() {
        return stripeProcess;
    }

    public void setStripeProcess(String stripeProcess) {
        this.stripeProcess = stripeProcess;
    }

    public String getStripeProcessType() {
        return stripeProcessType;
    }

    public void setStripeProcessType(String stripeProcessType) {
        this.stripeProcessType = stripeProcessType;
    }

    public String getStripeFee() {
        return stripeFee;
    }

    public void setStripeFee(String stripeFee) {
        this.stripeFee = stripeFee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public Date getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Date insertionTime) {
        this.insertionTime = insertionTime;
    }
}
