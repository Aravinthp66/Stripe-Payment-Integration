package com.example.Stripe_Integration.StripeCoreService;

import com.example.Stripe_Integration.StripeDomain.StripeCustomerImpl;
import com.example.Stripe_Integration.StripeUtils.PaymentForm;
import net.sf.json.JSONObject;


public interface StripeCoreService {

    StripeCustomerImpl createCustomer(PaymentForm paymentForm);

    JSONObject makePayment(PaymentForm paymentForm);

    JSONObject createProduct(PaymentForm paymentForm);

    JSONObject createPaymentLink(PaymentForm paymentForm);

    JSONObject retrieveAllProduct();

    JSONObject retrieveAllCharge();

}
