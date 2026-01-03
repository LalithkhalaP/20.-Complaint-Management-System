<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Status</title>
</head>
<body>

<h2>View Complaint Status</h2>

<form action="ComplaintServlet" method="get">
    Complaint ID:
    <input type="number" name="cid" required>
    <input type="submit" value="Check Status">
</form>

<br>

<%
    String status = (String) request.getAttribute("status");
    if (status != null) {
%>
    <h3>Status: <%= status %></h3>
<%
    }
%>

</body>
</html>
