<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD MATERIAL</title>
    </head>
    <body>
        <h1>ADD PROMOTIONAL CONTENT</h1>
        <div>
            <form method="post" action="AddMaterialServlet">
            <label>NAME OF MATERIAL</label>
            <input type="text"  name="material_name"/>
            
            <label>IMAGE</label>
            <input type="text"  name="image_dir"/>
            
            <input type="submit" value="ADD MATERIAL"/>
        </form>
        </div>
    </body>
</html>
