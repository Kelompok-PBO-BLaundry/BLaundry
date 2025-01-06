<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shoe Wash Administrator</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shoes_admin.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/admin/home">Home</a>
            <a href="${pageContext.request.contextPath}/admin/admin_orders" class="active">Order</a>
        </div>
        <a href="${pageContext.request.contextPath}/login_admin.jsp" class="logout">Logout</a>
    </nav>

    <header class="header">
        <h1>Shoe Wash</h1>
        <p>Restore your shoes to their fresh and clean like new</p>
    </header>

    <div class="main-section">
        <div class="content">
            <div class="shoe-icon">
                <img src="${pageContext.request.contextPath}/img/shoe.png" alt="Shoe Icon">
                <p>Shoe Wash</p>
            </div>
            <div class="order-box">
                <div class="order-form">
                    <form action="${pageContext.request.contextPath}/admin/placeOrder" method="POST">
                        <input type="hidden" name="service_type" value="Shoe Wash">
                        <input type="hidden" name="user_type" value="employee">
                        <input type="hidden" id="weight" name="weight" value = "0"/>   
                        <div class="form-group">
                            <label for="number_of_items">Number of items:</label>
                            <input type="number" id="number_of_items" name="number_of_items" min="1" placeholder="Enter number" required>
                        </div>
                        <div class="form-group">
                            <label for="duration">Duration:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="duration" value="1-day" required> 1 day (express)
                                </label>
                                <label>
                                    <input type="radio" name="duration" value="3-days" required> 3 days (normal)
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="customer_name">Customer Name:</label>
                            <input type="text" id="customer_name" name="customer_name" placeholder="Enter customer name" required>
                        </div>
                        <div class="form-group">
                            <label for="phone_number">Phone Number:</label>
                            <input type="number" id="phone_number" name="phone_number" min="1" placeholder="Enter phone number" required>
                        </div>
                        <div class="form-group">
                            <label for="delivery_address">Address:</label>
                            <input type="text" id="delivery_address" name="delivery_address" class="wide-input" placeholder="Enter address" required>
                        </div>
                        <div class="form-group">
                            <label for="total_amount">Total Amount:</label>
                            <input type="number" id="total_amount" name="total_amount" readonly>
                        </div>
                        <button type="submit" class="payment-btn">Continue to payment</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.querySelectorAll('input[name="duration"]').forEach((elem) => {
            elem.addEventListener("change", calculateTotal);
        });

        document.getElementById('number_of_items').addEventListener("input", calculateTotal);

        function calculateTotal() {
            const number_of_items = document.getElementById('number_of_items').value;
            const duration = document.querySelector('input[name="duration"]:checked')?.value;
            let total = 0;

            if (number_of_items && duration) {
                total = number_of_items * 15000;
                document.getElementById('total_amount').value = total;
            }
        }
    </script>
</body>
</html>