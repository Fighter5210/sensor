package com.dy.sensor.product.model;

public class ProductDataBean {
    /**
     * 产品信息
     */
    private Long userhId;// 猎头id
    private String userhName;// 猎头id
    private Double totalIncome;// 总收入
    private Double realTotalincome;// 实际总收入
    private Double realTotalpay;// 实际总支出
    private Double balanceMoney;// 账户锁定状态

    public Long getUserhId() {
        return userhId;
    }

    public void setUserhId(Long userhId) {
        this.userhId = userhId;
    }

    public String getUserhName() {
        return userhName;
    }

    public void setUserhName(String userhName) {
        this.userhName = userhName;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getRealTotalincome() {
        return realTotalincome;
    }

    public void setRealTotalincome(Double realTotalincome) {
        this.realTotalincome = realTotalincome;
    }

    public Double getRealTotalpay() {
        return realTotalpay;
    }

    public void setRealTotalpay(Double realTotalpay) {
        this.realTotalpay = realTotalpay;
    }

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

}
