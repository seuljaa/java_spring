<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name} 게시물 리스트" />
<%@ include file="../common/head.jspf"%>


<section class="mt-5">
  <div class="container mx-auto px-3">
    <div>
      게시물 개수 : <div class="badge badge-primary">${articlesCount}</div>개
    </div>
    <div class="teable-box-typ-1">
      <table>
      <colgroup>
        <col width="50" />
        <col width="150" />
        <col width="150" />
        <col width="150" />
        <col />
      </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>작성날짜</th>
            <th>수정날짜</th>
            <th>작성자</th>
            <th>제목</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles}">
            <tr>
              <td>${article.id}</td>
              <td>${article.regDate.substring(2, 16)}</td>
              <td>${article.updateDate.substring(2, 16)}</td>
              <td>${article.extra__writerName}</td>
              <td>
                <a class="btn-text-link"  href="../article/detail?id=${article.id}">${article.title}</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="btn-group justify-center">
      <c:set var="pageMenuArmLen" value="9"/>
      <c:set var="startPage" value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1}"/>
      <c:set var="endPage" value="${page + pageMenuArmLen <= pageCount ? page + pageMenuArmLen : pageCount}"/>
      <a class="btn btn-sm" href="?page=1&boardId=${boardId}">처음으로</a>
      <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <a class="btn btn-sm ${page == i ? 'btn-active' : '' }" href="?page=${i}&boardId=${boardId}">${i}</a>
      </c:forEach>
      <a class="btn btn-sm" href="?page=${pageCount}&boardId=${boardId}">마지막으로</a>
    </div>
    
  </div>
</section>

<%@ include file="../common/foot.jspf"%>