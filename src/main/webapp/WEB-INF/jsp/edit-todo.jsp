<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
    <title>Edit Todo</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container">
        <h1 class="my-4">Edit Todo</h1>
        
        <form action="/todos/update/${todo.id}" method="post">
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <input type="text" id="description" name="description" class="form-control" value="${todo.description}" required>
                <c:if test="${not empty errors['description']}">
                    <div class="text-danger">${errors['description']}</div>
                </c:if>
            </div>
            
            <div class="mb-3">
                <label for="targetDate" class="form-label">Target Date</label>
                <input type="text" id="targetDate" name="targetDate" class="form-control datepicker" value="${todo.targetDate}" required>
                <c:if test="${not empty errors['targetDate']}">
                    <div class="text-danger">${errors['targetDate']}</div>
                </c:if>
            </div>
            
            <div class="mb-3">
                <label for="done" class="form-label">Is Done</label>
                <select id="done" name="done" class="form-control">
                    <option value="true" ${todo.done ? 'selected' : ''}>Yes</option>
                    <option value="false" ${!todo.done ? 'selected' : ''}>No</option>
                </select>
                <c:if test="${not empty errors['done']}">
                    <div class="text-danger">${errors['done']}</div>
                </c:if>
            </div>
            
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="/todos" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script>
    $(document).ready(function () {
        $('#targetDate').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });
    });
    </script>
</body>
</html>
