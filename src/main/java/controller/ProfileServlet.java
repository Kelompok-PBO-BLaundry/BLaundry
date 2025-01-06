/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Customer;
import service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/customer/profile")
public class ProfileServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_cust.jsp");
            return;
        }

        int customerId = (Integer) session.getAttribute("customer_id");
        Customer customer = customerService.getCustomerById(customerId);
        request.setAttribute("user", customer);
        request.getRequestDispatcher("/customer/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_cust.jsp");
            return;
        }

        int customerId = (Integer) session.getAttribute("customer_id");
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phone_number");
            String address = request.getParameter("address");

            try {
                Customer customer = customerService.getCustomerById(customerId);
                customer.setUsername(username);
                customer.setPhoneNumber(phoneNumber);
                customer.setAddress(address);

                if (password != null && !password.isEmpty()) {
                    String hashedPassword = customerService.hashPassword(password);
                    customer.setPassword(hashedPassword);
                }

                customerService.updateCustomerProfile(customer);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/customer/profile?error=update_failed");
                return;
            }
        } else if ("delete".equals(action)) {
            customerService.deleteCustomer(customerId);
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login_cust.jsp");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/customer/profile");
    }
}