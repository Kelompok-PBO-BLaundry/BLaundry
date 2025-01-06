package controller;

import model.Employee;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginAdmin")
public class LoginAdminServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeeService = new EmployeeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login_admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Employee employee = employeeService.authenticateEmployee(username, password);
        if (employee != null) {
            HttpSession session = request.getSession();
            
            session.setAttribute("employee_id", employee.getUserID());
            session.setAttribute("user", employee.getUsername());
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login_admin.jsp").forward(request, response);
        }
    }
}