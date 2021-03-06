package Controllers;


import DAO.*;
import models.Basket_order;
import models.Order;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class OrderController {
    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String findAll(ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);
        model.addAttribute("OrdersList", orders.findAll());
        return "orders";
    }

    @RequestMapping(value = "/search_orders", method = RequestMethod.GET)
    public String list(@RequestParam String delivery_address,
                       @RequestParam(required = false) Boolean payment_card,
                       @RequestParam String min_o_date, @RequestParam String max_o_date,
                       @RequestParam String min_d_date, @RequestParam String max_d_date,
                       @RequestParam String status,
                       @RequestParam String min_d_price, @RequestParam String max_d_price,
                       ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);
        if (delivery_address.equals("")) delivery_address = null;
        if (status.equals("")) status = null;
        Double min_d_p = (min_d_price.equals("")) ? null : Double.parseDouble(min_d_price);
        Double max_d_p = (max_d_price.equals("")) ? null : Double.parseDouble(max_d_price);
        Date min_o_d = null;
        if (!min_o_date.equals("")) min_o_d = get_sql_date(min_o_date);
        Date max_o_d = null;
        if (!max_o_date.equals("")) max_o_d = get_sql_date(max_o_date);
        Date min_d_d = null;
        if (!min_d_date.equals("")) min_d_d = get_sql_date(min_d_date);
        Date max_d_d = null;
        if (!max_d_date.equals("")) max_d_d = get_sql_date(max_d_date);
        model.addAttribute("OrdersList",
                orders.find(null, delivery_address, payment_card, min_o_d, max_o_d,
                        min_d_d, max_d_d, status, min_d_p, max_d_p));
        model.addAttribute("delivery_address", delivery_address);
        model.addAttribute("min_o_date", min_o_date);
        model.addAttribute("max_o_date", max_o_date);
        model.addAttribute("min_d_date", min_d_date);
        model.addAttribute("max_d_date", max_d_date);
        model.addAttribute("status", status);
        model.addAttribute("min_d_price", min_d_price);
        model.addAttribute("max_d_price", max_d_price);
        return "orders/search_results";
    }

    @RequestMapping(value = "/edit_orders", method = RequestMethod.POST)
    public String edit_order(@RequestParam Integer id,
                            ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);
        Order order = orders.getById(id);
        model.addAttribute("id", id);
        model.addAttribute("delivery_date", order.getDelivery_date());
        model.addAttribute("status", order.getStatus());
        return "orders/edit";
    }

    @RequestMapping(value = "/edit_done_orders", method = RequestMethod.POST)
    public String edit_done(@RequestParam Integer id,
                            @RequestParam String delivery_date, @RequestParam String status, ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);
        if (delivery_date.equals("") || status.equals("")) {
            model.addAttribute("error", true);
            model.addAttribute("delivery_date", delivery_date );
            model.addAttribute("status", status);
            return "orders/edit";
        }
        Order order = orders.getById(id);
        order.setDelivery_date(get_sql_date(delivery_date));
        order.setStatus(status);
        session.getTransaction().begin();
        orders.update(order);
        session.getTransaction().commit();
        double full_price = 0;
        for(Basket_order b_o : order.getBasket_orderList()) {
            full_price+=b_o.getPrice()*b_o.getCount_book();
        }
        model.addAttribute("order", order);
        model.addAttribute("full_price", full_price);
        model.addAttribute("BooksList", order.getBasket_orderList());
        return "orders/detailed";
        /*model.addAttribute("OrdersList", orders.findAll());
        return "orders";*/
    }

    @RequestMapping(value = "/rm_orders", method = RequestMethod.POST)
    public String remove_order(@RequestParam Integer id,
                              ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);

        session.getTransaction().begin();
        orders.delete(orders.getById(id));
        session.getTransaction().commit();

        model.addAttribute("OrdersList", orders.findAll());
        return "orders";
    }

    @RequestMapping(value = "/detailed_orders", method = RequestMethod.GET)
    public String detailed_order(@RequestParam Integer id,
                                ModelMap model) {
        Session session = factory.openSession();
        OrderDAO orders = new OrderDAO(session);
        Order order = orders.getById(id);
        double full_price = 0;
        for(Basket_order b_o : order.getBasket_orderList()) {
            full_price+=b_o.getPrice()*b_o.getCount_book();
        }
        model.addAttribute("order", order);
        model.addAttribute("full_price", full_price);
        model.addAttribute("BooksList", order.getBasket_orderList());
        return "orders/detailed";
    }

    java.sql.Date get_sql_date(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
            try {
            parsed = format.parse(date);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        java.sql.Date d_date = new java.sql.Date(parsed.getTime());
        return d_date;
    }
}
