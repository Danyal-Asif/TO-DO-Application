<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>TODO APPLICATION</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://github.com/Danyal-Asif" class="navbar-brand">
     TODO Application </a> 
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Tasks</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Tasks</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Task</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Due Date</th>
                                <th>Priority</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="task" items="${listTask}">

                                <tr>
                                    <td>
                                        <c:out value="${task.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${task.title}" />
                                    </td>
                                    <td>
                                        <c:out value="${task.description}" />
                                    </td>
                                    
                                    
                                    <c:if test="${task.status=='COMPLETED'}">
                            		<td>
                                        <c:out value="${task.due_date}" />
                                    </td>
                        			</c:if>
                                    
                                    <c:if test="${task.status!='COMPLETED'}">
                                    <c:if test="${task.due_date<=date}">
                            		<td style="color:red;">
                                        <c:out value="${task.due_date}" />
                                    </td>
                        			</c:if>
                        			
                        			<c:if test="${task.due_date>date}">
                            		<td style="color:green;">
                                        <c:out value="${task.due_date}" />
                                    </td>
                        			</c:if>
                        			</c:if>
                                    <td>
                                        <c:out value="${task.priority}" />
                                    </td>
                                    <c:if test="${task.status=='COMPLETED'}">
                            		<td style="color: green;">
                                        <c:out value="${task.status}" /> &#x2713;
                                    </td>
                        			</c:if>
                        			
                                    <c:if test="${task.status!='COMPLETED'}">
                            		<td>
                                        <c:out value="${task.status}" />
                                    </td>
                        			</c:if>
                                    <td><a href="edit?id=<c:out value='${task.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${task.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>