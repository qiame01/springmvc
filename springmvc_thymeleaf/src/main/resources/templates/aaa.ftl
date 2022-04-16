<!DOCTYPE html>
<!--<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml"> 也行-->
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>aaa-ftl</title>
</head>
<body>
    <!--thymeleaf：字符串-->

    <div th:text="渲染常量">这里的文件不会展示</div>
    <div th:text="${msg}">渲染变量</div>

    aaa-未加th，对比bbb：<input type="text" value="${msg}" id="aaa">
    <br><!--可以将model里的值放入input的value属性里，注意只用于input标签-->
    bbb：<input type="text" th:value="${msg}" id="bbb">

    <div th:text="${#strings.isEmpty(msg1)}"></div>
    <div th:text="${#strings.isEmpty(msg2)}"></div>
    <div th:text="${#strings.isEmpty(msg3)}"></div>

</body>
</html>
