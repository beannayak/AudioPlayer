<%-- 
    Document   : getSomethingFromCheckBox
    Created on : Feb 8, 2016, 10:21:29 PM
    Author     : binayak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="/AudioPlayerProject/rhythm/testCheckBox" method="POST">
            <input type="checkbox" name="checkboxText" id="checkboxText" value="true" checked="true">true</input>
            <input type="submit" value="submit" />
        </form>
    </body>
</html>
