<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>마이페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <sec:authentication property="principal.memDto" var="memInfo" />
    <div class="container">
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="card border-0 shadow-lg rounded-2 w-50">
                <div class="card-header">
                    <h2>본인 확인</h2>
                </div>
                <div class="card-body">
                    <form action="/mypage" method="post" name="toMypageForm" onsubmit="return toMypage();">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="form-group mb-2">
                            <label class="memId">아이디</label>
                            <input type="text" class="form-control-plaintext" id="memId" name="memId" value="${memInfo.memId}" readonly />
                        </div>
                        <div class="form-group mb-1">
                            <label for="memPw">비밀번호</label>
                            <input type="password" class="form-control" id="memPw" name="memPw" />
                        </div>
                        <span id="memPwErrMsg" class="text-danger"></span>
                        <div class="mt-2 text-end">
                            <button type="submit" class="btn btn-primary">확인</button>
                            <a href="/boards" class="btn btn-secondary" role="button">취소</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const memPw = document.querySelector('#memPw');
    const memPwErrMsg = document.querySelector('#memPwErrMsg');
    
    function toMypage(){
        if(memPw.value.trim() == ''){
            memPwErrMsg.textContent = '비밀번호를 입력해주세요.';
            return false;
        }
        return true;
    }

</script>
</html>