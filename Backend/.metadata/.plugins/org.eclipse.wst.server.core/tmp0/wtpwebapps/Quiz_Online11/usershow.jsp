<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.quizz.entities.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>UserShow</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }

        table {
            border-collapse: collapse;
            width: 90%;
            margin: 0 auto 30px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            color: #333;
            text-transform: uppercase;
            font-size: 14px;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #e9f5ff;
        }

        a {
            display: inline-block;
            text-align: center;
            padding: 10px 20px;
            background-color: #337ab7;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin: 0 auto;
        }

        a:hover {
            background-color: #23527c;
        }

        .center {
            text-align: center;
        }
    </style>
</head>
<body>
    <h2>All Users</h2>

    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>
            <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
            %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4" class="center">No users found</td>
            </tr>
            <%
            }
            %>
        </tbody>
    </table>

    <div class="center">
        <a href="admin.html">Back to Admin</a>
    </div>
</body>
</html>
