<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <!-- Bootstrap CSS -->
    <link 
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" 
        rel="stylesheet" />
    <link rel="stylesheet" href="/css/bootstrap.min.css">
</head>
<body class="bg-light">
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg p-4 text-center" style="max-width: 400px;">
            <h1 class="card-title mb-3">Welcome to the Todo App!</h1>
            <p class="card-text text-muted">Your tasks are just one click away. Get started by exploring your todo list.</p>
            <a href="todos" class="btn btn-primary btn-lg mt-3">Go to Todos</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" ></script>
    <script src="/js/bootstrap.bundle.min.js"></script>
</body>
</html>


