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
          <form id="insert-comment" method="POST" action="comment-add">
            <textarea name="content" placeholder="하고 싶은 말 " ></textarea>

            <div id="input-email-group" class="input-group col-md-4">
              <span class="input-group-addon" id="user-email">@</span>
              <input type="text" class="form-control" placeholder="email" name="email" aria-describedby="basic-addon1">
            </div>
            <div id="input-password-group" class="input-group col-md-4">
              <span class="input-group-addon" id="user-password">PW</span>
              <input type="password" class="form-control" placeholder="password" name="pw" aria-describedby="basic-addon1">
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
          <div class="col-md-12 col-sm-10 content-area-${guestBookModel.id}">
            <div class="panel panel-default arrow left">
              <div class="panel-body">
                <header class="text-left">
                  <div class="comment-user"><i class="fa fa-user"></i>${guestBookModel.email}</div>
                  <time class="comment-date" datetime="${guestBookModel.lastUpdate}"><i class="fa fa-clock-o"></i></time>
                </header>
                <div class="comment-post">
                  <p>
                      ${guestBookModel.content}
                  </p>
                </div>
                <p class="text-right view-modify-btn" data-id="${guestBookModel.id}"><a href="#" class="btn btn-default btn-sm"><i class="fa fa-reply"></i> 수정하기</a></p>
              </div>
            </div>
          </div>
          <div class="col-md-12 col-sm-10 modify-form-${guestBookModel.id} hide">
            <div class="panel panel-default arrow left">
              <div class="panel-body">
                <form>
                  <textarea class="modify-form-content" name="content" placeholder="${guestBookModel.content}" ></textarea>

                  <div id="modify-form-email" class=" input-group col-md-4">
                    <span class="input-group-addon" >@</span>
                    <input type="text" class="form-control"  readonly=readonly value="${guestBookModel.email}" name="email" aria-describedby="basic-addon1">
                  </div>
                  <div id="modify-form-pw" class=" input-group col-md-4">
                    <span class="input-group-addon" >PW</span>
                    <input type="password" class="form-control inputed-password-${guestBookModel.id}" placeholder="비밀번호를 입력하세요" name="pw" aria-describedby="basic-addon1">
                  </div>
                  <button type="button" class="btn btn-success green submit-modify-btn" data-id="${guestBookModel.id}"><i class="fa fa-share"></i> 적용</button>
                </form>
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
<%--<script type="text/javascript" src="js/custom/index.js"></script>--%>
<script type="text/javascript">
  $(".view-modify-btn").on('click',function(){
    var clickedId = $(this).data('id');
    $(".content-area-"+clickedId).addClass('hide');
    $(".modify-form-"+clickedId).removeClass('hide');
  });

  $(".submit-modify-btn").on('click',function(){
    var targetId = $(this).data('id');
    var targetPw = $('.inputed-password-'+targetId).val();
    var modifiedContent = $(this).parent('form').children('textarea').val();
    console.log('submit data to modify content.');
    var apiUrl = '/comment-modify';
    $.ajax({
      url: apiUrl,
      type: "POST",
      data: {
        id : targetId,
        content : modifiedContent,
        pw : targetPw
      },
      success: function (data) {
        if(data=='success'){
          window.location.replace("/index");
        }else{
          alert('비밀번호를 확인해주세요. 정신 차리고 입력해보세요~~');
        }

      }
    });
  });

  function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
  }
  
  $( "#insert-comment" ).submit(function( event ) {
    if ( isEmail($( "input:first" ).val()) ) {
      return;
    }
    alert("이메일 형식을 확인해주세요.");
    event.preventDefault();
  });

</script>
</html>
