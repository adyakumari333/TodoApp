<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css">
    <title>Todos</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/navbar.jspf" %>
    <div class="container">
        <h1 class="my-4">Todo List</h1>
        <a href="/todos/add" class="btn btn-primary mb-3">Add New Todo</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="todo" items="${todos}">
                    <tr>
                        
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done ? 'Yes' : 'No'}</td>
                        <td>
                            <a href="/todos/edit/${todo.id}" class="btn btn-info btn-sm">Edit</a>
                            <a href="/todos/delete/${todo.id}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
