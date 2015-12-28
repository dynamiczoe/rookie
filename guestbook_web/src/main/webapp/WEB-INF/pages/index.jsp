<%--
  Created by IntelliJ IDEA.
  User: dynamiczoe
  Date: 15. 12. 28.
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
  <!-- Bootstrap -->
  <link href="css/libs/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/index.css"> <!-- Resource style -->
  <title>nhn enter - rookie</title>
</head>
<body>

<div class="container">
  <div class="row">

    <div class="col-md-12">
      <div class="widget-area no-padding blank">
        <div class="status-upload">
          <form>
            <textarea name="content" placeholder="하고 싶은 말 " ></textarea>

            <div id="input-email-group" class="input-group col-md-4">
              <span class="input-group-addon" id="user-email">@</span>
              <input type="text" class="form-control" placeholder="email" name="email" aria-describedby="basic-addon1">
            </div>
            <div id="input-password-group" class="input-group col-md-4">
              <span class="input-group-addon" id="user-password">PW</span>
              <input type="text" class="form-control" placeholder="password" name="password" aria-describedby="basic-addon1">
            </div>
            <button type="submit" class="btn btn-success green"><i class="fa fa-share"></i> 등록</button>
          </form>
        </div><!-- Status Upload  -->
      </div><!-- Widget Area -->
    </div>

  </div>
  <div class="row">
    <div class="col-md-12">
      <h2 class="page-header">방명록</h2>
      <section class="comment-list">
      <c:forEach items="${guestBookModelList}" var="guestBookModel">
        <!-- First Comment -->
        <article class="row">
          <div class="col-md-12 col-sm-10">
            <div class="panel panel-default arrow left">
              <div class="panel-body">
                <header class="text-left">
                  <div class="comment-user"><i class="fa fa-user"></i>${guestBookModel.email}</div>
                  <time class="comment-date" datetime="16-12-2014 01:05"><i class="fa fa-clock-o"></i>${guestBookModel.lastUpdate}</time>
                </header>
                <div class="comment-post">
                  <p>
                      ${guestBookModel.content}
                  </p>
                </div>
                <p class="text-right"><a href="#" class="btn btn-default btn-sm"><i class="fa fa-reply"></i> 수정하기</a></p>
              </div>
            </div>
          </div>
        </article>
      </c:forEach>
      </section>
    </div>
  </div>
</div>
</body>
<script src="js/libs/jquery-2.1.4.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>
<script type="text/javascript" src="js/custom/index.js"></script>
</html>
