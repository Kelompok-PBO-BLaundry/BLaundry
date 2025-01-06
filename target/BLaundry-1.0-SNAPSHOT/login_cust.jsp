<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>B-Laundry</title>
    <link href='https://fonts.googleapis.com/css?family=Poppins' rel='stylesheet'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_login_cust.css">
</head>
<body>
    <div class="gradient-container text-center">
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-transparent">
                <div class="container">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/register_cust.jsp">B-Laundry</a>
                    <div class="ml-auto">
                        <a href="${pageContext.request.contextPath}/login_cust.jsp" class="btn btn-primary">Login</a>
                    </div>
                </div>
            </nav>
        </header>
        <div class="welcome">
            <h1>Hello, Please Login</h1>
            <p>How's your day? Let us make your laundry fresh again!</p>
        </div>
    </div>

    <main class="content d-flex justify-content-center align-items-center">
        <div class="form-container bg-white p-4 rounded shadow">
            <h2>Login</h2>
            <form action="${pageContext.request.contextPath}/loginCustomer" method="POST">
                <div class="form-group">
                    <label for="login-username">Username:</label>
                    <input type="text" id="login-username" name="username" class="form-control" placeholder="Enter your username" required>
                </div>
                <div class="form-group">
                    <label for="login-password">Password:</label>
                    <input type="password" id="login-password" name="password" class="form-control" placeholder="Enter your password" required>
                </div>
                <button type="submit" class="btn btn-warning btn-block">Login</button>
            </form>
            <p class="link-text mt-3">
                Have no account? <a href="${pageContext.request.contextPath}/register_cust.jsp" id="regist-link" class="highlight-link">Register</a>
            </p>
            <p class="link-text mt-3">
                Login to admin account? <a href="${pageContext.request.contextPath}/login_admin.jsp" id="admin-login-link" class="highlight-link">loginadmin</a>
            </p>
        </div>
    </main>

    <footer class="footer mt-auto py-3 bg-warning">
        <div class="container">
            <div class="row">
                <div class="col-md-6 footer-left text-left">
                    <h3>Our Location:</h3>
                    <p>Jl. Sukabirus no123, Bojongsoang, Kabupaten Bandung</p>
                    <p>Operational hour: 09.00-17.00</p>
                </div>
                <div class="col-md-6 footer-right text-right">
                    <h3>Contact us at:</h3>
                    <p>+62812345678</p>
                    <p>blaundry@gmail.com</p>
                </div>
            </div>
            <div class="footer-center text-center mt-3">
                <p>© 2024 BLaundry. All rights reserved.</p>
            </div>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pjaaA8dDz/ohwJAR42twvKVZ5iwhs9VVnw4ro/9JRQ/pGlYfRvH+6QQF26vQYjQe" crossorigin="anonymous"></script>
</body>
</html>