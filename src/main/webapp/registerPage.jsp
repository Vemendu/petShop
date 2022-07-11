<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>

<head>
    <title>Title</title></head>

<body>

<form action="Register" method="post" id="register_user">
    <div class="form-row">
        <div class="col-md-4 mb-3">
            <label for="firstName">First Name</label>
            <input name="firstName" type="text" class="form-control" id="firstName"
                   placeholder="Input your first name" maxlength="10" required>
        </div>
        <div class="col-md-4 mb-3">
            <label for="lastName">Surname</label>
            <input name="lastName" type="text" class="form-control" id="lastName"
                   placeholder="Input your surname" maxlength="10" required>
        </div>

    </div>
    <div class="form-row">
        <div class="col-md-3 mb-3">
            <label for="birthdayDate">Birthday Date</label>
            <input name="birthdayDate" type="date" class="form-control" id="birthdayDate"
                   value="2004-06-27"
                   min="1922-01-01" max="2010-01-01" required >
        </div>
        <div class="col-md-3 mb-3">
            <label for="phoneNumber">Phone number</label>
            <input name="phoneNumber" type="text" class="form-control" id="phoneNumber"
                   placeholder="Input your phone number" maxlength="15" required>
            <%--            <c:if test="${requestScope.notCorrectPhone != null}">--%>
            <%--                <small class="text-danger"><fmt:message key="error.phone"/></small>--%>
            <%--            </c:if>--%>
        </div>
        <div class="col-md-3 mb-3">
            <label for="address">Address</label>
            <input name="address" type="text" class="form-control" id="address"
                   placeholder="Input your address" maxlength="15" required>
        </div>
        <div class="col-md-3 mb-3">
            <label for="language">Language</label>
            <input name="language" type="text" class="form-control" id="language"
                   placeholder="Input your language" maxlength="15" required>
        </div>
        <div class="col-md-3 mb-3">
            <label for="country">Country</label>
            <input name="country" type="text" class="form-control" id="country"
                   placeholder="Input your country" maxlength="15" required>
        </div>

        <div class="col-md-3 mb-3">
            <input name="roleId" type="hidden" class="form-control" id="roleId"
                   value="1" maxlength="15" required>
        </div>

        <div class="col-md-4 mb-3">
            <label for="username">Input your login</label>
            <div class="input-group">
                <input name="username" type="text" class="form-control" id="username"
                       placeholder="Input your username" maxlength="10"
                       aria-describedby="inputGroupPrepend2" required>
            </div>
            <%--            <c:if test="${requestScope.loginIsExist != null}">--%>
            <%--                <small class="text-danger"><fmt:message key="error.login.exists"/></small>--%>
            <%--            </c:if>--%>
        </div>
        <div class="col-md-3 mb-3">
            <label for="password">Password</label>
            <input name="password" type="password" class="form-control" id="password"
                   placeholder="Input your password" maxlength="8" required>
            <%--            <c:if test="${requestScope.notCorrectPassword != null}">--%>
            <%--                <small class="text-danger"><fmt:message key="error.password"/></small>--%>
            <%--            </c:if>--%>
        </div>
        <div class="col-md-6 mb-3">
            <label for="email">Email</label>
            <input name="email" type="text" class="form-control" id="email"
                   placeholder="Input your email" required>
            <%--            <c:if test="${requestScope.notCorrectEmail != null}">--%>
            <%--                <small class="text-danger"><fmt:message key="error.email"/></small>--%>
            <%--            </c:if>--%>
        </div>
    </div>
    <button name="submit" class="btn btn-primary text-right" type="submit" form="register_user">Register</button>
</form>
<form action="LoginForward" method="get" id="login_forward">
    <button name="login" class="btn btn-primary text-right" type="submit" form="login_forward">Already have an account? Log in!</button>
</form>
</body>

</html>