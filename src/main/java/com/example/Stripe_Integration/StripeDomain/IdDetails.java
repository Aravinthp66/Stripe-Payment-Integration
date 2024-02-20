package com.example.Stripe_Integration.StripeDomain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Id_Details")
public class IdDetails {

    @Id
    @Field("primaryId")
    private long primaryId;

    @Field("stripe_customer")
    private long stripeCustomer;

    @Field("stripe_product")
    private long stripeProduct;

    @Field("stripe_payment")
    private long stripePayment;

    @Field("stripe_credit_card")
    private long stripe_credit_card;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public long getStripeCustomer() {
        return stripeCustomer;
    }

    public void setStripeCustomer(long stripeCustomer) {
        this.stripeCustomer = stripeCustomer;
    }

    public long getStripeProduct() {
        return stripeProduct;
    }

    public void setStripeProduct(long stripeProduct) {
        this.stripeProduct = stripeProduct;
    }

    public long getStripePayment() {
        return stripePayment;
    }

    public void setStripePayment(long stripePayment) {
        this.stripePayment = stripePayment;
    }

    public long getStripe_credit_card() {
        return stripe_credit_card;
    }

    public void setStripe_credit_card(long stripe_credit_card) {
        this.stripe_credit_card = stripe_credit_card;
    }
}
