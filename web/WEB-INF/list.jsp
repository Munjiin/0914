<%--
  Created by IntelliJ IDEA.
  User: zzz
  Date: 2018-09-12
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="includes/header.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Tables</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>

    <script>
        function change(obj){

            var sizeValue = obj.options[obj.selectedIndex].value;
            console.log(sizeValue);
            self.location = "list?page=1&size=" + sizeValue;
        }
    </script>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    DataTables Advanced Tables
                    <select onchange="change(this)">
                        <option value="10" ${pageMaker.pageDTO.size == 10?"selected":""}>10</option>
                        <option value="20"${pageMaker.pageDTO.size == 20?"selected":""}>20</option>
                        <option value="50"${pageMaker.pageDTO.size == 50?"selected":""}>50</option>
                        <option value="100"${pageMaker.pageDTO.size == 100?"selected":""}>100</option>
                    </select>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <h2>${pageMaker}</h2>
                    <table width="100%" class="table table-striped table-bordered table-hover" >
                        <thead>
                        <tr>
                            <th>BNO</th>
                            <th>TITLE</th>
                            <th>WRITER</th>
                            <th>REG DATE</th>
                            <th>UPDATE DATE</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="board" items="${list}" >
                            <tr class="odd gradeX">
                                <td>${board.bno}</td>
                                <td><a href="read?bno=${board.bno}&page=${pageMaker.pageDTO.page}">${board.title}<span class="badge">${board.viewcnt}</span></a></td>
                                <td>${board.writer}</td>
                                <td>${board.regdate}</td>
                                <td>${board.updatedate}</td>

                            </tr>
                        </c:forEach>


                        </tbody>
                    </table>

                    <!-- /.table-responsive -->
                    <p class="text-center">
                        <a href="/write"><button type="button" class="btn btn-primary btn-lg btn-block">글쓰기</button></a></p>
                    <div class="text-center">
                        <br>


                    <div class="well">
                        <ul class="pagination">

                            <c:if test="${pageMaker.prev}">
                                <li><a href="list?page=${pageMaker.start-1}&size=${pageMaker.pageDTO.size}">Prev</a> </li>
                            </c:if>

                            <c:forEach begin="${pageMaker.start}" end = "${pageMaker.end}" var="num">
                                <li ${pageMaker.pageDTO.page == num?"class='active'":""}><a href="list?page=${num}&size=${pageMaker.pageDTO.size}">${num}</a></li>
                            </c:forEach>

                            <c:if test="${pageMaker.next}">
                                <li><a href="list?page=${pageMaker.end+1}&size=${pageMaker.pageDTO.size}">Next</a> </li>
                            </c:if>

                        </ul>
                    </div>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<%@include file="includes/footer.jsp"%>
