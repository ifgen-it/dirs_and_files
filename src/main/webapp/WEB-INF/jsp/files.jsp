<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${directory.path}</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>
<body>
<div class="wrapper">
    <div id="content-files">

        <%--         DIRECTORY INFO --%>
        <c:if test="${directory == null}">
            <div class="text-bad-news">Error! There is no such directory!</div>
        </c:if>
        <c:if test="${directory != null}">

            <fmt:parseDate value="${directory.added}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
            <div class="text-good-news">${directory.path} <fmt:formatDate pattern="dd-MM-yyyy HH:mm"
                                                                     value="${parsedDateTime}"/></div>
            <br>
            <table id="table-files">
                <tr>
                    <th>File name</th>
                    <th>Size</th>
                </tr>

                <c:forEach var="file" items="${files}">
                    <tr>
                        <td>${file.fileName}</td>
                        <c:if test="${file.size == null}">
                            <td>&lt;DIR&gt;</td>
                        </c:if>
                        <c:if test="${file.size != null}">

                            <c:if test="${file.size < 1024}">
                                <td>${file.size} B</td>
                            </c:if>
                            <c:if test="${file.size >= 1024 && file.size < 1048576}">
                                <td><fmt:formatNumber value="${file.size / 1024}" maxFractionDigits="2"/> KB</td>
                            </c:if>
                            <c:if test="${file.size >= 1048576 && file.size < 1073741824}">
                                <td><fmt:formatNumber value="${file.size / 1048576}" maxFractionDigits="2"/> MB</td>
                            </c:if>
                            <c:if test="${file.size >= 1073741824}">
                                <td><fmt:formatNumber value="${file.size / 1073741824}" maxFractionDigits="2"/> GB</td>
                            </c:if>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

</body>
</html>