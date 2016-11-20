<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Problems</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            overflow: hidden;
            word-break: normal;
            border: 1px solid #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            overflow: hidden;
            word-break: normal;
            border: 1px solid #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>TODO List</h1>

<c:if test="${!empty listProblems}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Description</th>
            <th width="120">Status</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listProblems}" var="problem">
            <tr>
                <td>${problem.id}</td>
                <td>${problem.description}</td>
                <td>
                    <c:choose>
                        <c:when test="${problem.done}">
                            solved
                        </c:when>
                        <c:otherwise>
                            unsolved
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><a href="<c:url value='/edit/${problem.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${problem.id}'/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:choose>
    <c:when test="${page > 0}">
        <a href="/todo?page=${page - 1}&searchType=${searchType}"> prev </a>
    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>
&nbsp&nbsp
<c:choose>
    <c:when test="${(page < maxPage)}">
        <a href="/todo?page=${page + 1}&searchType=${searchType}"> next </a>
    </c:when>
    <%--page 0 size = 6  0 < -0.6  --%>
    <c:otherwise></c:otherwise>
</c:choose>
<br><br><br><br>
<a href="<c:url value='/todo?searchType=ALL'/>"> View all </a> &nbsp;
<a href="<c:url value='/todo?searchType=SOLVED'/>"> View solved </a> &nbsp;
<a href="<c:url value='/todo?searchType=UNSOLVED'/>"> View unsolved </a>
<h1>Add a Problem</h1>

<c:url var="addAction" value="/books/add"/>

<form:form action="${addAction}" commandName="problem">
    <table>
        <c:if test="${!empty problem.description}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Description"/>
                </form:label>
            </td>
            <td>
                <form:input path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:radiobutton path="done" value="true"/> Solved
                <form:radiobutton path="done" value="false"/> Unsolved
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty problem.description}">
                    <input type="submit"
                           value="<spring:message text="Edit Problem"/>"/>
                </c:if>
                <c:if test="${empty problem.description}">
                    <input type="submit"
                           value="<spring:message text="Add Problem"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


</body>
</html>
