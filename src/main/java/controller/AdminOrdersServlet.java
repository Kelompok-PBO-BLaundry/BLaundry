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
import java.util.List;

@WebServlet("/admin/admin_orders")
public class AdminOrdersServlet extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        super.init();
        orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_admin.jsp");
            return;
        }

        List<Order> orders = orderService.getAllOrders();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/admin/admin_orders.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("update_order_status") != null) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            String orderStatus = request.getParameter("order_status");
            orderService.updateOrderStatus(orderId, orderStatus);
        } else if (request.getParameter("delete_order") != null) {
            int orderId = Integer.parseInt(request.getParameter("order_id"));
            orderService.deleteOrder(orderId);
        }
        response.sendRedirect(request.getContextPath() + "/admin/admin_orders");
    }
}
