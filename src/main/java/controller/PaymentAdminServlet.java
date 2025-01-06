package controller;

import model.Order;
import service.OrderService;
import service.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/payment")
public class PaymentAdminServlet extends HttpServlet {
    private OrderService orderService;
    private PaymentService paymentService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService();
        paymentService = new PaymentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_admin.jsp");
            return;
        }

        int orderId = Integer.parseInt(request.getParameter("order_id"));
        Order order = orderService.getOrderById(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("payment_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("order_id"));
        String paymentMethod = request.getParameter("payment_method");
        double totalAmount = Double.parseDouble(request.getParameter("total_amount"));
        String userType = request.getParameter("user_type");
        
        System.out.println(orderId + paymentMethod + totalAmount + userType);

        paymentService.processPayment(orderId, paymentMethod, totalAmount, userType);

        if ("employee".equals(userType)) {
            response.sendRedirect(request.getContextPath() + "/success_admin.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/success_cust.jsp");
        }
    }
}