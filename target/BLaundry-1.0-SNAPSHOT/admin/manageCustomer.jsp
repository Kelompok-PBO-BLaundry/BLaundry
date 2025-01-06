<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manageCustomer.css">
</head>
<body>
    <div class="gradient-container">
        <header>
            <nav>
                <div class="nav-left">
                    <a href="${pageContext.request.contextPath}/admin/home">Home</a>
                    <a href="${pageContext.request.contextPath}/admin/admin_orders">Order</a>
                </div>
                <div class="nav-right">
                    <a href="${pageContext.request.contextPath}/login_admin.jsp" class="logout">Logout</a>
                </div>
            </nav>
        </header>
        <div class="welcome">
            <h1>Manage Customer</h1>
        </div>
    </div>

    <div class="main-content">
        <div class="search-container">
            <form method="GET" action="${pageContext.request.contextPath}/admin/manageCustomer">
                <input type="text" id="searchBar" name="search" placeholder="Search by username..." value="${param.search}">
                <button type="submit" id="searchButton">Search</button>
            </form>
        </div>

        <div class="add-customer-container">
            <form method="POST" action="${pageContext.request.contextPath}/admin/manageCustomer">
                <input type="text" name="username" placeholder="Username" required>
                <input type="password" name="password" placeholder="Password" required>
                <input type="text" name="phoneNumber" placeholder="Phone Number" required>
                <input type="text" name="address" placeholder="Address" required>
                <button type="submit" name="add_customer">Add Customer</button>
            </form>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty customers}">
                            <c:forEach var="customer" items="${customers}">
                                <tr>
                                    <td>${customer.userID}</td>
                                    <td>${customer.username}</td>
                                    <td>${customer.phoneNumber}</td>
                                    <td>${customer.address}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr><td colspan="4">No data found</td></tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </div>

    <footer>
        <div class="footer-container">
            <div class="left-section">
                <div class="location">
                    <p><strong>Our Location:</strong></p>
                    <p>Jl. Sukabirus no123, Bojongsoang, Kabupaten Bandung</p>
                </div>
                <div class="operational-hour">
                    <p><strong>Operational hour:</strong></p>
                    <p>09.00-17.00</p>
                </div>
            </div>
            <div class="contact">
                <p><strong>Contact us at:</strong></p>
                <p>+62812345678</p>
                <p>blaundry@gmail.com</p>
            </div>
        </div>
        <p class="footer-rights">© 2024 BLaundry. All rights reserved.</p>
    </footer>
</body>
</html>