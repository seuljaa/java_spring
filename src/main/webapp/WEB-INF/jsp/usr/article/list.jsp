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
    <div class="">
      <table class="table table-fixed w-full ">
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
            <th class="w-auto">조회수</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="article" items="${articles}">
            <tr>
              <td>${article.id}</td>
              <td>${article.regDate.substring(2, 16)}</td>
              <td>${article.updateDate.substring(2, 16)}</td>
              <td>${article.extra__writerName}</td>
              <td class="truncate">
                <a class="btn-text-link"  href="../article/detail?id=${article.id}">
                  ${article.title}
                </a>
              </td>
              <td>${article.hitCount}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <form>
      <div class="container">
        <div class="form-control">
          <div class="input-group justify-center">
            <select data-value="${param.searchKeywordType}" class="select select-bordered w-auto" name="searchKeywordType">
              <option value="title">제목</option>
              <option value="body">내용</option>
              <option value="title,body">제목+내용</option>
            </select>
            <input type="text" placeholder="검색" class="input input-bordered w-96" name="searchKeyword" value="${param.searchKeyword}">
              <button type="submit" class="btn btn-square">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
              </button>
          </div>
        </div>
      </div>
    </form>
    <div class="btn-group justify-center my-5">
      <c:set var="pageMenuArmLen" value="9"/>
      <c:set var="startPage" value="${page - pageMenuArmLen >= 1 ? page - pageMenuArmLen : 1}"/>
      <c:set var="endPage" value="${page + pageMenuArmLen <= pageCount ? page + pageMenuArmLen : pageCount}"/>
      <a class="btn btn-sm" href="?page=1&boardId=${boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}">처음으로</a>
      <a class="btn btn-sm" href="?page=${page-1}&boardId=${boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}">이전</a>
      <c:forEach begin="${startPage}" end="${endPage}" var="i">
        <a class="btn btn-sm ${page == i ? 'btn-active' : '' }" href="?page=${i}&boardId=${boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}">${i}</a>
      </c:forEach>
      <a class="btn btn-sm" href="?page=${page+1}&boardId=${boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}">다음</a>
      <a class="btn btn-sm" href="?page=${pageCount}&boardId=${boardId}&searchKeywordType=${param.searchKeywordType}&searchKeyword=${param.searchKeyword}">마지막으로</a>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>