<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <style>
        .boardRow {
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
                    <h2>게시판</h2>
                </div>
                <div class="card-body">
                    <table class="table table-hover text-center">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="boardList" value="${requestScope.boardInfo.contentList}" />
                            <c:choose>
                                <c:when test="${boardList != null and boardList.size() > 0}">
                                    <c:forEach var="board" items="${boardList}">
                                        <tr class="boardRow">
                                            <td style="width: 10%;">${board.boardId}</td>
                                            <td style="width: 50%;">${board.boardTitle}</td>
                                            <td style="width: 10%;">${board.memNick}</td>
                                            <td style="width: 30%;">${board.createdAt}</td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4">게시글이 존재하지 않습니다.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-center align-items-center">
                        <ul class="pagination">
                            <c:if test="${requestScope.boardInfo.startPage > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:forEach var="page" begin="${requestScope.boardInfo.startPage}" end="${requestScope.boardInfo.endPage}">
                                <li class="page-item <c:if test='${requestScope.boardInfo.currentPage == page}'>active</c:if>">
                                    <a class="page-link" href="/boards/pages/${page}">${page}</a>
                                </li>
                            </c:forEach>
                            <c:if test="${requestScope.boardInfo.endPage != requestScope.boardInfo.totalPages}">
                                <li class="page-item">
                                    <a class="page-link" href="#" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
                <div class="card-footer text-end">
                    <a href="/boards/insert" class="btn btn-primary" role="button">글쓰기</a>
                </div>
            </div>
        </div>
    </div>
</body>
<script>
    const boardRows = document.querySelectorAll('.boardRow');
    boardRows.forEach(function(row){
        row.onclick = function(){
            const boardId = this.querySelectorAll('td')[0].textContent;
            location.href = '/boards/' + boardId;
        }
    });
</script>
</html>