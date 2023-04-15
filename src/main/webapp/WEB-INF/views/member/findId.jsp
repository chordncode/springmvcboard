<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>아이디 찾기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="card border-0 shadow-lg rounded-2 w-50">
                <div class="card-header">
                    <h2>아이디 찾기</h2>
                </div>
                <div class="card-body">
                    <form action="/findId" method="post" name="findIdForm" onsubmit="return findId();">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="form-group mb-1">
                            <label for="memEmail">이메일</label>
                            <input type="text" class="form-control" id="memEmail" name="memEmail" />
                        </div>
                        <span id="memEmailErrMsg" class="text-danger"></span>
                        <div class="mt-2 text-end">
                            <button type="submit" class="btn btn-primary">아이디 찾기</button>
                            <a href="/login" class="btn btn-secondary" role="button">취소</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const memEmail = document.querySelector('#memEmail');
    const memEmailErrMsg = document.querySelector('#memEmailErrMsg');
    
    function findId(){
        if(memEmail.value.trim() == ''){
            memEmailErrMsg.textContent = '이메일을 입력해주세요.';
            return false;
        }
        return true;
    }

</script>
</html>