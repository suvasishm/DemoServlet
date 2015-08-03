<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Tag Example</title>
</head>
<body>
<c:forEach var="item" items="${items}">
   <p>${item.id} --- ${item.name}</p>
</c:forEach>
</body>
</html>