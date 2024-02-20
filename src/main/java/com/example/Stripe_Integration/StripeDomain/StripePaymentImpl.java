package com.example.Stripe_Integration.StripeDomain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("stripe_payment")
public class StripePaymentImpl implements StripePayment {


    @Id
    @Field("primaryId")
    private String primaryId;

    @Field("invoice_id")
    private String invoiceId;

    @Field("charge_id")
    private String chargeId;

    @Field("user_id")
    private String userId;

    @Field("transaction_type")
    private String transactionType;

    @Field("transaction_mode")
    private String transactionMode;

    @Field("join_date")
    private String joinDate;

    @Field("description")
    private String description;

    @Field("amount")
    private String amount;

    @Field("candidate_name")
    private String candidateName;

    @Field("transaction_status")
    private String transactionStatus;

    @Field("reference_id")
    private String referenceId;

    @Field("process_name")
    private String processName;

    @Field("used_card_number")
    private String usedCardNumber;

    @Field("type")
    private String type;

    @Field("is_active")
    private Boolean isActive;

    @Field("insertion_time")
    private Date insertionTime;

    @Field("updated_time")
    private Date updatedTime;

    public String getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(String primaryId) {
        this.primaryId = primaryId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getUsedCardNumber() {
        return usedCardNumber;
    }

    public void setUsedCardNumber(String usedCardNumber) {
        this.usedCardNumber = usedCardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getInsertionTime() {
        return insertionTime;
    }

    public void setInsertionTime(Date insertionTime) {
        this.insertionTime = insertionTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
