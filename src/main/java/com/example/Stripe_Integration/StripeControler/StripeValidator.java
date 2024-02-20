package com.example.Stripe_Integration.StripeControler;

import com.example.Stripe_Integration.StripeUtils.PaymentForm;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class StripeValidator {

    public JSONObject customerValidator(PaymentForm paymentForm) {
        JSONObject json = new JSONObject();
        try {
            if (paymentForm != null) {
                if (paymentForm.getName() == null || paymentForm.getName().isEmpty()) {
                    json.put("emptyName", "Name cannot be empty");
                }
                if (paymentForm.getPhone() == null || paymentForm.getPhone().isEmpty()) {
                    json.put("emptyPhoneNumber", "Phone number cannot be empty");
                }
                if (paymentForm.getEmail() == null || paymentForm.getEmail().isEmpty()) {
                    json.put("emptyEmail", "Email cannot be empty");
                }
            }else{
                json.put("failure","failure");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }


    //basic validator
    public JSONObject makePaymentvalidator(PaymentForm paymentForm) {
        JSONObject json = new JSONObject();
        try {
            if (paymentForm != null) {
                if (paymentForm.getFee() == null || paymentForm.getFee().isEmpty()) {
                    json.put("emptyAmount", "Amount cannot be empty");
                }
                if (paymentForm.getCreditCardNo() == null || paymentForm.getCreditCardNo().isEmpty()) {
                    json.put("emptyCard", "Credit card cannot be empty");
                }
                if (paymentForm.getCvv() == null || paymentForm.getCvv().isEmpty()) {
                    json.put("emptyCvv", "Credit card CVV cannot be empty");
                }

                if (paymentForm.getEmail() == null || paymentForm.getEmail().isEmpty()) {
                    json.put("emptyEmail", "Please enter the valid email");
                }
                if (paymentForm.getCardExpiryMonth() == null || paymentForm.getCardExpiryMonth().isEmpty()) {
                    json.put("emptyCardExpiryMonth", "Credit card Expiry month cannot be empty");
                }
                if (paymentForm.getCardExpiryYear() == null || paymentForm.getCardExpiryYear().isEmpty()) {
                    json.put("emptyCardExpiryYear", "Credit card Expiry year cannot be empty");
                }

            }else{
                json.put("failure","failure");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    //basic validator
    public JSONObject productValidator(PaymentForm paymentForm) {
        JSONObject json = new JSONObject();
        try {
            if (paymentForm != null) {
                if (paymentForm.getFee() == null || paymentForm.getFee().isEmpty()) {
                    json.put("emptyAmount", "Amount cannot be empty");
                }
                if (paymentForm.getName() == null || paymentForm.getName().isEmpty()) {
                    json.put("emptyProductName", "Product name cannot be empty");
                }
                if (paymentForm.getDescription() == null || paymentForm.getDescription().isEmpty()) {
                    json.put("emptyDescription", "Description cannot be empty");
                }
                if (paymentForm.getPeriod() == null || paymentForm.getPeriod().equals(0)) {
                    json.put("emptyPeriod", "Period cannot be empty or 0");
                }
            }else{
                json.put("failure","failure");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }





}
