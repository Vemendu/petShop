<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Basket page</title>
    <style>
        .productTitle {
            font-size: 30px;
        }
        .productCost {
            font-size: 25px;
        }
        .productInStock {
            font-size: 23px;
        }
        .gridLayout {
            display: grid;
            grid-template-columns: 100%;
        }
        .productItem {
            outline: 2px solid orange;
        }
        .basket_button_div {
            display: inline-block;
            position: relative;
        }
    </style>
</head>
<body>
<div class="gridLayout">
    <c:forEach items="${productsList}" var="product">
        <div class="productItem">
            <p class="fill-div"></p>
            <p class="productTitle">${product.name}</p>
            <form action="MainPageForward" method="get" id="basket_add">
                <button name="basket_add" class="btn1 btn-primary text-right" type="submit" form="basket_add">Return to main page</button>
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>
