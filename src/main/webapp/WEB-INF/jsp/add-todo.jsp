<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <title>Add Todo</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container">
    
        <h1 class="my-4">Add Todo</h1>
        
        <form id="todoForm" action="/todos" method="post">
		    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="text" id="description" name="description" 
                       class="form-control ${not empty errors['description'] ? 'is-invalid' : ''}" 
                       value="${todo.description}" required>
                <div class="invalid-feedback">
                    ${errors['description']}
                </div>
            </div>
            <div class="mb-3">
                <label for="targetDate" class="form-label">Target Date</label>
                <input type="text" id="targetDate" name="targetDate" class="form-control datepicker" value="${todo.targetDate}" required>

            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="/todos" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script>
        $(document).ready(function () {
            $('.datepicker').datepicker({
                format: 'yyyy-mm-dd',
                autoclose: true,
                todayHighlight: true 
            });

            $('#todoForm').submit(function (event) {
            	//event.preventDefault();
                console.log('Form submission triggered');
                console.log('Description:', $('#description').val());
                console.log('Target Date:', $('#targetDate').val());
            });
        });
    </script>
</body>
</html>
