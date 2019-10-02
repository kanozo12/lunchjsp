<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row my-4">
			<div class="col">
				<div class="card">
					<div class="card-header">
						<div class="form-row">
							<div class="col-6">
								<input class="form-control" type="date" id="sdate">
							</div>
							<div class="col-3">
								<button type="submit" class="btn btn-primary" id="loadBtn">가져오기</button>
							</div>
						</div>
					</div>
					<div class="card-body">
						<div id="menuList"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("#loadBtn").on("click",function() {
				let date = $("#sdate").val();
				if(date == "") {
					alert("날짜를 선택해주세요");
					return;
				}
				let dates = date.split("-");
				date = "";
				for(let i = 0; i < dates.length; i++) {
					date += dates[i];
				}
				
				//날짜 형식 변경
				
				$.ajax({
					url:'/lunch',
					method:'post',
				success: function(data) {
					alert(data.msg);
					if(data.result) {
						$("#menuList").empty();
						$("#menuList").append(data.menu);
					}
				}
			});
	</script>
</body>
</html>