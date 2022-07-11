<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div>
        <p>Login</p>
        <form action="Login" method="post" id="login_user">
            <div class="col-md-4 mb-3">
                <label for="username">Username</label>
                <input name="username" type="text" class="form-control" id="username"
                       placeholder="Input your username" maxlength="10" required>
            </div>
            <div class="col-md-4 mb-3">
                <label for="password">Password</label>
                <input name="password" type="text" class="form-control" id="password"
                                                    placeholder="Input your password" maxlength="10" required>
            </div>
            <button name="submit" class="btn btn-primary text-right" type="submit" form="login_user">Login</button>
        </form>
        <form action="RegisterForward" method="get" id="register_forward">
            <button name="register" class="btn btn-primary text-right" type="submit" form="register_forward">Register New Account</button>
        </form>
    </div>
</body>
</html>
