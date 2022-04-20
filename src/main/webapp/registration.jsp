
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<head>
    <title>Registration Page</title>

    </head>

<form action="/user/registration" method="post">
<body>

<div class="col-md-6" style="background-color: blue">

</div>

<div class="container w-25 mt-5">
    <div class="row col-md-6 col-md-offset-3">
        <div class="panel panel-primary">
            <div class="panel-heading text-center">
                <h1>Registration Form</h1>
            </div>
            <div class="panel-body">

                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="text" name="phone" class="form-control" id="phone">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text" name="password" class="form-control" id="password">
                    </div>
                    <div class="form-group">
                        <label for="confirm password">Confirm Password</label>
                        <input type="text" name="confirm_password" class="form-control" id="confirm password">
                    </div>
                    <input type="submit" class="btn btn-primary">


            </div>
            <div class="panel-footer">
              <small>&copy;InternetProvider</small>

            </div>
        </div>
    </div>
</div>
    </body>
</form>
