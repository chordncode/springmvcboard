<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center vh-100">
            <div class="card border-0 shadow-lg rounded-2 w-50 h-50">
                <div class="card-header">
                    <h2>회원가입</h2>
                </div>
                <div class="card-body">
                    <form action="/signup" method="post" name="signupForm">
                        <div class="form-group mb-2">
                            <label for="memId">아이디</label>
                            <input type="text" class="form-control" id="memId" name="memId" />
                        </div>
                        <div class="row mb-2">
                            <div class="col-md-6 form-group">
                                <label for="memPw">비밀번호</label>
                                <input type="password" class="form-control" id="memPw" name="memPw" />
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="memPw">비밀번호 확인</label>
                                <input type="password" class="form-control" id="memPwChk" name="memPwChk" />
                            </div>
                        </div>
                        <hr />
                        <div class="row mb-2">
                            <div class="col-md-6 form-group">
                                <label for="memNick">닉네임</label>
                                <input type="text" class="form-control" id="memNick" name="memNick" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="memEmil">이메일</label>
                            <input type="text" class="form-control" id="memEmail" name="memEmail" />
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </form>
                </div>
                <div class="card-footer text-end">
                    <button type="button" id="signupBtn" class="btn btn-primary">회원가입</button>
                    <button type="button" id="gobackBtn" class="btn btn-secondary">취소</button>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const signupBtn = document.querySelector('#signupBtn');
    const gobackBtn = document.querySelector('#gobackBtn');
    
    signupBtn.onclick = function(){
        document.signupForm.submit();
    }

    gobackBtn.onclick = function(){
        history.back();
    }
</script>
</html>