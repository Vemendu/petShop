<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product page</title>
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
        .basket_button_div {
            display: inline-block;
            position: relative;
        }
    </style>
</head>
<body>
<div class="gridLayout">
    <div class="productItem">
        <p></p>
        <p class="productTitle">${product.name}</p>
        <p>${product.description}</p>
        <p class="productCost">${product.cost}</p>
        <p class="productInStock">${product.inStock}</p>
        <div class="basket_button_div">
            <form action="BasketAdd" method="get" id="basket_add">
                <input type="hidden" name="productId" value="${product.id}" />
                <button name="basket_add" class="btn1 btn-primary text-right" type="submit" form="basket_add">Add to Cart</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
