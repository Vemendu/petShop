<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>PetShop</title></head>
<style>
    .register_button_div, .basket_button_div {
        display: inline-block;
        position: relative;
    }

    .header {
        flex-direction: row;
        display: flex;
        width: 100%;
        height: max-content;
    }

    .categoryDiv {
        flex-direction: row;
        display: flex;
        width: 100%;
        height: max-content;
    }

    .buttons {
        justify-content: flex-end;
        flex-direction: row;
        display: flex;

        text-align: right;
        background-color: orange;
        width: 100%;
    }

    .btn1{
        position: absolute;
        bottom: 25%;
        right: 0;
        width: 100px;
        height: 40px;
    }
    .btn2{
        position: absolute;
        bottom: 25%;
        right: 100px;
        width: 100px;
        height: 40px;
    }
    .mainDiv {
        width: 100%;
    }
</style>
<body>

<div class="header">

    <div>
        <h1>PetShop</h1>
    </div>
    <div class="buttons">
        <div class="basket_button_div">
            <form action="BasketForward" method="get" id="basket_forward">
                <button name="basket" class="btn1 btn-primary text-right" type="submit" form="basket_forward">To Basket</button>
            </form>
        </div>

        <div class="register_button_div">
            <form action="RegisterForward" method="get" id="register_forward">
                <button name="register" class="btn2 btn-primary text-right" type="submit" form="register_forward">Register New Account</button>
            </form>
        </div>
    </div>


</div>
<div class="categoryDiv">
    <c:forEach items="${categoryList}" var="category">
        <form action="CategoryShow" method="get">
            <input type="hidden" name="categoryId" value="${category.id}" />
            <input type="submit" value="${category.name}" name="Show">
        </form>
    </c:forEach>
</div>


</body>

</html>