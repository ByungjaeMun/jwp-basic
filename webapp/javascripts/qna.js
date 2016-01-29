$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
	e.preventDefault();
	
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
	    type : 'post',
	    url : '/api/qna/addanswer.next',
	    data : queryString,
	    dataType : 'json',
	    error: onError,
	    success : onSuccess
	});
}

function onSuccess(data, status){
	var question = data.newQuestion;
	var answer = data.answer;
	var questionEle = "<div class = question><h3> 댓글 수 : " + question.countOfComment + "</h3></div>";
	var answerEle = "<div class='answer'><b>" + answer.writer + "</b><p>" + answer.contents + "</p>" + 
		"<a href='/api/qna/deleteanswer.next?answerId=" + answer.answerId + "'>삭제</a></div>";
	$(".question").html(questionEle);
	$(".answers").prepend(answerEle);
}

function onError(data, status) {
	alert("error");
}

$(".answerDelete").click(deleteAnswer);

function deleteAnswer(e){
	e.preventDefault();
	console.log("삭제 메서드");
	
	var answerEle = $(this);
	
	$.ajax({
	    type : 'get',
	    url : answerEle.attr("href"),
//	    data : queryString,
	    dataType : 'json',
	    error: onDeleteError,
	    success : function(data){
	    	if(data.status)
	    	{
	    		console.log("성공");
	    		answerEle.parent().remove();
	    	}
	    	
	    }
	});
}

function onDeleteError(data,status){
	alert("error");
}






