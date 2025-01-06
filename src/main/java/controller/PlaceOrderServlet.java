/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import model.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/customer/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_cust.jsp");
            return;
        }

        int customerId = (Integer) session.getAttribute("customer_id");
        String serviceType = request.getParameter("service_type");
        double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
        String deliveryAddress = "";
        Date date = Date.valueOf(LocalDate.now());
        
        Order order = new Order();
        order.setOrderDate(date);
        order.setOrderStatus("Pending");
        order.setServiceType(serviceType);
        order.setTotalAmount(totalAmount);
        order.setDeliveryAddress(deliveryAddress);
        order.setCustomerId(customerId);

        int orderId = orderService.placeCustomerOrder(order);

        response.sendRedirect(request.getContextPath() + "/customer/payment?order_id=" + orderId);
    }
}