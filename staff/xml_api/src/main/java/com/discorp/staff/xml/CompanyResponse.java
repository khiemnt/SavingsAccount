package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tiepnm
 * Date: 10/3/12
 * Time: 3:03 PM
 * To change this template use File | Settings | File Templates.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "CompanyResponse")
public class CompanyResponse {


    @XmlElement(required = true)
    private List<Company> listCompany;
    @XmlElement(required = true)
    private List<BillingHistory> listBillingHistory;
    @XmlElement(required = true)
    private List<AdminPayment> listAdminPayment;
    @XmlElement(required = true)
    private List<TextEntry> listEntryText;

    public List<AdminPayment> getListAdminPayment() {
        return listAdminPayment;
    }

    public void setListAdminPayment(List<AdminPayment> listAdminPayment) {
        this.listAdminPayment = listAdminPayment;
    }

    public List<BillingHistory> getListBillingHistory() {
        return listBillingHistory;
    }

    public void setListBillingHistory(List<BillingHistory> listBillingHistory) {
        this.listBillingHistory = listBillingHistory;
    }

    public List<Company> getListCompany() {
        return listCompany;
    }

    public void setListCompany(List<Company> listCompany) {
        this.listCompany = listCompany;
    }

    public List<TextEntry> getListEntryText()
    {
        return listEntryText;
    }

    public void setListEntryText(List<TextEntry> listEntryText)
    {
        this.listEntryText = listEntryText;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "TextEntry")
    public static class TextEntry
    {
        public int getId()
        {
            return id;
        }

        public void setId(int id)
        {
            this.id = id;
        }

        public String getBlock_name()
        {
            return block_name;
        }

        public void setBlock_name(String block_name)
        {
            this.block_name = block_name;
        }

        public String getBlock_content()
        {
            return block_content;
        }

        public void setBlock_content(String block_content)
        {
            this.block_content = block_content;
        }

        @XmlElement(required = true)
        private int id;
        @XmlElement(required = true)
        private String block_name;
        @XmlElement(required = true)
        private String block_content;

    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "Company")
    public static class Company
    {
        @XmlElement(required = true)
        private String companyName;
        @XmlElement(required = true)
        private String noMember;
        @XmlElement(required = true)
        private String monthlyBilling;
        @XmlElement(required = true)
        private String totalBilled;
        @XmlElement(required = true)
        private String startDate;
        @XmlElement(required = true)
        private String billingDelay;
        @XmlElement(required = true)
        private String rate;
        @XmlElement(required = true)
        private String id;
        @XmlElement(required = true)
        private List<Account> listAdminDTO;
        @XmlElement(required = true)
        private String dealerId;





        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getNoMember() {
            return noMember;
        }

        public void setNoMember(String noMember) {
            this.noMember = noMember;
        }

        public String getMonthlyBilling() {
            return monthlyBilling;
        }

        public void setMonthlyBilling(String monthlyBilling) {
            this.monthlyBilling = monthlyBilling;
        }

        public String getTotalBilled() {
            return totalBilled;
        }

        public void setTotalBilled(String totalBilled) {
            this.totalBilled = totalBilled;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getBillingDelay() {
            return billingDelay;
        }

        public void setBillingDelay(String billingDelay) {
            this.billingDelay = billingDelay;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Account> getListAdminDTO() {
            return listAdminDTO;
        }

        public void setListAdminDTO(List<Account> listAdminDTO) {
            this.listAdminDTO = listAdminDTO;
        }

        public String getDealerId() {
            return dealerId;
        }

        public void setDealerId(String dealerId) {
            this.dealerId = dealerId;
        }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "BillingHistory")
    public static class BillingHistory {
        @XmlElement(required = true)
        private String from;
        @XmlElement(required = true)
        private String to;
        @XmlElement(required = true)
        private String amount;
        @XmlElement(required = true)
        private String note;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    @XmlRootElement(name = "AdminPayment")
    public static class AdminPayment {
        @XmlElement(required = true)
        private String id;
        @XmlElement(required = true)
        private String card_name;
        @XmlElement(required = true)
        private String card_number;
        @XmlElement(required = true)
        private String csv_code;
        @XmlElement(required = true)
        private String amount;
        @XmlElement(required = true)
        private String expire_date;
        @XmlElement(required = true)
        private String payment_id;
        @XmlElement(required = true)
        private String customer_id;
        @XmlElement(required = true)
        private Integer billingId;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getCsv_code() {
            return csv_code;
        }

        public void setCsv_code(String csv_code) {
            this.csv_code = csv_code;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getExpire_date() {
            return expire_date;
        }

        public void setExpire_date(String expire_date) {
            this.expire_date = expire_date;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getPayment_id() {
            return payment_id;
        }

        public void setPayment_id(String payment_id) {
            this.payment_id = payment_id;
        }

        public Integer getBillingId() {
            return billingId;
        }

        public void setBillingId(Integer billingId) {
            this.billingId = billingId;
        }
    }
}
