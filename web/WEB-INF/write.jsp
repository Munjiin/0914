<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-13
  Time: 오전 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">글을 등록해주세요</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">


                <p class="panel panel-default">
                <div class="panel-heading">
                    원하는 글을 써주세요~
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-6">

                            <form action="/write" method="post">

                                <div class="form-group">
                                    <label>제목</label>
                                    <input class="form-control" name="title">
                                </div>
                                <div class="form-group">
                                    <label>작성자</label>
                                    <input class="form-control" placeholder="Enter text" name="mname" >
                                </div>
                                <div class="form-group">
                                    <label>내용</label>
                                    <input class="form-control" placeholder="Enter text" name="content" >
                                </div>

                                <button>등록</button>
                            </form>

                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>


            <form action = "/list">
                <button>취소</button>
            </form>

        </div>




<%@ include file="includes/footer.jsp"%>