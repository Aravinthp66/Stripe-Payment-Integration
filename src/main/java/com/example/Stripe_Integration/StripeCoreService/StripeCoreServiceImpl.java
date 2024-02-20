package com.example.Stripe_Integration.StripeCoreService;

import com.example.Stripe_Integration.StripeDao.StripeDao;
import com.example.Stripe_Integration.StripeDomain.StripeCustomerImpl;
import com.example.Stripe_Integration.StripeDomain.StripePaymentImpl;
import com.example.Stripe_Integration.StripeDomain.StripeProductImpl;
import com.example.Stripe_Integration.StripeUtils.PaymentForm;
import com.stripe.Stripe;
import com.stripe.model.*;
import com.stripe.param.*;
import com.stripe.model.PaymentLink;
import com.stripe.param.PaymentLinkCreateParams;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeCoreServiceImpl implements StripeCoreService {

    private static final Log LOG = LogFactory.getLog(StripeCoreServiceImpl.class);

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    StripeDao stripeDao;

    public JSONObject makePayment(PaymentForm paymentForm){
        Stripe.apiKey = stripeApiKey;
        JSONObject jsonObject = new JSONObject();
        StripeCustomerImpl stripeCustomer = null;
        Customer customerObj = null;
        Long chargingAmount = null;
        Charge charge = null;
        StripePaymentImpl stripePayment = null;

        try {
            stripeCustomer = stripeDao.getStripeCustomer(paymentForm.getEmail());

            if(stripeCustomer == null){
                stripeCustomer= createCustomer(paymentForm);
            }
                customerObj = Customer.retrieve(stripeCustomer.getStripeResponseId());
                jsonObject = createCardToken(paymentForm,customerObj);
                chargingAmount = Long.parseLong(paymentForm.getFee()) * 100;

                if (customerObj != null && customerObj.getId() != null && jsonObject.containsKey("success")) {
                    LOG.info(" ------------------ apikey ----------------"+  stripeApiKey);
                    ChargeCreateParams params =
                            ChargeCreateParams.builder()
                                    .setAmount(chargingAmount)
                                    .setCurrency("usd")
                                    .setCustomer(customerObj.getId())
                                    .setSource((String)jsonObject.get("card"))
                                    .build();
                    charge = Charge.create(params);
                    if (charge != null) {
                        stripePayment = new StripePaymentImpl();
                        stripePayment.setAmount(paymentForm.getFee());
                        stripePayment.setDescription(charge.getDescription());
                        stripePayment.setChargeId(charge.getId());
                        stripePayment.setInsertionTime(new Date());
                        stripeDao.saveStripePayment(stripePayment);
                        jsonObject.put("success", charge);
                    }
                    else
                        jsonObject.put("failed", "Charged amount failed");
                }else{
                    jsonObject.put("failed", "payment failure");
                }
        }catch(Exception e){
            LOG.error(" ------------------ Exception in makePayment method  ----------------"+  e.getMessage());
        }
        return jsonObject;
    }

    public StripeCustomerImpl createCustomer(PaymentForm paymentForm) {
        Stripe.apiKey = stripeApiKey;
        Customer customerObj = null;
        StripeCustomerImpl stripeCustomer = null;
        Map<String, Object> customer = new HashMap<String, Object>();

        try {
            if (paymentForm != null) {
                //create new customer
                customer.put("description", "candidateEmail" + " " + paymentForm.getEmail());
                customer.put("email", paymentForm.getEmail());
                customer.put("name", paymentForm.getName());
                customerObj = Customer.create(customer);
                if (customerObj != null) {
                    stripeCustomer = new StripeCustomerImpl();
                    stripeCustomer.setStripeCustomerName(paymentForm.getName());
                    stripeCustomer.setPhoneNumber(paymentForm.getPhone());
                    stripeCustomer.setEmailId(paymentForm.getEmail());
                    stripeCustomer.setStripeResponseId(customerObj.getId());                    ;
                    stripeCustomer.setDefaultSource(customerObj.getDefaultSource());
                    stripeCustomer.setInsertionTime(new Date());
                    stripeCustomer.setDescription(customerObj.getDescription());
                    stripeDao.saveStripeCustomer(stripeCustomer);
                }else{
                    LOG.error(" ------------------ Customer creation failed  ----------------");
                }

            }
        } catch (Exception e) {
            LOG.error(" ------------------ Exception in createCustomer method  ----------------"+  e.getMessage());

            e.getMessage();
            e.printStackTrace();
        } finally {
            if (customer != null) {
                customer.clear();
            }
        }
        return stripeCustomer;
    }

    private JSONObject createCardToken (PaymentForm paymentForm, Customer customer ){
        Stripe.apiKey = stripeApiKey;
        JSONObject jsonObject = new JSONObject();
        Token token = null;
        Card cardObj = null;
        try {
            LOG.info(" ------------------  createCardToken method Started  ----------------");

            TokenCreateParams params =
                    TokenCreateParams.builder()
                            .setCard(
                                    TokenCreateParams.Card.builder()
                                            .setNumber(paymentForm.getCreditCardNo())
                                            .setExpMonth(paymentForm.getCardExpiryMonth())
                                            .setExpYear(paymentForm.getCardExpiryYear())
                                            .setCvc(paymentForm.getCvv())
                                            .build()
                            )
                            .build();
            token = Token.create(params);

            if(token != null) {
                Map<String, Object> card = new HashMap<String, Object>();
                card.put("card", token.getId());
                cardObj = (Card) customer.getSources().create(card);
                if (cardObj != null) {
                    jsonObject.put("card", token.getId());
                    jsonObject.put("success", cardObj);
                } else
                    jsonObject.put("failure", "Card creation failed");
            }else{
                jsonObject.put("failure", "Card creation failed");
            }
        }catch(Exception e){
            LOG.error(" ------------------ Exception in createCardToken method  ----------------"+  e.getMessage());
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject createProduct(PaymentForm paymentForm) {
        JSONObject jsonObject = new JSONObject();
        Price price = null;
        Product product = null;
        JSONArray jsonArray = new JSONArray();
        HashMap<String , Object> hashMap = null;
        StripeProductImpl stripeProduct = null;
        try {
            hashMap = createStripeProductAndPrice(paymentForm);
            product = (Product)hashMap.get("productObject");
            price = (Price)hashMap.get("priceObject");
            if(product != null && price != null){
                stripeProduct = new StripeProductImpl();
                stripeProduct.setStripeProcess(product.getName());
                stripeProduct.setDescription(product.getDescription());
                stripeProduct.setProductId(product.getId());
                stripeProduct.setStripeFee(paymentForm.getFee());
                stripeProduct.setPeriod(paymentForm.getPeriod());
                stripeProduct.setPriceId(price.getId());
                stripeProduct.setCurrency(price.getCurrency());
                stripeProduct.setInsertionTime(new Date());
                stripeDao.saveStripeProduct(stripeProduct);
                jsonObject.put("productName",product.getName());
                jsonObject.put("productId",product.getId());
                jsonObject.put("priceId",price.getId());
                jsonObject.put("currency",price.getCurrency());
                jsonObject.put("price",paymentForm.getFee());
                jsonObject.put("productDescription",product.getDescription());
                jsonArray.add(jsonObject);
                jsonObject.clear();
                jsonObject.put("product",jsonArray);
                jsonObject.put("success","success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return jsonObject;
    }

    public HashMap createStripeProductAndPrice (PaymentForm paymentForm){
        Stripe.apiKey = stripeApiKey;
        JSONObject jsonObject = new JSONObject();
        HashMap<String , Object> hashMap = new HashMap<>();
        Product product = null;

        Price price = null;
        Long chargingAmount = null;
        try {
            ProductCreateParams productParams =
                    ProductCreateParams.builder()
                            .setName(paymentForm.getName())
                            .setDescription(paymentForm.getDescription())
                            .build();
            product = Product.create(productParams);
            System.out.println("Success! Here is your starter subscription product id: " + product.getId());
            chargingAmount = Long.parseLong(paymentForm.getFee()) * 100;
            PriceCreateParams params =
                    PriceCreateParams
                            .builder()
                            .setProduct(product.getId())
                            .setCurrency("usd")
                            .setUnitAmount(chargingAmount)
                            .setRecurring(
                                    PriceCreateParams.Recurring
                                            .builder()
                                            .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                            .setTrialPeriodDays(paymentForm.getPeriod())
                                            .build())
                            .build();
            price = Price.create(params);
            System.out.println("Success! Here is your starter subscription price id: " + price.getId());
            if(product!= null && price != null){
                hashMap.put("productObject", product);
                hashMap.put("priceObject",price);
            }

        }catch(Exception e){
            e.printStackTrace();
            LOG.error(" ------------------ Exception in createStripeProductAndPrice method  ----------------"+  e.getMessage());
        }

        return hashMap;
    }

    public JSONObject createPaymentLink (PaymentForm paymentForm){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        HashMap<String , Object> hashMap = null;
        Stripe.apiKey = stripeApiKey;
        StripeProductImpl stripeProduct = null;

        Product product = null;
        Price price = null;
        try {
            hashMap = createStripeProductAndPrice(paymentForm);
            product = (Product)hashMap.get("productObject");
            price = (Price)hashMap.get("priceObject");
            if(product != null && price != null) {
                PaymentLinkCreateParams paymentLinkparams =
                        PaymentLinkCreateParams.builder()
                                .addLineItem(
                                        PaymentLinkCreateParams.LineItem.builder()
                                                .setPrice(price.getId())
                                                .setQuantity(1L)
                                                .build()
                                )
                                .build();
                PaymentLink paymentLink = PaymentLink.create(paymentLinkparams);
                System.out.println("Success! Here is payment url: " + paymentLink.getUrl());
                if(paymentLink != null){
                    stripeProduct = new StripeProductImpl();
                    stripeProduct.setStripeProcess(product.getName());
                    stripeProduct.setDescription(product.getDescription());
                    stripeProduct.setProductId(product.getId());
                    stripeProduct.setStripeFee(paymentForm.getFee());
                    stripeProduct.setPeriod(paymentForm.getPeriod());
                    stripeProduct.setPriceId(price.getId());
                    stripeProduct.setCurrency(price.getCurrency());
                    stripeProduct.setPaymentLinkId(paymentLink.getId());
                    stripeProduct.setPaymentLink(paymentLink.getUrl());
                    stripeProduct.setInsertionTime(new Date());
                    stripeDao.saveStripeProduct(stripeProduct);

                    jsonObject.put("productName",product.getName());
                    jsonObject.put("productId",product.getId());
                    jsonObject.put("priceId",price.getId());
                    jsonObject.put("currency",price.getCurrency());
                    jsonObject.put("price",paymentForm.getFee());
                    jsonObject.put("paymentUrlId",paymentLink.getId());
                    jsonObject.put("paymentUrl",paymentLink.getUrl());
                    jsonObject.put("productDescription",product.getDescription());
                    jsonArray.add(jsonObject);
                    jsonObject.clear();
                    jsonObject.put("product",jsonArray);
                    jsonObject.put("success","success");
                }
            }

        }catch(Exception e){
            e.printStackTrace();
            LOG.error(" ------------------ Exception in createPaymentLink method  ----------------"+  e.getMessage());
        }
        return jsonObject;
    }

    public JSONObject retrieveAllProduct (){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<StripeProductImpl> stripeProductList = null;
        try {
            stripeProductList = stripeDao.retrieveAllProduct();
            if(stripeProductList !=null){
                for(StripeProductImpl stripeProduct : stripeProductList){
                    jsonObject.put("productName",stripeProduct.getStripeProcess());
                    jsonObject.put("productId",stripeProduct.getProductId());
                    jsonObject.put("priceId",stripeProduct.getPriceId());
                    jsonObject.put("currency",stripeProduct.getCurrency());
                    jsonObject.put("price",stripeProduct.getStripeFee());
                    jsonObject.put("paymentUrlId",stripeProduct.getPaymentLinkId());
                    jsonObject.put("paymentUrl",stripeProduct.getPaymentLink());
                    jsonObject.put("productDescription",stripeProduct.getDescription());
                    jsonArray.add(jsonObject);
                    jsonObject.clear();
                }
                if(jsonArray.size()>0){
                    jsonObject.put("AllProduct",jsonArray);
                    jsonObject.put("success","success");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            LOG.error(" ------------------ Exception in retrieveAllProduct method  ----------------"+  e.getMessage());
        }
        return jsonObject;
    }
    public JSONObject retrieveAllCharge(){
        JSONObject jsonObject = new JSONObject();
        Stripe.apiKey = stripeApiKey;
        try {
            ChargeListParams params = ChargeListParams.builder().setLimit(10L).build();
            ChargeCollection charges = Charge.list(params);

            jsonObject.put("chargeList", charges.toJson());
        }catch(Exception e){
            e.printStackTrace();
            LOG.error(" ------------------ Exception in retrieveAllCharge method  ----------------"+  e.getMessage());
        }
        return jsonObject;
    }
}

