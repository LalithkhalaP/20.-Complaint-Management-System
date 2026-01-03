<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Complaint Registration</title>
</head>
<body>

<h2>Submit Complaint</h2>

<form action="ComplaintServlet" method="post">
    Complaint Issue:<br>
    <textarea name="issue" required></textarea><br><br>
    <input type="submit" value="Submit Complaint">
</form>

<%
    String msg = (String) request.getAttribute("msg");
    if (msg != null) {
%>
    <p style="color:green;"><b><%= msg %></b></p>
<%
    }
%>

<br>
<a href="viewStatus.jsp">Check Complaint Status</a>

</body>
</html>
