<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>agent page</title>
    </head>
    <body>
        <h1>AGENT LOGIN</h1>
        
        <form method="get" action="LoginServlet">
            <label>Username: </label>
            <input type="text" name="username"/><br>
            
            <label>Password: </label>
            <input type="password" name="password"/><br>
            
            <input type="submit" value="LOGIN"/>
        </form>

        
    </body>
</html>
