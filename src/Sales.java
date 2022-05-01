
import java.util.*;
import java.util.Date;

//-----------------------------------------------
// Assignment 1
// © Anushka R Shetty
// Written by: Anushka R Shetty 40192371
//-----------------------------------------------

public class Sales {
    private String country;
    private String item_type;
    private Character order_priority;
    private Date order_date;
    private Long order_id;
    private Date ship_date;
    private Integer units_sold;
    private Float unit_price;
    private Float unit_cost;
    private Double revenue;
    private Double total_cost;
    private Double total_profit;

    public Sales(Sales sales){
        this.country = sales.country;
        this.item_type = sales.item_type;
        this.order_priority = sales.order_priority;
        this.order_date = sales.order_date;
        this.order_id = sales.order_id;
        this.ship_date = sales.ship_date;
        this.units_sold = sales.units_sold;
        this.unit_price = sales.unit_price;
        this.unit_cost = sales.unit_cost;
        this.revenue = sales.revenue;
        this.total_cost = sales.total_cost;
        this.total_profit = sales.total_profit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public Character getOrder_priority() {
        return order_priority;
    }

    public void setOrder_priority(Character order_priority) {
        this.order_priority = order_priority;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Date getShip_date() {
        return ship_date;
    }

    public void setShip_date(Date ship_date) {
        this.ship_date = ship_date;
    }

    public Integer getUnits_sold() {
        return units_sold;
    }

    public void setUnits_sold(Integer units_sold) {
        this.units_sold = units_sold;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

    public Float getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(Float unit_cost) {
        this.unit_cost = unit_cost;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(Double total_cost) {
        this.total_cost = total_cost;
    }

    public Double getTotal_profit() {
        return total_profit;
    }

    public void setTotal_profit(Double total_profit) {
        this.total_profit = total_profit;
    }

    public Sales(String country, String item_type, Character order_priority,
                 Date order_date, Long order_id, Date ship_date, Integer units_sold,
                 Float unit_price, Float unit_cost, Double revenue, Double total_cost, Double total_profit) {
        this.country = country;
        this.item_type = item_type;
        this.order_priority = order_priority;
        this.order_date = order_date;
        this.order_id = order_id;
        this.ship_date = ship_date;
        this.units_sold = units_sold;
        this.unit_price = unit_price;
        this.unit_cost = unit_cost;
        this.revenue = revenue;
        this.total_cost = total_cost;
        this.total_profit = total_profit;
    }

    @Override
    public String toString() {
        return "Sales{" + "\n"+
                "country='" + country + '\'' + "\n"+
                ", item_type='" + item_type + '\'' + "\n"+
                ", order_priority=" + order_priority + "\n"+
                ", order_date=" + order_date + "\n"+
                ", order_id=" + order_id + "\n"+
                ", ship_date=" + ship_date + "\n"+
                ", units_sold=" + units_sold + "\n"+
                ", unit_price=" + unit_price + "\n"+
                ", unit_cost=" + unit_cost + "\n"+
                ", revenue=" + revenue + "\n"+
                ", total_cost=" + total_cost + "\n"+
                ", total_profit=" + total_profit + "\n"+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Objects.equals(country, sales.country) && Objects.equals(item_type, sales.item_type) && Objects.equals(order_priority, sales.order_priority)
                && Objects.equals(order_date, sales.order_date) && Objects.equals(order_id, sales.order_id) && Objects.equals(ship_date, sales.ship_date)
                && Objects.equals(units_sold, sales.units_sold) && Objects.equals(unit_price, sales.unit_price) && Objects.equals(unit_cost, sales.unit_cost)
                && Objects.equals(revenue, sales.revenue) && Objects.equals(total_cost, sales.total_cost) && Objects.equals(total_profit, sales.total_profit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, item_type, order_priority, order_date, order_id, ship_date, units_sold, unit_price, unit_cost, revenue, total_cost, total_profit);
    }

}
