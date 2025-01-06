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
import java.util.List;

@WebServlet("/admin/manageCustomer")
public class ManageCustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_admin.jsp");
            return;
        }

        String searchQuery = request.getParameter("search");
        List<Customer> customers = (searchQuery == null || searchQuery.trim().isEmpty())
                ? customerService.getAllCustomers()
                : customerService.searchCustomers(searchQuery);

        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/admin/manageCustomer.jsp").forward(request, response);
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("add_customer") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            try {
                String hashedPassword = customerService.hashPassword(password);

                Customer customer = new Customer(username, hashedPassword, phoneNumber, address);
                customerService.addCustomer(customer);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/admin/manageCustomer?error=add_failed");
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/manageCustomer");
    }
}