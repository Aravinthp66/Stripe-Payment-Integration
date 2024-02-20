package com.example.Stripe_Integration.StripeDomain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "stripe_customer")
public class StripeCustomerImpl implements StripeCustomer{

    private static final Log LOG = LogFactory.getLog(StripeCustomerImpl.class);

        @Id
        @Field("primaryId")
        private String primaryId;

        @Field("stripe_customer_id")
        private String stripeCustomerId;

        @Field("stripe_customer_name")
        private String stripeCustomerName;

        @Field("phone_no")
        private String phoneNumber;

        @Field("response_id")
        private String stripeResponseId;

        @Field("user_id")
        private String userId;

        @Field("user_type")
        private String userType;

        @Field("object")
        private String object;

        @Field("live_mode")
        private Boolean liveMode;

        @Field("created_timestamp")
        private Date createdTimeStamp;

        @Field("account_balance")
        private Integer accountBalance;

        @Field("currency")
        private String currency;

        @Field("default_source")
        private String defaultSource;

        @Field("delinquent")
        private Boolean delinquent;

        @Field("description")
        private String description;

        @Field("email")
        private String emailId;

        @Field("insertion_time")
        private Date insertionTime;

    @Override
    public String getStripeCustomerName() {
        return stripeCustomerName;
    }

    @Override
    public void setStripeCustomerName(String stripeCustomerName) {
        this.stripeCustomerName = stripeCustomerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrimaryId() {
            return primaryId;
        }

        public void setPrimaryId(String primaryId) {
            this.primaryId = primaryId;
        }

        public String getStripeCustomerId() {
            return stripeCustomerId;
        }

        public void setStripeCustomerId(String stripeCustomerId) {
            this.stripeCustomerId = stripeCustomerId;
        }

        public String getStripeResponseId() {
            return stripeResponseId;
        }

        public void setStripeResponseId(String stripeResponseId) {
            this.stripeResponseId = stripeResponseId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getObject() {
            return object;
        }

        public void setObject(String object) {
            this.object = object;
        }

        public Boolean getLiveMode() {
            return liveMode;
        }

        public void setLiveMode(Boolean liveMode) {
            this.liveMode = liveMode;
        }

        public Date getCreatedTimeStamp() {
            return createdTimeStamp;
        }

        public void setCreatedTimeStamp(Date createdTimeStamp) {
            this.createdTimeStamp = createdTimeStamp;
        }

        public Integer getAccountBalance() {
            return accountBalance;
        }

        public void setAccountBalance(Integer accountBalance) {
            this.accountBalance = accountBalance;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDefaultSource() {
            return defaultSource;
        }

        public void setDefaultSource(String defaultSource) {
            this.defaultSource = defaultSource;
        }

        public Boolean getDelinquent() {
            return delinquent;
        }

        public void setDelinquent(Boolean delinquent) {
            this.delinquent = delinquent;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public Date getInsertionTime() {
            return insertionTime;
        }

        public void setInsertionTime(Date insertionTime) {
            this.insertionTime = insertionTime;
        }

        public void cleanUp(){

            stripeCustomerId = null;
            stripeResponseId = null;
            userId = null;
            userType = null;
            object = null;
            liveMode = null;
            createdTimeStamp = null;
            currency = null;
            defaultSource = null;
            description = null;
            emailId = null;

        }

}
