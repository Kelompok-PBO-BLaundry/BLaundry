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

@WebServlet("/loginCustomer")
public class LoginCustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login_cust.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Customer customer = customerService.authenticateCustomer(username, password);
            if (customer != null) {
                HttpSession session = request.getSession();
                session.setAttribute("customer_id", customer.getUserID());
                session.setAttribute("user", customer.getUsername());
                response.sendRedirect(request.getContextPath() + "/customer/home");
            } else {
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("/login_cust.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Login failed due to an unexpected error.");
            request.getRequestDispatcher("/login_cust.jsp").forward(request, response);
        }
    }
}