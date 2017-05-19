<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 实现外接js文件也能够获取项目路径 -->
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="static/themes/icon.css">
<link rel="stylesheet" type="text/css" href="static/themes/default/easyui.css">
</head>