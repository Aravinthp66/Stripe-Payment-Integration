package com.example.Stripe_Integration.StripeDao;
import com.example.Stripe_Integration.StripeDomain.StripeCustomerImpl;
import com.example.Stripe_Integration.StripeDomain.StripePaymentImpl;
import com.example.Stripe_Integration.StripeDomain.StripeProductImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StripeDaoImpl implements StripeDao{

    private static final Log LOG = LogFactory.getLog(StripeDaoImpl.class);

    MongoOperations operations;
    public StripeDaoImpl(MongoOperations operations){
        this.operations = operations;
    }
    public static final String STRIPE_CUSTOMER= "stripe_customer";
    public static final String STRIPE_PAYMENT= "stripe_payment";
    public static final String STRIPE_PRODUCT= "stripe_product";
    public static final String STRIPE_CREDIT_CARD= "stripe_credit_card";


    public StripeCustomerImpl saveStripeCustomer(StripeCustomerImpl stripeCustomer){
        try{
            this.operations.save(stripeCustomer,STRIPE_CUSTOMER);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return stripeCustomer;
    }

    public StripeCustomerImpl getStripeCustomer(String email){
        StripeCustomerImpl stripeCustomer = null;
        try {
            Query query = new Query(Criteria.where("email").is(email));
            stripeCustomer = this.operations.findOne(query,StripeCustomerImpl.class,STRIPE_CUSTOMER);
        }catch(Exception e){
            e.printStackTrace();
        }
        return stripeCustomer;
    }

    public StripePaymentImpl saveStripePayment(StripePaymentImpl stripePayment){
        try{
            this.operations.save(stripePayment,STRIPE_PAYMENT);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return stripePayment;
    }

    public StripeProductImpl saveStripeProduct(StripeProductImpl stripeProduct){
        try{
            this.operations.save(stripeProduct,STRIPE_PRODUCT);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return stripeProduct;
    }

    public List<StripeProductImpl> retrieveAllProduct(){
        List<StripeProductImpl> stripeProductList = null;
        try{
            stripeProductList = this.operations.findAll(StripeProductImpl.class,STRIPE_PRODUCT);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return stripeProductList;
    }

}
