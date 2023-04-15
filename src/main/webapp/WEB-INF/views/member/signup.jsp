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
            <div class="card border-0 shadow-lg rounded-2 w-50">
                <div class="card-header">
                    <h2>회원가입</h2>
                </div>
                <div class="card-body">
                    <form action="/signup" method="post" name="signupForm" onsubmit="return signup();">
                        <div class="form-group mb-1">
                            <label for="memId">아이디</label>
                            <input type="text" class="form-control" id="memId" name="memId" />
                        </div>
                        <span id="memIdErrMsg" class="text-danger"></span>
                        <div class="row mt-2 mb-1">
                            <div class="col-md-6 form-group">
                                <label for="memPw">비밀번호</label>
                                <input type="password" class="form-control" id="memPw" name="memPw" />
                            </div>
                            <div class="col-md-6 form-group">
                                <label for="memPw">비밀번호 확인</label>
                                <input type="password" class="form-control" id="memPwChk" name="memPwChk" />
                            </div>
                        </div>
                        <span id="memPwErrMsg" class="text-danger"></span>
                        <hr />
                        <div class="row mt-2 mb-1">
                            <div class="col-md-6 form-group">
                                <label for="memNick">닉네임</label>
                                <input type="text" class="form-control" id="memNick" name="memNick" />
                            </div>
                        </div>
                        <span id="memNickErrMsg" class="text-danger"></span>
                        <div class="row mt-2 mb-1">
                            <div class="col-md-6 form-group">
                                <label for="memEmail">이메일</label>
                                <input type="text" class="form-control" id="memEmail" name="memEmail" />
                            </div>
                            <div class="col-md d-flex justify-content-start align-items-end">
                                <button type="button" class="btn btn-primary" id="authSendBtn">발송하기</button>
                            </div>
                            <span id="memEmailErrMsg" class="text-danger"></span>
                        </div>
                        <div class="row mt-2 mb-1">
                            <div class="col-md-6 form-group">
                                <label for="authNum">인증 번호</label>
                                <input type="text" class="form-control" id="authNum" name="authNum" />
                            </div>
                            <div class="col-md d-flex justify-content-start align-items-end">
                                <button type="button" class="btn btn-primary" id="authChkBtn">인증하기</button>
                            </div>
                            <span id="memEmailAuthErrMsg" class="text-danger"></span>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <hr />
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">회원가입</button>
                            <button type="button" id="gobackBtn" class="btn btn-secondary">취소</button>
                        </div>
                    </form>
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
    
    const memIdErrMsg = document.querySelector('#memIdErrMsg');
    const memPwErrMsg = document.querySelector('#memPwErrMsg');
    const memNickErrMsg = document.querySelector('#memNickErrMsg');
    const memEmailErrMsg = document.querySelector('#memEmailErrMsg');
    
    const authSendBtn = document.querySelector('#authSendBtn');
    const authChkBtn = document.querySelector('#authChkBtn');
    const gobackBtn = document.querySelector('#gobackBtn');

    let authSendFlag = false; // 인증 메일 발송 여부

    authSendBtn.onclick = function(){
        const memEmailValue = memEmail.value.trim();
        
        if(memEmailValue == ''){
            alert('이메일을 입력해주세요.');
            return false;
        }

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

        fetch('/checkAuthNum', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN' : '${_csrf.token}',
                'Content-Type' : 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                'authNum' : authNumValue
            })
        })
        .then(res => {
            if(res.status === 403) {
                memEmailAuthErrMsg.textContent = '인증번호가 틀렸습니다.';
                return;
            }
            if(!res.ok) throw new Error('잠시 후 다시 시도해주세요.');

            alert('인증 되었습니다.');
            memEmail.readOnly = true;
            authSendBtn.disabled = true;
            authNum.disabled = true;
            authChkBtn.disabled = true;
        })
        .catch(err => {
            alert(err.message);
        })


    }
    
    function signup(){
        if(!memId.value.trim()){
            alert('아이디를 입력해주세요.');
            return false;
        }
        if(!memPw.value.trim()){
            alert('비밀번호를 입력해주세요.');
            return false;
        }
        if(!memPwChk.value.trim()){
            alert('비밀번호 확인을 입력해주세요.');
            return false;
        }
        if(memPw.value.trim() != memPwChk.value.trim()){
            alert('비밀번호가 서로 일치하지 않습니다.');
            return false;
        }
        if(!memNick.value.trim()){
            alert('닉네임을 입력해주세요.');
            return false;
        }
        if(!memEmail.value.trim()){
            alert('이메일을 입력해주세요.');
            return false;
        }
        return true;
    }

    function isMemPwMatch(){
        const memPwVal = memPw.value.trim();
        const memPwChkVal = memPwChk.value.trim();

        if(memPwVal == '' || memPwChkVal == '') return;

        if(memPwVal != memPwChkVal){
            memPwErrMsg.textContent = '비밀번호가 서로 일치하지 않습니다.';
        } else {
            memPwErrMsg.textContent = '';
        }
    }

    function emptyErrMsg(errMsgSpan){
        errMsgSpan.textContent = '';
    }

    memPw.onkeyup = isMemPwMatch;
    memPwChk.onkeyup = isMemPwMatch;
    
    memId.onkeydown = () => {emptyErrMsg(memIdErrMsg);}
    memId.onblur = function(){
        if(memId.value.trim() == ''){
            memIdErrMsg.textContent = '아이디를 입력해주세요.';
        } else {
            memIdErrMsg.textContent = '';
        }
    }

    memNick.onkeydown = () => {emptyErrMsg(memNickErrMsg);}
    memNick.onblur = function(){
        if(memNick.value.trim() == ''){
            memNickErrMsg.textContent = '닉네임을 입력해주세요.';
        } else {
            memNickErrMsg.textContent = '';
        }
    }

    memEmail.onkeydown = () => {emptyErrMsg(memEmailErrMsg);}
    memEmail.onblur = function(){
        if(memEmail.value.trim() == ''){
            memEmailErrMsg.textContent = '이메일을 입력해주세요.';
        } else {
            memEmailErrMsg.textContent = '';
        }
    }

    authNum.onkeydown = () => {emptyErrMsg(memEmailAuthErrMsg);}

    gobackBtn.onclick = function(){
        history.back();
    }
</script>
</html>