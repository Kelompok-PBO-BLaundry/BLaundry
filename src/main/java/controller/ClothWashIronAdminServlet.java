package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin/iron_wash_order")
public class ClothWashIronAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("employee_id") == null) {
            response.sendRedirect(request.getContextPath() + "/login_admin.jsp");
            return;
        }

        request.getRequestDispatcher("/admin/iron_wash_admin.jsp").forward(request, response);
    }
}