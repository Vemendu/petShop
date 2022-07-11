<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 10.07.2022
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
            grid-template-columns: 25% 25% 25% 25%;
        }
        .productItem {
            outline: 2px solid orange;
        }
        a.fill-div {
            display: block;
            height: 100%;
            width: 100%;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="gridLayout">
    <c:forEach items="${productList}" var="product">
        <div class="productItem">
            <p class="fill-div"></p>
            <p class="productTitle">${product.name}</p>
            <p class="productCost">${product.cost}</p>
            <p class="productInStock">${product.inStock}</p>
            <form action="ProductShow" method="get">
                <input type="hidden" name="productId" value="${product.id}" />
                <input type="submit" value="Show details" name="Show">
            </form>
        </div>
    </c:forEach>
</div>

</body>
</html>
