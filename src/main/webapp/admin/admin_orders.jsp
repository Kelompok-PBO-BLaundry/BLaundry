<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_myOrder.css">
</head>
<body>
    <header class="header">
        <nav class="navbar">
            <div class="menu">
                <a href="${pageContext.request.contextPath}/admin/home">Home</a>
                <a href="${pageContext.request.contextPath}/admin/admin_orders" class="active">Manage Orders</a>
            </div>
            <div class="logout">
                <a href="${pageContext.request.contextPath}/login_admin.jsp">Logout</a>
            </div>
        </nav>
        <h1 class="title">Manage Orders</h1>
    </header>
    <main class="main">
        <c:forEach var="order" items="${orders}">
            <div class="order-card">
                <div class="order-info">
                    <div class="order-icon">${order.serviceType == 'Shoe Wash' ? '👟' : '👕'}</div>
                    <div>
                        <h2>${order.serviceType}</h2>
                        <form action="${pageContext.request.contextPath}/admin/admin_orders" method="POST">
                            <p><strong>Customer:</strong> ${order.customerName} (ID: ${order.customerId})</p>
                            <p class="status ${order.orderStatus.toLowerCase().replace(' ', '-')}">
                                <select name="order_status">
                                    <option value="Pending" ${order.orderStatus == 'Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="Processing" ${order.orderStatus == 'Processing' ? 'selected' : ''}>Processing</option>
                                    <option value="On Delivery" ${order.orderStatus == 'On Delivery' ? 'selected' : ''}>On Delivery</option>
                                    <option value="Completed" ${order.orderStatus == 'Completed' ? 'selected' : ''}>Completed</option>
                                </select>
                                <input type="hidden" name="order_id" value="${order.orderId}">
                                <button type="submit" name="update_order_status">Update</button>
                                <button type="submit" name="delete_order">Delete</button>
                            </p>                           
                            <p>Ordered on: <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd" /></p>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </main>
</body>
</html>
