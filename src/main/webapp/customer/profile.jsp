<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BLaundry</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_profile.css">
</head>
<body>
    <div class="gradient-container">
        <header>
            <nav>
                <div class="nav-left">
                    <a href="${pageContext.request.contextPath}/customer/home">Home</a>
                    <a href="${pageContext.request.contextPath}/customer/my_orders">Order</a>
                </div>
                <div class="nav-right">
                    <a href="${pageContext.request.contextPath}/login_cust.jsp" class="logout">Logout</a>
                </div>
            </nav>
        </header>
        <div class="welcome">
            <h1>My Profile</h1>
        </div>
    </div>
    <div class="profile-container">
        <div class="profile-form-container">
            <div class="profile-header">
                <div class="profile-picture"></div>
                <form action="${pageContext.request.contextPath}/customer/profile" method="POST" id="profile-form" class="profile-form">
                    <div class="profile-info">
                        <div class="info-item">
                            <span class="label">Username</span>
                            <div class="input-container">
                                <input type="text" name="username" value="${user.username}" readonly>
                                <button type="button" class="edit-btn" onclick="makeEditable(this)">✏️</button>
                            </div>
                        </div>
                        <div class="info-item">
                            <span class="label">Password</span>
                            <div class="input-container">
                                <input type="password" name="password" value="" placeholder="Change password" data-hidden="true">
                                <button type="button" class="edit-btn" onclick="togglePasswordVisibility(this)">👁️</button>
                            </div>
                        </div>
                        <div class="info-item">
                            <span class="label">Phone Number</span>
                            <div class="input-container">
                                <input type="text" name="phone_number" value="${user.phoneNumber}" readonly>
                                <button type="button" class="edit-btn" onclick="makeEditable(this)">✏️</button>
                            </div>
                        </div>
                        <div class="info-item">
                            <span class="label">Address</span>
                            <div class="input-container">
                                <input type="text" name="address" value="${user.address}" readonly>
                                <button type="button" class="edit-btn" onclick="makeEditable(this)">✏️</button>
                            </div>
                        </div>
                    </div>
                    <div class="action-buttons">
                        <input type="hidden" name="action" id="action-input" value="update">
                        <button type="submit" name="update_profile" class="save-btn">Save Changes</button>
                        <button type="submit" name="delete_account" class="delete-btn" onclick="confirmDelete()">Delete Account</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <footer>
    </footer>
    <script>
        function makeEditable(button) {
            const input = button.previousElementSibling;
            if (input.hasAttribute('readonly')) {
                input.removeAttribute('readonly');
                input.focus();
            }
        }

        function togglePasswordVisibility(button) {
            const input = button.previousElementSibling;
            const isHidden = input.getAttribute('data-hidden') === 'true';

            if (isHidden) {
                input.type = 'text';
                input.setAttribute('placeholder', 'Enter new password');
                input.setAttribute('data-hidden', 'false');
                button.textContent = "🙈";
            } else {
                input.type = 'password';
                input.removeAttribute('placeholder');
                input.setAttribute('data-hidden', 'true');
                button.textContent = "👁️";
            }
        }

        function confirmDelete() {
            if (confirm('Are you sure you want to delete your account? This action cannot be undone.')) {
                document.getElementById('action-input').value = 'delete';
                document.getElementById('profile-form').submit();
            }
        }

          document.getElementById('profile-form').addEventListener('submit', function(event) {
            const passwordInput = document.querySelector('input[name="password"]');
            
            if (passwordInput.value.length > 0 && passwordInput.value.length < 8) {
                event.preventDefault();
                alert('Password must be at least 8 character long');
            }
        });
    </script>
</body>
</html>