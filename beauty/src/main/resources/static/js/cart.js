$(function(){
    
	/* 체크박스 처리*/
	/* 전체선택시 */
	$('input[name=chkAll]').click(function(){
		let checkList = $('input[name=chkCart]');
		if($(this).is(":checked")){
		    checkList.prop("checked", true);
		}else{
		    checkList.prop("checked", false);
		}
		check();
	});
	/* 개별선택 -> 모두 체크한 경우 전체선택 자동체크 */
	$('input[name=chkCart]').click(function(){
		let isAll = true;
		$("input[name=chkCart]").each(function(){
	        isAll = isAll && $(this).is(":checked");
	    });
	    $('input[name=chkAll]').prop("checked", isAll);
	    check();
	});
	/* 전부 체크 해제인 경우*/
	function check() {
		if ($("input:checkbox[name='cartNo']").is(":checked")==false) {
			//to do sum all
		}
	}
	
	/* 수량증가 */
	$('#wrap table a.btnIncrease').click(function(){
		
		$.ajax({
			url:'/Beauty/myshop/cartIncrease',
			type:'POST',
			data:{'cartNo': cartNo},
			dataType:'json',
			success:function(data){
				location.reload();
			}
		});
	});
	
	/* 수량감소 *//*
	$('#wrap table a.btnDecrease').click(function(e){
		//let amount = parseInt($('count').val());
		//if(amount<=1){ return; }
		let cartNo = 
		console.log('감소');
		$.ajax({
			url:'/Beauty/myshop/cartDecrease',
			type:'POST',
			data:{'cartNo': cartNo},
			dataType:'json',
			success:function(data){
				location.reload();
			}
	});*/
	
	$('#wrap table a.btnDecrease').click(function(e){
		e.preventDefault();
		let cartNo = $(this).parent().parent().parent().children('td:nth-child(1)').children('input[type=checkbox]').val();
		console.log(cartNo);
	});
	
	

});