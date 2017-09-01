
$(function(){
	
	boardListInit($("form[name='boardFrm']"));
	
	boardRegInit($("form[name='boardRegFrm']"));
	
});

function boardListInit($frm){
	if($frm.size() == 0) {
		return ;
	}
	
	var $goView = $(".goView", $frm);
	
	this.goViewClickListener = function() {
		$goView.click(function(e) {
			e.preventDefault();
			var id = $(this).attr("id");
			location.href = "/front/board/board.do?seq="+id;
		});
	}
	
	$("#searchInput", $frm).autocomplete({
		source : function(request, response) {
			$.ajax({
				type: 'get',
				url:'/front/board/auto.do',
				dataType : "json",
				data : {
					query : $("#searchInput").val()
				},
				success : function(data) {
					response($.map(data, function(item) {
						return {
							label : item.name,
							label : item.value
						}
					}));
				}
			});
		},
		minLength : 1,
		select : function(event, ui) {
		}
	});
	
	goViewClickListener();
}

function boardRegInit($frm) {
	if($frm.size() == 0) {
		return ;
	}
	
	var $btnSubmit = $("#btnSubmit", $frm),
		$btnDelete = $("#btnDelete", $frm),
		$edit = $("#edit", $frm),
		seq = $("#seq", $frm).val();

	
	this.frmValidate = function($frm) {
		var $title = $("#title", $frm),
			$contents = $("#contents", $frm),
			$reg_seq = $("#reg_seq", $frm);
		
		if($title.val() == "") {
			alert("제목을 입력해주세요.");
			return false;
		}
		
		if($contents.val() == "") {
			alert("내용을 입력해주세요.");
			return false;
		}
		
		if($reg_seq.val() == "") {
			alert("작성자를 입력해주세요.");
			return false;
		}
		
		return true;
	}
	
	this.btnSubmitClickListener = function() {
		$btnSubmit.click(function(e) {
			e.preventDefault();
			
			if(frmValidate($frm)) {
				$frm.submit();
			}
		});
	}
	
	this.btnDeleteClcikListener = function() {
		$btnDelete.click(function(e) {
			e.preventDefault();
			$edit.val(3);
			$frm.submit();
		});
	}
	
	
	btnSubmitClickListener();
	btnDeleteClcikListener();
}