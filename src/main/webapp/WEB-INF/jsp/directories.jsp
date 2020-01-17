<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <title>Directories and files</title>
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="shortcut icon" type="image/x-icon" href="/resources/img/favicon.ico">
</head>

<body>
<header>Directories and files</header>

<div class="wrapper">
    <div id="content-table-dirs">

        <%--        ADD NEW DIRECTORY --%>
        <div class="text-good-news">Insert path of new directory:</div>
        <form action="/directories" method="post">
            <div class="form-element">
                <input id="directoryName" size="60" name="directoryName" type="text" placeholder="Path" required>
                <label class="error-message">${directoryNameError}</label>
                <label class="text-good-news">${directoryNameSuccess}</label>
            </div>

            <input type="submit" value="Add directory">
        </form>

        <%--            SHOW DIRECTORIES --%>
        <table id="table-dirs">
            <tr>
                <th>Added</th>
                <th>Path</th>
                <th>Directories</th>
                <th>Files</th>
                <th>Size of files</th>
                <th>Browse</th>
            </tr>

            <c:forEach var="directory" items="${directories}">
                <tr>

                    <fmt:parseDate value="${directory.added}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <td><fmt:formatDate pattern="dd-MM-yyyy HH:mm" value="${parsedDateTime}"/></td>
                    <td>${directory.path}</td>
                    <td>${directory.numberOfDirectories}</td>
                    <td>${directory.numberOfFiles}</td>

                    <c:if test="${directory.sizeOfFiles < 1024}">
                        <td>${directory.sizeOfFiles} B</td>
                    </c:if>
                    <c:if test="${directory.sizeOfFiles >= 1024 && directory.sizeOfFiles < 1048576}">
                        <td><fmt:formatNumber value="${directory.sizeOfFiles / 1024}" maxFractionDigits="2"/> KB</td>
                    </c:if>
                    <c:if test="${directory.sizeOfFiles >= 1048576 && directory.sizeOfFiles < 1073741824}">
                        <td><fmt:formatNumber value="${directory.sizeOfFiles / 1048576}" maxFractionDigits="2"/> MB</td>
                    </c:if>
                    <c:if test="${directory.sizeOfFiles >= 1073741824}">
                        <td><fmt:formatNumber value="${directory.sizeOfFiles / 1073741824}" maxFractionDigits="2"/> GB
                        </td>
                    </c:if>

                    <td><a target="_blank" href="/files?id=${directory.directoryId}">Files</a></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form action="/delete" method="get">
            <input type="submit" value="Delete all">
        </form>

    </div>
</div>

</body>
</html>
