package ru.job4j.shop;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Food {

    private final String name;
    private final LocalDate expireDate;
    private final LocalDate createDate;
    private Double price;
    private int discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, Double price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public Double getPrice() {
        return price;
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
        int totalLifeTime = (int) ChronoUnit.DAYS.between(createDate, expireDate);
        LocalDate currentDate = LocalDate.now();
        int currentLifeTime = (int) ChronoUnit.DAYS.between(createDate, currentDate);
        return (currentLifeTime * 100) / totalLifeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return name.equals(food.name) &&
                expireDate.equals(food.expireDate) &&
                createDate.equals(food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate);
    }
}
