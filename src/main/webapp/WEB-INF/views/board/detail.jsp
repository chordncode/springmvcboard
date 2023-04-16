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
        <div class="d-flex justify-content-center align-items-center my-4" style="min-height: 700px;">
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
                    <form name="insertCommentForm" action="/boards/insertComment" method="post" onsubmit="return insertComment();">
                        <div class="row">
                            <h4 class="col-md-2 text-center">댓글</h4>
                            <div class="col-md">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <input type="hidden" name="boardId" value="${board.boardId}" />
                                <textarea id="commentContent" name="commentContent"></textarea>
                            </div>
                            <div class="col-md-auto">
                                <button type="submit" class="btn btn-primary" style="width: 100px;">등록</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="offset-md-2 col-md">
                            <hr />
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${commentList != null and commentList.size() > 0}">
                            <c:forEach var="comment" items="${commentList}">
                                <div class="commentRow row mb-2">
                                    <div class="commentBox offset-md-2 col-md">
                                        <div class="commentHeader mb-2">
                                            <h5 class="d-inline">${comment.memNick}</h5>
                                            <p class="d-inline text-muted ms-2"><small>${comment.createdAt}</small></p>
                                            <c:if test="${comment.memId == memDet.memDto.memId}">
                                                <span class="text-danger commentDeleteBtn">&times;</span>
                                                <form action="/boards/deleteComment" method="post" class="deleteCommentForm">
                                                    <input type="hidden" name="boardId" value="${board.boardId}" />
                                                    <input type="hidden" name="commentId" value="${comment.commentId}" />
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                </form>
                                            </c:if>
                                        </div>
                                        <div class="commentBody">
                                            ${comment.commentContent}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="commentRow row mb-2">
                                <div class="commentBox offset-md-2 col-md text-center">
                                    <h5 class="my-4">작성된 댓글이 없습니다.</h5>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const commentContent = document.querySelector('#commentContent');

    const updateForm = document.updateForm;
    const deleteForm = document.deleteForm;
    const insertCommentForm = document.insertCommentForm;

    const updateBtn = document.querySelector('#updateBtn');
    const deleteBtn = document.querySelector('#deleteBtn');
    const commentDeleteBtn = document.querySelectorAll('.commentDeleteBtn');

    if(updateBtn != null){
        updateBtn.onclick = function() {
            updateForm.submit();
        }
    }

    if(deleteBtn != null){
        deleteBtn.onclick = function() {
            if(confirm('게시글을 삭제하시겠습니까?')){
                deleteForm.submit();
            }
        }
    }

    $('#commentContent').summernote({
        height: 120,
        toolbar: []
    });

    function insertComment(){
        const commentContentValue = commentContent.value.trim();
        if(commentContentValue == ''){
            alert('댓글 내용을 작성해주세요.');
            return false;
        }

        return true;
    }

    commentDeleteBtn.forEach(deleteBtn => {
        deleteBtn.onclick = function(){
            if(confirm('댓글을 삭제하시겠습니까?')){
                this.nextSibling.nextSibling.submit();
            }
        }
    })
</script>
</html>