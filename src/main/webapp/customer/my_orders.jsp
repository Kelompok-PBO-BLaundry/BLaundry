<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_myOrder.css">
</head>
<body>
    <header class="header">
        <nav class="navbar">
            <div class="menu">
                <a href="${pageContext.request.contextPath}/customer/home">Home</a>
                <a href="${pageContext.request.contextPath}/customer/profile">My Profile</a>
                <a href="${pageContext.request.contextPath}/customer/my_orders" class="active">My Order</a>
            </div>
            <div class="logout">
                <a href="${pageContext.request.contextPath}/login_cust.jsp">Logout</a>
            </div>
        </nav>
        <h1 class="title">My Order</h1>
    </header>
    <main class="main">
        <c:forEach var="order" items="${orders}">
            <div class="order-card ${order.orderStatus == 'Completed' ? 'done' : 'active'}">
                <div class="order-info">
                    <div class="order-icon">${order.serviceType == 'Shoes Wash' ? '👟' : '👕'}</div>
                    <div>
                        <h2>${order.serviceType}</h2>
                        <p class="status ${order.orderStatus.toLowerCase().replace(' ', '-')}">
                            ${order.orderStatus}
                        </p>
                        <p>
                            <c:choose>
                                <c:when test="${order.orderStatus == 'Completed'}">
                                    Delivered at: <fmt:formatDate value="${order.orderDate}" pattern="EEEE, dd MMMM" />
                                </c:when>
                                <c:otherwise>
                                    Estimated finished time: Pending
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                </div>
                <c:if test="${order.orderStatus != 'Completed'}">
                    <div class="progress-bar">
                        <span class="progress on-process ${order.orderStatus == 'Processing' ? 'active' : ''}"></span>
                        <span class="progress on-delivery ${order.orderStatus == 'On Delivery' ? 'active' : ''}"></span>
                        <span class="progress finished ${order.orderStatus == 'Completed' ? 'active' : ''}"></span>
                    </div>
                </c:if>
                <c:if test="${order.orderStatus == 'Completed'}">
                    <div class="status-icon">✔️</div>
                </c:if>
            </div>
        </c:forEach>
    </main>
</body>
</html>