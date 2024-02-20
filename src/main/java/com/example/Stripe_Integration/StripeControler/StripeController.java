package com.example.Stripe_Integration.StripeControler;

import com.example.Stripe_Integration.StripeCoreService.StripeCoreService;
import com.example.Stripe_Integration.StripeDomain.StripeCustomerImpl;
import com.example.Stripe_Integration.StripeUtils.PaymentForm;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date; 

@RestController
@RequestMapping("/stripe")
public class StripeController {


    @Autowired
    StripeCoreService stripeCoreService;

    @Autowired
    StripeValidator validator;


    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){
        JSONObject json = new JSONObject();
        StripeCustomerImpl stripeCustomer = null;
        try{
            json= validator.customerValidator(paymentForm);
            if(json.size()==0) {
                stripeCustomer = stripeCoreService.createCustomer(paymentForm);
                if(stripeCustomer != null){
                    json.put("name",stripeCustomer.getStripeCustomerName());
                    json.put("phone",stripeCustomer.getPhoneNumber());
                    json.put("name",stripeCustomer.getStripeCustomerName());
                    json.put("email",stripeCustomer.getEmailId());
                    json.put("stripeCustomerId",stripeCustomer.getStripeResponseId());
                    json.put("stripeCustomerDescription",stripeCustomer.getDescription());
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @PostMapping("/makePayment")
    public ResponseEntity<String> makePayment(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){

        JSONObject json = new JSONObject();
        try{
            json= validator.makePaymentvalidator(paymentForm);

          if(json.size()==0){
              json = stripeCoreService.makePayment(paymentForm);
              //json.put("success", "product successfully created");
            }else{
              return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
          }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){

        JSONObject json = new JSONObject();

        try{
            json= validator.productValidator(paymentForm);
            if(json.size()==0){
                json= stripeCoreService.createProduct(paymentForm);
            }else{
                return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

    @PostMapping("/createPaymentLink")
    public ResponseEntity<String> createPaymentLink(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){

        JSONObject json = new JSONObject();

        try{
            json= validator.productValidator(paymentForm);
            if(json.size()==0){
                json = stripeCoreService.createPaymentLink(paymentForm);
            }else{
                return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }


    @GetMapping("/retrieveAllProduct")
    public ResponseEntity<String> retrieveAllProduct(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){
        JSONObject json = new JSONObject();
        try{
            json = stripeCoreService.retrieveAllProduct();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }


    @GetMapping("/retrieveLatestTransaction")
    public ResponseEntity<String> retrieveLatestTransaction(@ModelAttribute(value = "paymentForm") PaymentForm paymentForm){

        JSONObject json = new JSONObject();

        try{
            json = stripeCoreService.retrieveAllCharge();
            json.put("success", "product successfully created");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
    }

}
