<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-light bg-light">
    <div class="container-fluid">
        <a href="/boards" class="navbar-brand"><h2 class="m-0">익명게시판</h2></a>
        <div class="row g-2" style="min-width: 200px;">
            <a href="/toMypage" class="btn btn-success col-md-6" role="button">마이페이지</a>
            <form action="/logout" method="post" class="col-md-6">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit" class="btn btn-danger w-100">로그아웃</button>
            </form>
        </div>
    </div>
</div>