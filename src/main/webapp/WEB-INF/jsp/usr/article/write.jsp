<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="게시물 작성" />
<%@ include file="../common/head.jspf"%>


<section class="mt-5">
  <div class="container mx-auto px-3">
    <form class="teable-box-typ-1" method="POST" action="../article/doAdd">
      <table>
        <colgroup>
          <col width="200" />
        </colgroup>
        <tbody>
          <tr>
            <th>작성자</th>
            <td>
              <div class="badge">${rq.loginedMember.nickname}</div>
            </td>
          </tr>
          <tr>
            <th>게시판선택</th>
            <td>
              <select class="select select-bordered" name="boardId">
                <option value="1">공지사항</option>
                <option value="2">자유게시판</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
            <input class="w-96 input input-bordered" name="title" type="text" placeholder="제목"/>
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              <textarea class="textarea textarea-bordered w-full" name="body" rows="10" placeholder="내용"></textarea>
            </td>
          </tr>
          <tr>
            <th>작성</th>
            <td>
              <button class="btn btn-active btn-primary btn-sm" type="submit" >작성</button>
              <button class="btn btn-active btn-ghost btn-sm" type="button" onclick="history.back();">뒤로가기</button>
            </td>
          </tr>
        </tbody>
      </table>
    </form>
  </div>
</section>

<%@ include file="../common/foot.jspf"%>