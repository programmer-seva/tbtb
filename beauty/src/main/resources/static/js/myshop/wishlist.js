$(function(){

	/* 위시리스트 전체삭제 */
	$('.wishListFrame > div.wishListHead > a.deleteAllWish').click(function(e){
		e.preventDefault();
		if(confirm('위시리스트를 비우시겠습니까?')){
			$.ajax({
				url:'/Beauty/myshop/deleteAllWish',
				type:'POST',
				success:function(data){
					if(data == 1){
						$('.wishListFrame > article.wishList').empty();
						$('#headCount').text("0");
						$('.wishListFrame > article.wishList').append('<div class="emptyWish">찜한 상품이 없습니다.</div>');
					}
				}
			});
		}else{
			return;
		}
	});
	
	/* 수량감소 */
	$('#wrap table a.btnDecrease').click(function(e){
		e.preventDefault();
		let amount = parseInt($('count').val());
		if(amount<=1){ 
			alert('1개보다 작게 설정할 수 없습니다.');
			return;
		}
		let cartNo = $(this).parent().parent().parent().children('td:nth-child(1)').children('input[type=checkbox]').val();
		$.ajax({
			url:'/Beauty/myshop/cartDecrease',
			type:'POST',
			data:{'cartNo': cartNo},
			dataType:'json',
			success:function(data){
				if(data == 1){
					location.reload();				
				}else{
					alert('1개보다 작게 설정할 수 없습니다.');
				}
			}
		});
	});

});