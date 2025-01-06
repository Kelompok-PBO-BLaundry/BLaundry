<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cloth Wash + Iron</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/washIron.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/customer/home">Home</a>
            <a href="${pageContext.request.contextPath}/customer/profile">My Profile</a>
            <a href="${pageContext.request.contextPath}/customer/my_orders" class="active">My Order</a>
        </div>
        <a href="${pageContext.request.contextPath}/login_cust.jsp" class="logout">Logout</a>
    </nav>

    <header class="header">
        <h1>Cloth Wash + Iron</h1>
        <p>Complete care for your clothes – washed, ironed, and ready to wear</p>
    </header>

    <div class="main-section">
        <div class="content">
            <div class="shoe-icon">
                <img src="${pageContext.request.contextPath}/img/wr.png" alt="Wash+Iron Icon">
                <p>Cloth Wash + Iron</p>
            </div>
            <div class="order-box">
                <div class="order-form">
                    <form action="${pageContext.request.contextPath}/customer/placeOrder" method="POST">
                        <input type="hidden" name="service_type" value="Wash">
                        <div class="form-group">
                            <label for="weight">Weight (Kg):</label>
                            <input type="number" id="weight" name="weight" min="1" placeholder="Enter weight" required>
                        </div>
                        <div class="form-group">
                            <label for="number_of_items">Number of items:</label>
                            <input type="number" id="number_of_items" name="number_of_items" min="1" placeholder="Enter number" required>
                        </div>
                        <div class="form-group">
                            <label>Duration :</label>
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
                            <label>Perfume:</label>
                            <div class="radio-group">
                                <label>
                                    <input type="radio" name="perfurme" value="Fresh Blossom" required> Fresh Blossom
                                </label>
                                <label>
                                    <input type="radio" name="perfurme" value="Sweet Lavender" required> Sweet Lavender
                                </label>
                            </div>
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
        document.querySelectorAll('input[name="duration"], input[name="perfurme"]').forEach((elem) => {
            elem.addEventListener("change", calculateTotal);
        });

        document.getElementById('weight').addEventListener("input", calculateTotal);

        function calculateTotal() {
            const weight = document.getElementById('weight').value;
            const duration = document.querySelector('input[name="duration"]:checked')?.value;
            const perfume = document.querySelector('input[name="perfurme"]:checked')?.value;
            let total = 0;

            if (weight && duration && perfume) {
                if (duration === "1-day") {
                    total = weight * 8000;
                } else if (duration === "3-days") {
                    total = weight * 7000;
                }

                document.getElementById('total_amount').value = total;
            }
        }
    </script>
</body>
</html>
