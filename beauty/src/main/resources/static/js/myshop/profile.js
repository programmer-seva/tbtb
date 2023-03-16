$(function(){

	/* 위시리스트 전체삭제 */
	$('#btnCheckPW').click(function(e){
		e.preventDefault();
		let pw = $('#pw').val();
		$.ajax({
			url:'/Beauty/myshop/checkPW',
			type:'POST',
			data:{'pw': pw},
			dataType:'json',
			success:function(data){
				if(data == 1){
					
				}else{
					alert('비밀번호가 일치하지 않습니다.');
				}
			}
		});
		
	});

});