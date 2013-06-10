package com.discorp.savings.dto;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: tiepnm
 * Date: 5/23/13
 * Time: 4:19 PM
 */
public class SavingsDTO {
    private Integer id;
    private String nameSaving;
    private Double moneySaving;
    private List<HistoryDTO> historyList;
    private Double earnInterest;

    public String getNameSaving() {
        return nameSaving;
    }

    public void setNameSaving(String nameSaving) {
        this.nameSaving = nameSaving;
    }

    public Double getMoneySaving() {
        return moneySaving;
    }

    public void setMoneySaving(Double moneySaving) {
        this.moneySaving = moneySaving;
    }

    public List<HistoryDTO> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<HistoryDTO> historyList) {
        this.historyList = historyList;
    }

    public Double getEarnInterest() {
        return earnInterest;
    }

    public void setEarnInterest(Double earnInterest) {
        this.earnInterest = earnInterest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
