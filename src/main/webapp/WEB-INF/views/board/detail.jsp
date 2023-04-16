<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>글보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <style>
        .infoRow{
            align-items: center;
        }
        .note-editable>*{
            margin: 0;
        }
        .commentDeleteBtn{
            cursor: pointer;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/navbar.jsp" %>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center" style="height: 700px;">
            <div class="card border-0 shadow-lg rounded-2 w-75">
                <div class="card-header">
                    <div class="navbar p-0">
                        <h2>글보기</h2>
                        <a href="/boards/pages/${board.currentPage}" class="btn btn-secondary" role="button">목록으로</a>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row infoRow">
                        <h4 class="col-md-2 text-center m-0">제목</h4>
                        <h5 class="col-md-10 text-center m-0">${board.boardTitle}</h5>
                    </div>
                    <hr />
                    <div class="row">
                        <div class="col-md-6">
                            <div class="row infoRow">
                                <h4 class="col-md-4 text-center m-0">작성자</h4>
                                <h5 class="col-md-8 text-center m-0">${board.memNick}</h5>
                            </div>
                            <hr />
                            <div class="row infoRow">
                                <h4 class="col-md-4 text-center m-0">작성일</h4>
                                <h5 class="col-md-8 text-center m-0">${board.createdAt}</h5>
                            </div>
                        </div>
                        <sec:authentication property="principal" var="memDet" />
                        <c:if test="${board.memId == memDet.memDto.memId}">
                            <div class="offset-md-4 col-md-2">
                                <div class="d-grid gap-2">
                                    <button type="button" id="updateBtn" class="btn btn-warning btn-sm">수정</button>
                                    <form action="/boards/update" method="post" name="updateForm">
                                        <input type="hidden" name="boardId" value="${board.boardId}" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </form>
                                    <button type="button" id="deleteBtn" class="btn btn-danger btn-sm">삭제</button>
                                    <form action="/boards/delete" method="post" name="deleteForm">
                                        <input type="hidden" name="boardId" value="${board.boardId}" />
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                    <hr />
                    <div class="row">
                        <h4 class="col-md-2 text-center">내용</h4>
                    </div>
                    <div class="row">
                        <div class="offset-md-2 col-md">
                            ${board.boardContent}
                        </div>
                    </div>
                    <hr class="mb-0" />
                </div>
                <div class="card-body">
                    <div class="row">
                        <h4 class="col-md-2 text-center">댓글</h4>
                        <div class="col-md">
                            <textarea id="comment"></textarea>
                        </div>
                        <div class="col-md-auto">
                            <button type="button" class="btn btn-primary" style="width: 100px;">등록</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="offset-md-2 col-md">
                            <hr />
                        </div>
                    </div>
                    <div class="commentRow row mb-2">
                        <div class="commentBox offset-md-2 col-md">
                            <div class="commentHeader mb-2">
                                <h5 class="d-inline">이상혁</h5>
                                <p class="d-inline text-muted ms-2"><small>2023-04-16 22:51:31</small></p>
                                <span class="text-danger commentDeleteBtn">&times;</span>
                            </div>
                            <div class="commentBody">
                                <p>감사합니다 ㅎㅎ</p>
                            </div>
                        </div>
                    </div>
                    <div class="commentRow row mb-2">
                        <div class="commentBox offset-md-2 col-md">
                            <div class="commentHeader mb-2">
                                <h5 class="d-inline">강감찬</h5>
                                <p class="d-inline text-muted ms-2"><small>2023-04-16 22:51:46</small></p>
                                <span class="text-danger commentDeleteBtn">&times;</span>
                            </div>
                            <div class="commentBody">
                                <p>저두 동감입니다~~</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const updateForm = document.updateForm;
    const deleteForm = document.deleteForm;
    const updateBtn = document.querySelector('#updateBtn');
    const deleteBtn = document.querySelector('#deleteBtn');

    updateBtn.onclick = function() {
        updateForm.submit();
    }

    deleteBtn.onclick = function() {
        if(confirm('게시글을 삭제하시겠습니까?')){
            deleteForm.submit();
        }
    }

    $('#comment').summernote({
        height: 120,
        toolbar: []
    });
</script>
</html>