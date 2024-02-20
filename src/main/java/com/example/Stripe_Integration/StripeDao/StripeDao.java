package com.example.Stripe_Integration.StripeDao;

import com.example.Stripe_Integration.StripeDomain.StripeCustomerImpl;
import com.example.Stripe_Integration.StripeDomain.StripePaymentImpl;
import com.example.Stripe_Integration.StripeDomain.StripeProductImpl;

import java.util.List;

public interface StripeDao {

    StripeCustomerImpl saveStripeCustomer(StripeCustomerImpl stripeCustomer);

    StripeCustomerImpl getStripeCustomer(String email);

    StripePaymentImpl saveStripePayment(StripePaymentImpl stripePayment);

    StripeProductImpl saveStripeProduct(StripeProductImpl stripeProduct);

    List<StripeProductImpl> retrieveAllProduct();

}
