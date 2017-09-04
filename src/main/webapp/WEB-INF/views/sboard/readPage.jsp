<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/resources/js/upload.js"></script>	
   <style type="text/css">
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%;  overflow:hidden; z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
  	
    </style>

 <div class='popup back' style="display:none;"></div>
    <div id="popup_front" class='popup front' style="display:none;">
     <img id="popup_img">
    </div>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<form role="form" method="post">
					<input type="hidden" name="bno" value="${boardVO.bno }"> <input
						type="hidden" name="page" value="${cri.page }" /> <input
						type="hidden" name="perPageNum" value="${cri.perPageNum }" /> <input
						type="hidden" name="searchType" value="${cri.searchType }" /> <input
						type="hidden" name="keyword" value="${cri.keyword }" />

				</form>
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name="title" class="form-control" value="${boardVO.title }"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content }</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>

				</div>
				<ul class="mailbox-attachments clearfix uploadedList"></ul>
				<div class="box-footer">
					<c:if test = "${login.uid == boardVO.writer }">
					
						<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
						<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
					</c:if>
					<button type="submit" class="btn btn-primary" id="goListBtn">LIST
						ALL</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title">ADD NEW REPLY</h3>
				</div>
				<c:if test = "${not empty login }">
				<div class="box-body">
					<label for="exampleInputEmail1">Writer</label> <input
						class="form-control" type="text" value = "${login.uid }" readonly
						id="newReplyWriter"> <label for="exampleInputEmail1">Reply
						Text</label> <input class="form-control" type="text"
						placeholder="REPLY TEXT" id="newReplyText">

				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
						REPLY</button>
				</div>
				</c:if>
				
				<c:if test = "${empty login }">
					<div class = "box-body">
						<div><a href="/user/login" >Login Please</a></div>
					</div>
				</c:if> 
			</div>

			<ul class="timeline">
				<li class="time-label" id="repliesDiv"><span class="bg-green">Replies
						List<small id="replycntSamll">[${boardVO.replycnt }]</small>
				</span></li>
			</ul>
			<div class="text-center">
				<ul id="pagination" class="pagination pagination-sm no-margin ">

				</ul>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div id="modifyModal" class="modal modal-primary fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body" data-rno>
					<p>
						<input type="text" id="replytext" class="form-control">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
					<button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
</section>
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class = "replyLi" data-rno = {{rno}}>
			<i class = "fa fa-comments bg-blue"></i>
			<div class = "timeline-item">
				<span class = "time">
					<i class = "fa fa-clock-o"></i>{{prettifyDate regdate}}
				</span>
				<h3 class = "timeline-header"><strong>{{rno}}</strong>-{{replyer}}</h3>
				<div class="timeline-body">{{replytext}} </div>
				<div class = "timeline-footer">
					{{#eqReplyer replyer}}
					<a class = "btn btn-primary btn-xs" data-toggle = "modal" data-target = "#modifyModal">Modify</a>
					{{/eqReplyer}}
				</div>
			</div>
		</li>
	{{/each}}
</script>
<script id = "templateAttach" type = "text/x-handlebars-template">
	<li data_src = "{{fullName}}">
		<span class = "mailbox-attachment-icon has-img"><img src = "{{imgsrc}}" alt = "Attachment"></span>
		<div class = "mailbox-attachment-info">
			<a href = "{{getLink}}" class = "mailbox-attachment-name">{{fileName}}</a>
		</div>
	</li>
</script>
<script>
Handlebars.registerHelper("prettifyDate", function(timeValueee) {
	var dateObj = new Date(timeValueee);
	var year = dateObj.getFullYear();
	var month = dateObj.getMonth() + 1;
	var date = dateObj.getDate();
	return year + "/" + month + "/" + date;
});

Handlebars.registerHelper("eqReplyer", function(replyer, block){
	var accum = "";	
	
	if(replyer == "${login.uid}"){
		accum += block.fn();
	}
	
	return accum;
});
var printData = function(replyArr){
		$(".replyLi").remove();
		var template = Handlebars.compile($("#template").html());
		
		var html = template(replyArr);
		
		$("#repliesDiv").after(html);
		
	}
	
	var bno = ${boardVO.bno};
	var replyPage = 1;
	
	function getPaging(pageInfo){
		$.getJSON(pageInfo, function(data){
			printData(data.list);
			printPaging(data.pageMaker, $(".pagination"));
			
			
			$("#modifyModal").modal("hide");
			$("#replycntSamll").html("[" + data.pageMaker.totalCount + "]");
		});
	}	
		var printPaging = function(pageMaker, target){
			var str = "";
			
			if(pageMaker.prev){
				str += "<li><a href = '" + (pageMaker.startPage -1) + "'> << </a></li>";
			}
			
			for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++){
				var strClass = pageMaker.cri.page == i? 'class=active': '';
				str += "<li " + strClass + "><a href = '" + i +"'>" + i + "</a></li>";
			}
			
			if(pageMaker.next){
				str += "<li><a href = '" + (pageMaker.endPage + 1) + "'> >> </a></li>";
			}
			
			target.html(str);
		};
	
	$("#repliesDiv").on("click", function(){
		if($(".timeline li").size() > 1){
			$(".replyLi").remove();
			$("#pagination li").remove();
			
			
			return;
		}
		
		getPaging("/replies/" + bno + "/1");
	});
	
	$(".pagination").on("click", "li a", function(event){
		event.preventDefault();
		
		replyPage = $(this).attr("href");
		
		getPaging("/replies/"+ bno + "/" +replyPage);
	});
	
	$("#replyAddBtn").on("click", function(){
		var replyer = $("#newReplyWriter").val();
		var replytext = $("#newReplyText").val();
		
		$.ajax({
			type: "post",
			url: "/replies/",
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method" : "post"
			},
			
			data: JSON.stringify({bno: bno, replyer: replyer, replytext: replytext}),
			dataType: "text",
			success: function(list){
				if(list == "success"){
					alert("등록되었습니다");
					getPaging("/replies/" + bno + "/" + replyPage);
					
					$("#newReplyWriter").val("");
					$("#newReplyText").val("");
				}
			}
		});
	});
	
	$(".timeline").on("click", ".replyLi", function(event){
		var reply = $(this);
		
		$("#replytext").val(reply.find(".timeline-body").text());
		$(".modal-title").html(reply.attr("data-rno"));
	});
	
	$("#replyModBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		
		$.ajax({
			type: "put",
			url: "/replies/" + rno,
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Overide" : "put"
			},
			
			dataType: "text",
			data:JSON.stringify({replytext:replytext}),
			
			success:function(result){
				console.log("result: " + result);
				if(result == "success"){
					alert("수정되었습니다");
					getPaging("/replies/" + bno + "/" + replyPage);
				}
			}
		});
	});
	
	$("#replyDelBtn").on("click", function(){
		var rno = $(".modal-title").html();
		var replytext = $("#replytext").val();
		$.ajax({
			type: "delete",
			url: "/replies/" + rno,
			headers: {
				"Content-Type" : "application/jsos",
				"X-HTTP-Method-Override" : "delete",
			},
			dataType: "text",
			success:function(result){
				console.log("result: " + result);
				if(result == "success"){
					alert("삭제되었습니다");
					getPaging("/replies/" + bno + "/" + replyPage);
				}
			}
		});
	});
	
	$(".uploadedList").on("click", ".mailbox-attachment-info a", function(event){
		
		var fileLink =  $(this).attr("href");
		
		if(checkImg(fileLink)){
			event.preventDefault();
			
			var imgTag = $("#popup_img");
			imgTag.attr("src", fileLink);
			
			console.log(imgTag.attr("src"));
			
			$(".popup").show("slow");
			imgTag.addClass("show");
		}
	});
	
	$("#popup_img").on("click", function(){
		$(".popup").hide("slow");
	});
</script>
<script>
	$(document).ready(function(){
		
		var formObj = $("form[role='form']");
		
		
		$("#modifyBtn").on("click", function(){
			formObj.attr("action", "/sboard/modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$("#removeBtn").on("click",function(){
			var replyCnt = $("#replycntSmall").html().replace(/[^0-9]/g, "");
			
			if(replyCnt > 0){
				alert("댓글이 달린 게시물을 삭제할 수없습니다.");
				
				return;
			}
			
			var arr = [];
			$(".uploadedList li").each(function(index){
				arr.push($(this.attr("data-src")));
			});
				
			if(arr.length > 0){
				$.post("/deleteAllFiles", {files: arr}, function(){});
			}	
			formObj.attr("action", "/sboard/removePage");
			formObj.submit();
		});
		
		$("#goListBtn").on("click", function(){
			formObj.attr("method","GET");
			formObj.attr("action", "/sboard/list");
			formObj.submit();
		});
		
		var bno = ${boardVO.bno};
		var template = Handlebars.compile($("#templateAttach").html());
		
		$.getJSON("/sboard/getAttach/" + bno, function(list){
			$(list).each(function(){
				
				var fileInfo = getFileInfo(this);
				
				var html = template(fileInfo);
				
				$(".uploadedList").append(html);
			});
		});
		
	});
</script>
<%@ include file="../include/footer.jsp"%>