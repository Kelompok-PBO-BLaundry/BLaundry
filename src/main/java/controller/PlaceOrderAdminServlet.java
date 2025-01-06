package controller;

import model.Customer;
import model.Order;
import service.CustomerService;
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

@WebServlet("/admin/placeOrder")
public class PlaceOrderAdminServlet extends HttpServlet {
    private OrderService orderService;
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService();
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_admin.jsp");
            return;
        }

        String customerName = request.getParameter("customer_name");
        String serviceType = request.getParameter("service_type");
        double weight = Double.parseDouble(request.getParameter("weight"));
        double totalItem = Double.parseDouble(request.getParameter("number_of_items"));
        String duration = request.getParameter("duration");
        double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
        String deliveryAddress = request.getParameter("delivery_address");

        Customer customer = customerService.getCustomerByName(customerName);
        if (customer == null) {
            request.setAttribute("error", "Customer not found.");
            request.getRequestDispatcher("").forward(request, response);
            return;
        }
        
        Date date = Date.valueOf(LocalDate.now());

        Order order = new Order();
        order.setOrderDate(date);
        order.setOrderStatus("Pending");
        order.setServiceType(serviceType);
        order.setTotalWeight(weight);
        order.setTotalItem(totalItem);
        order.setDuration(duration);
        order.setTotalAmount(totalAmount);
        order.setDeliveryAddress(deliveryAddress);
        order.setCustomerId(customer.getUserID());

        int orderId = orderService.placeAdminOrder(order);

        response.sendRedirect(request.getContextPath() + "/admin/payment?order_id=" + orderId);
    }
}