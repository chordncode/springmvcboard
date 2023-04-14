<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="card border-0 shadow-lg rounded-2 w-50">
                <div class="card-header">
                    <h2>로그인</h2>
                </div>
                <div class="card-body">
                    <form action="/login" method="post" name="loginForm" onsubmit="return login();">
                        <div class="form-group mb-2">
                            <label for="memId">아이디</label>
                            <input type="text" class="form-control" id="memId" name="memId" value="${requestScope.rememberId}" />
                        </div>
                        <div class="form-group mb-2">
                            <label for="memPw">비밀번호</label>
                            <input type="password" class="form-control" id="memPw" name="memPw" />
                        </div>
                        <div class="form-check mb-2">
                            <input type="checkbox" class="form-check-input" id="rememberId" name="rememberId" value="Y"
                                <c:if test="${requestScope.rememberId != null and requestScope.rememberId != ''}">checked</c:if>
                            />
                            <label for="rememberId" class="form-check-label">아이디 저장</label>
                        </div>
                        <div class="text-center mb-3">
                            <a href="#" class="link-secondary">아이디 찾기</a>
                            <a href="#" class="link-secondary ms-2">비밀번호 찾기</a>
                        </div>
                        <div class="my-4 mx-4 d-grid">
                            <button type="submit" class="btn btn-primary">로그인</button>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <hr />
                    </form>
                    <div class="form-group text-center">
                        <span><strong>아직 회원이 아니신가요?</strong></span><br />
                        <a href="/signup" class="link-secondary">회원가입</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const loginForm = document.loginForm;
    const memId = document.querySelector('#memId');
    const memPw = document.querySelector('#memPw');

    function login(){

        if(!memId.value.trim()){
            alert('아이디를 입력해주세요.');
            return false;
        }

        if(!memPw.value.trim()){
            alert('비밀번호를 입력해주세요.');
            return false;
        }
        
        return true;

    }

</script>
</html>