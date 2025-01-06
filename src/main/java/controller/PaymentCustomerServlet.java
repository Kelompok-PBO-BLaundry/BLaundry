/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Customer;
import model.Order;
import service.CustomerService;
import service.OrderService;
import service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/customer/payment")
public class PaymentCustomerServlet extends HttpServlet {
    private OrderService orderService;
    private PaymentService paymentService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService();
        paymentService = new PaymentService();
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_cust.jsp");
            return;
        }

        int orderId = Integer.parseInt(request.getParameter("order_id"));
        Order order = orderService.getOrderById(orderId);
        Customer customer = customerService.getCustomerById((Integer) session.getAttribute("customer_id"));

        request.setAttribute("order", order);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/payment_cust.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("order_id"));
        String paymentMethod = request.getParameter("payment_method");
        double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
        String userType = request.getParameter("user_type");

        paymentService.processPayment(orderId, paymentMethod, totalAmount, userType);

        if ("customer".equals(userType)) {
            response.sendRedirect(request.getContextPath() + "/success_cust.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/success_admin.jsp");
        }
    }
}
