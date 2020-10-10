package ru.job4j.shop;

import java.util.Date;

public class Food {

    private final String name;
    private final Date expireDate;
    private final Date createDate;
    private Double price;
    private int discount;

    public Food(String name, Date expireDate, Date createDate, Double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expireDate=" + expireDate +
                ", createDate=" + createDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }

    /**
     * Getting product expiring parameter in %.
     * fresh product has 0%.
     * expired product has 100%.
     *
     * @return expiring parameter from 0% to 100%.
     */
    public int getExpiredPercentage() {
        long create = createDate.getTime();
        long expire = expireDate.getTime();

        long totalLifeTime = expire - create;
        long currentLifeTime = System.currentTimeMillis() - create;

        return (int) (currentLifeTime * 100 / totalLifeTime);
    }
}
