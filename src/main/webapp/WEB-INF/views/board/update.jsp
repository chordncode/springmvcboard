<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>글수정</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <style>
        .note-editable>*{
            margin: 0;
        }
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/views/navbar.jsp" %>
    <div class="container">
        <div class="d-flex justify-content-center align-items-center" style="height: 700px;">
            <div class="card border-0 shadow-lg rounded-2 w-75">
                <form action="/boards/updatePost" method="post">
                    <div class="card-header">
                        <h2>글수정</h2>
                    </div>
                    <div class="card-body">
                        <input type="hidden" name="boardId" value="${board.boardId}" />
                        <div class="form-group">
                            <label for="boardTitle"><h5>제목</h5></label>
                            <input type="text" class="form-control" id="boardTitle" name="boardTitle" value="${board.boardTitle}" />
                        </div>
                        <hr />
                        <div class="form-group">
                            <label for="boardContent"><h5>내용</h5></label>
                            <textarea id="boardContent" name="boardContent"></textarea>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>
                    <div class="card-footer text-end">
                        <button type="submit" class="btn btn-primary">등록</button>
                        <a href="javascript:history.back();" class="btn btn-secondary" role="button">취소</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
<script>
    const boardTitle = document.querySelector('#boardTitle');;
    const boardContent = document.querySelector('#boardContent');

    $('#boardContent').summernote({
        height: 200
    });
    $('#boardContent').summernote('code', '${board.boardContent}');
</script>
</html>