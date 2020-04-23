package form;

public class OrderSearch {
    private String delivery_address;
    private Boolean payment_card;
    private String min_o_date;
    private String max_o_date;
    private String min_d_date;
    private String  max_d_date;
    private String status;
    private String min_d_price;
    private String max_d_price;

    public OrderSearch() {
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Boolean getPayment_card() {
        return payment_card;
    }

    public void setPayment_card(Boolean payment_card) {
        this.payment_card = payment_card;
    }

    public String getMin_o_date() {
        return min_o_date;
    }

    public void setMin_o_date(String min_o_date) {
        this.min_o_date = min_o_date;
    }

    public String getMax_o_date() {
        return max_o_date;
    }

    public void setMax_o_date(String max_o_date) {
        this.max_o_date = max_o_date;
    }

    public String getMin_d_date() {
        return min_d_date;
    }

    public void setMin_d_date(String min_d_date) {
        this.min_d_date = min_d_date;
    }

    public String getMax_d_date() {
        return max_d_date;
    }

    public void setMax_d_date(String max_d_date) {
        this.max_d_date = max_d_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMin_d_price() {
        return min_d_price;
    }

    public void setMin_d_price(String min_d_price) {
        this.min_d_price = min_d_price;
    }

    public String getMax_d_price() {
        return max_d_price;
    }

    public void setMax_d_price(String max_d_price) {
        this.max_d_price = max_d_price;
    }
}
