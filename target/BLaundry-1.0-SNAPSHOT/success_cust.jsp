<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Success - Customer</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }
        .success-message {
            text-align: center;
        }
        .success-message h1 {
            color: #f5a623;
            font-size: 2em;
        }
        .success-message p {
            color: #333;
            font-size: 1.2em;
        }
        .checkmark {
            display: inline-block;
            width: 20px;
            height: 20px;
            border: 2px solid #333;
            border-radius: 50%;
            position: relative;
            top: 5px;
            margin-left: 10px;
        }
        .checkmark::after {
            content: '';
            display: block;
            width: 10px;
            height: 5px;
            border: solid #333;
            border-width: 0 2px 2px 0;
            transform: rotate(-45deg) scale(-1, 1);
            position: absolute;
            top: 3px;
            left: 3px;
        }
    </style>
</head>
<body>
    <div class="success-message">
        <h1>Transaction success <span class="checkmark"></span></h1>
        <p>Returning to homepage...</p>
    </div>
    <script>
        setTimeout(function() {
            window.location.href = '${pageContext.request.contextPath}/customer/home';
        }, 2000);
    </script>
</body>
</html>