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
                    <h2>마이페이지</h2>
                </div>
                <div class="card-body">
                    <div class="form-group mb-1">
                        <label for="memId">아이디</label>
                        <input type="text" class="form-control-plaintext" id="memId" name="memId" value="${memInfo.memId}" readonly />
                    </div>
                    <div class="row">
                        <div class="col-md-6 form-group mt-2">
                            <label for="memPw">새 비밀번호</label>
                            <input type="password" class="form-control" id="memPw" name="memPw" disabled />
                        </div>
                        <div class="col-md d-flex justify-content-start align-items-end">
                            <button type="button" id="memPwChangeBtn" class="btn btn-danger">변경하기</button>
                            <button type="button" id="memPwChangeCompleteBtn" class="btn btn-success d-none">변경완료</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 form-group mt-2 mb-1">
                            <label for="memPw">새 비밀번호 확인</label>
                            <input type="password" class="form-control" id="memPwChk" name="memPwChk" disabled />
                        </div>
                    </div>
                    <span id="memPwErrMsg" class="text-danger"></span>
                    <hr />
                    <div class="row mt-2 mb-1">
                        <div class="col-md-6 form-group">
                            <label for="memNick">닉네임</label>
                            <input type="text" class="form-control" id="memNick" name="memNick" value="${memInfo.memNick}" disabled />
                        </div>
                        <div class="col-md d-flex justify-content-start align-items-end">
                            <button type="button" id="memNickChangeBtn" class="btn btn-danger">변경하기</button>
                            <button type="button" id="memNickChangeCompleteBtn" class="btn btn-success d-none">변경완료</button>
                        </div>
                    </div>
                    <span id="memNickErrMsg" class="text-danger"></span>
                    <div class="row mt-2 mb-1">
                        <div class="col-md-6 form-group">
                            <label for="memEmail">이메일</label>
                            <input type="text" class="form-control" id="memEmail" name="memEmail" value="${memInfo.memEmail}" disabled />
                        </div>
                        <div class="col-md-auto d-flex justify-content-start align-items-end">
                            <button type="button" id="authSendBtn" class="btn btn-primary" disabled>발송하기</button>
                        </div>
                        <div class="col-md d-flex justify-content-start align-items-end">
                            <button type="button" id="memEmailChangeBtn" class="btn btn-danger">변경하기</button>
                            <button type="button" id="memEmailChangeCompleteBtn" class="btn btn-success d-none" disabled>변경완료</button>
                        </div>
                        <span id="memEmailErrMsg" class="text-danger"></span>
                    </div>
                    <div class="row mt-2 mb-1">
                        <div class="col-md-6 form-group">
                            <label for="authNum">인증 번호</label>
                            <input type="text" id="authNum" class="form-control" name="authNum" disabled />
                        </div>
                        <div class="col-md d-flex justify-content-start align-items-end">
                            <button type="button" id="authChkBtn" class="btn btn-primary" disabled>인증하기</button>
                        </div>
                        <span id="memEmailAuthErrMsg" class="text-danger"></span>
                    </div>
                    <hr />
                    <div class="d-grid gap-2">
                        <a href="/boards" class="btn btn-secondary" role="button">뒤로가기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const memId = document.querySelector('#memId');
    const memPw = document.querySelector('#memPw');
    const memPwChk = document.querySelector('#memPwChk');
    const memNick = document.querySelector('#memNick');
    const memEmail = document.querySelector('#memEmail');
    const authNum = document.querySelector('#authNum');

    const memPwForm = document.memPwForm;

    const memPwErrMsg = document.querySelector('#memPwErrMsg');
    const memNickErrMsg = document.querySelector('#memNickErrMsg');
    const memEmailErrMsg = document.querySelector('#memEmailErrMsg');
    const memEmailAuthErrMsg = document.querySelector('#memEmailAuthErrMsg');

    const memPwChangeBtn = document.querySelector("#memPwChangeBtn");
    const memPwChangeCompleteBtn = document.querySelector("#memPwChangeCompleteBtn");
    const memNickChangeBtn = document.querySelector("#memNickChangeBtn");
    const memNickChangeCompleteBtn = document.querySelector("#memNickChangeCompleteBtn");
    const authSendBtn = document.querySelector("#authSendBtn");
    const authChkBtn = document.querySelector("#authChkBtn");
    const memEmailChangeBtn = document.querySelector("#memEmailChangeBtn");
    const memEmailChangeCompleteBtn = document.querySelector("#memEmailChangeCompleteBtn");

    let authSendFlag = false; // 인증 메일 발송 여부

    memPwChangeBtn.onclick = function(){
        memPw.disabled = false;
        memPwChk.disabled = false;
        this.classList.add('d-none');
        memPwChangeCompleteBtn.classList.remove('d-none');
    }

    memPwChangeCompleteBtn.onclick = function(){

        const memPwValue = memPw.value.trim();
        const memPwChkValue = memPwChk.value.trim();

        if(memPwValue == '' || memPwChkValue == ''){
            memPwErrMsg.textContent = '비밀번호를 입력해주세요.';
            return;
        }

        if(memPwValue != memPwChkValue){
            memPwErrMsg.textContent = '비밀번호가 서로 일치하지 않습니다.';
            return;
        }

        memPwErrMsg.textContent = '';
        const memIdValue = memId.value;
        fetch('/updateMemPw', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN' : '${_csrf.token}',
                'Content-Type' : 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                memId: memIdValue,
                memPw: memPwValue
            })
        })
        .then(res => {
            if(!res.ok) throw new Error('잠시 후 다시 시도해주세요.');

            alert('비밀번호가 변경되었습니다.');
            this.classList.add('d-none');
            memPwChangeBtn.classList.remove('d-none');
            memPw.value = '';
            memPw.disabled = true;
            memPwChk.value = '';
            memPwChk.disabled = true;
        })
        .catch(err => {
            alert(err.message);
        })
    }

    memNickChangeBtn.onclick = function(){
        memNick.disabled = false;
        this.classList.add('d-none');
        memNickChangeCompleteBtn.classList.remove('d-none');
    }

    memNickChangeCompleteBtn.onclick = function(){

        const memNickValue = memNick.value.trim();

        if(memNickValue == ''){
            memNickErrMsg.textContent = '닉네임을 입력해주세요.';
            return;
        }

        memNickErrMsg.textContent = '';
        const memIdValue = memId.value;
        fetch('/updateMemNick', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN' : '${_csrf.token}',
                'Content-Type' : 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                memId: memIdValue,
                memNick: memNickValue
            })
        })
        .then(res => {
            if(!res.ok) throw new Error('잠시 후 다시 시도해주세요.');

            alert('닉네임이 변경되었습니다.');
            this.classList.add('d-none');
            memNickChangeBtn.classList.remove('d-none');
            memNick.disabled = true;
        })
        .catch(err => {
            alert(err.message);
        })
    }

    memEmailChangeBtn.onclick = function(){
        memEmail.disabled = false;
        this.classList.add('d-none');
        memEmailChangeCompleteBtn.classList.remove('d-none');

        authNum.disabled = false;
        authSendBtn.disabled = false;
        authChkBtn.disabled = false;
    }


    authSendBtn.onclick = function(){
        const memEmailValue = memEmail.value.trim();
        
        if(memEmailValue == ''){
            memEmailErrMsg.textContent = '이메일을 입력해주세요.';
            return false;
        }
        memEmailErrMsg.textContent = '';

        fetch('/sendAuthMail', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN' : '${_csrf.token}',
                'Content-Type' : 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                'memEmail' : memEmailValue
            })
        })
        .then(res => {
            if(res.status === 400){
                memEmailErrMsg.textContent = '이미 사용중인 이메일입니다.';
                return;
            }
            if(!res.ok) throw new Error('잠시 후 다시 시도해주세요.');

            alert('인증 번호가 발송되었습니다.');
            authSendFlag = true;
        })
        .catch(() => {
            alert(err.message);
        });
    }

    authChkBtn.onclick = function(){

        if(!authSendFlag){
            memEmailAuthErrMsg.textContent = '인증 메일을 먼저 발송해주세요.';
            return;
        }

        const authNumValue = authNum.value.trim();

        if(authNumValue == ''){
            alert('인증 번호를 입력해주세요.');
            return false;
        }
        memEmailAuthErrMsg.textContent = '';

        const memIdValue = memId.value.trim();
        const memEmailValue = memEmail.value.trim();

        fetch('/updateMemEmail', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN' : '${_csrf.token}',
                'Content-Type' : 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                'memId' : memIdValue,
                'memEmail' : memEmailValue,
                'authNum' : authNumValue
            })
        })
        .then(res => {
            if(res.status === 403) {
                memEmailAuthErrMsg.textContent = '인증번호가 틀렸습니다.';
                return;
            }
            if(!res.ok) throw new Error('잠시 후 다시 시도해주세요.');

            alert('이메일이 변경되었습니다.');
            memEmail.disabled = true;
            authSendBtn.disabled = true;
            
            authNum.value = '';
            authNum.disabled = true;
            authChkBtn.disabled = true;

            memEmailChangeBtn.classList.remove('d-none');
            memEmailChangeCompleteBtn.classList.add('d-none');
        })
        .catch(err => {
            alert(err.message);
        })


    }

</script>
</html>