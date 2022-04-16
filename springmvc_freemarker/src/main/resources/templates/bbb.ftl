<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>bbb</title>
</head>
<body>

    <table border="1" width="50%" align="center">
        <tr >
            <th>姓名bbb</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>

        <#list users as user>
        <tr>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.sex}</td>
        </tr>
        </#list>
    </table>

</body>
</html>