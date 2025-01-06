<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>B-Laundry</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_home_cust.css">
</head>
<body>
    <div class="gradient-container">
        <header>
            <nav>
                <div class="nav-left">
                    <a href="${pageContext.request.contextPath}/customer/home">Home</a>
                    <a href="${pageContext.request.contextPath}/customer/profile">My Profile</a>
                    <a href="${pageContext.request.contextPath}/customer/my_orders">My Order</a>
                </div>
                <div class="nav-right">
                    <a href="${pageContext.request.contextPath}/login_cust.jsp" id="logout-button" class="logout">Logout</a>
                </div>
            </nav>
        </header>
        <div class="welcome">
            <h1>Hello, ${user.username}</h1>
            <p>How's your day? Let us make your laundry fresh again!</p>
        </div>
    </div>
    <main>
        <div class="services">
            <div class="left-section">
                <div class="service" id="cloth_wash_iron">
                    <div class="icons">
                        <img src="${pageContext.request.contextPath}/img/iron.png" alt="Iron">
                        <img src="${pageContext.request.contextPath}/img/shirt.png" alt="Shirt">
                    </div>
                    <p>Cloth Wash + Iron</p>
                </div>
            </div>
            <div class="right-section">
                <div class="top-section">
                    <div class="service" id="cloth_iron">
                        <p>Cloth Iron</p>
                        <img src="${pageContext.request.contextPath}/img/iron.png" alt="Cloth Iron">
                    </div>
                </div>
                <div class="bottom-section">
                    <div class="service" id="shoe_wash">
                        <img src="${pageContext.request.contextPath}/img/shoe.png" alt="Shoe Wash">
                        <p>Shoe Wash</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <div class="footer-container">
            <div class="left-section">
                <div class="location">
                    <p><strong>Our Location:</strong></p>
                    <p>Jl. Sukabirus no 123, Bojongsoang, Kabupaten Bandung</p>
                </div>
                <div class="operational-hour">
                    <p><strong>Operational Hour:</strong></p>
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
    <script>
        document.getElementById('cloth_wash_iron').onclick = function() {
            window.location.href = '${pageContext.request.contextPath}/customer/cloth_wash_iron_order';
        };
        document.getElementById('cloth_iron').onclick = function() {
            window.location.href = '${pageContext.request.contextPath}/customer/cloth_iron_order';
        };
        document.getElementById('shoe_wash').onclick = function() {
            window.location.href = '${pageContext.request.contextPath}/customer/shoe_wash_order';
        };
        document.getElementById('logout-button').onclick = function() {
            window.location.href = '${pageContext.request.contextPath}/login_cust.jsp';
        };
    </script>
</body>
</html>