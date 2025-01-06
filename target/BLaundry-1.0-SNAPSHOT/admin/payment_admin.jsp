<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment - Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_payment.css">
</head>
<body>
    <div class="gradient-container">
        <header>
            <nav>
                <div class="nav-left">
                    <a href="${pageContext.request.contextPath}/admin/home">Home</a>
                    <a href="${pageContext.request.contextPath}/admin/admin_orders">My Orders</a>
                </div>
                <div class="nav-right">
                    <a href="${pageContext.request.contextPath}/login_admin.jsp" class="logout">Logout</a>
                </div>
            </nav>
        </header>
        <div class="nav-middle">
            <h1>Admin Payment</h1>
        </div>
    </div>
    <div class="container">
        <form id="payment-form" action="${pageContext.request.contextPath}/admin/payment" method="POST">
            <input type="hidden" name="order_id" value="${order.orderId}">
            <input type="hidden" name="total_amount" value="${order.totalAmount}">
            <input type="hidden" name="user_type" value="employee">
            <strong class="detail-transaction">Detail Transaction</strong>
            <div class="flex-container">
                <div class="form-section">
                    <div class="form-group">
                        <label for="order-type">Order Type:</label>
                        <input type="text" id="order-type" name="order-type" value="${order.serviceType}" readonly>
                    </div>
                    <div class="form-group">
                        <label for="total-amount-display">Total Amount:</label>
                        <input type="text" id="total-amount-display" value="${order.totalAmount}" readonly>
                    </div>
                    <div class="payment-method">
                        <div class="form-group">
                            <label for="payment-method">Payment Method:</label>
                            <label>
                                <input type="radio" name="payment_method" value="QRIS" required> QRIS
                            </label>
                            <label>
                                <input type="radio" name="payment_method" value="Transfer" required> Transfer
                            </label>
                        </div>
                    </div>
                    <div class="transfer-info" style="display: none;">
                        Transfer to this virtual account: <span class="highlight">123456789</span>
                    </div>
                </div>
                <div class="qr-code" style="display: none;">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/d/d0/QR_code_for_mobile_English_Wikipedia.svg" alt="QR Code">
                </div>
            </div>
            <button type="submit" class="btn">DONE</button>
        </form>
    </div>
    <script>
    function toggleQRCode() {
        const qrisRadio = document.querySelector('input[name="payment_method"][value="QRIS"]');
        const transferRadio = document.querySelector('input[name="payment_method"][value="Transfer"]');
        const qrCode = document.querySelector('.qr-code');
        const transferInfo = document.querySelector('.transfer-info');
        const container = document.querySelector('.container');

        if (qrisRadio.checked) {
            qrCode.style.display = 'block';
            transferInfo.style.display = 'none';
            container.style.width = '700px';
        } else if (transferRadio.checked) {
            qrCode.style.display = 'none';
            transferInfo.style.display = 'block';
            container.style.width = '500px';
        } else {
            qrCode.style.display = 'none';
            transferInfo.style.display = 'none';
            container.style.width = '500px';
        }
    }

    window.onload = function() {
        const radios = document.querySelectorAll('input[name="payment_method"]');
        radios.forEach(radio => {
            radio.addEventListener('change', toggleQRCode);
        });

        toggleQRCode();
    }
</script>

</body>
</html>