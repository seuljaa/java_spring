<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 수정" />
<%@ include file="../common/head.jspf"%>


<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="teable-box-typ-1" method="POST" action="../article/doModify">
      <input type="hidden" name="id" value="${article.id}"/>
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>번호</th>
            <td>
              <div class="badge badge-primary">${article.id}</div>
            </td>
          </tr>
          <tr>
            <th>작성날짜</th>
            <td>
              <div class="badge badge-outline">${article.regDateForPrint}</div>
            </td>
          </tr>
          <tr>
            <th>수정날짜</th>
            <td>
              <div class="badge badge-outline">${article.updateDateForPrint}</div>
            </td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>
              <div class="badge">${article.extra__writerName}</div>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
            <input class="w-96 input input-bordered" name="title" type="text" placeholder="제목" value="${article.title}"/>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="textarea textarea-bordered w-full" name="body" rows="10" placeholder="내용">${article.body}</textarea>
            </td>
          </tr>
          <tr>
            <th>수정</th>
            <td>
              <button class="btn btn-active btn-primary btn-sm" type="submit" >수정</button>
              <button class="btn btn-active btn-ghost btn-sm" type="button" onclick="history.back();">뒤로가기</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
    <div class="btns">
      <c:if test="${article.extra__actorCanDelete}">
        <a class="btn btn-link"
          onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) return false;"
          href="../article/delete?id=${article.id}">게시글 삭제</a>
      </c:if>
    </div>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>