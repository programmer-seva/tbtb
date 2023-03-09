$(function(){

	/* tableBtns */
	/* No-member 관심상품등록 */
	$('div.cartFrame > article.cartList > table > tbody td > a.btnTableAddWishNon').click(function(e){
		e.preventDefault();
		alert('로그인이 필요합니다');
		//로그인창이동 or 그대로
		
	});

	/* cartBtns */
	/* No-member 카트비우기 */
	$('#btnDeleteAllCartNon').click(function(e){
		e.preventDefault();
		if(confirm('장바구니를 비우시겠습니까?')){
			$.ajax({
				url:'/Beauty/order/deleteAllCartNon',
				type:'POST',
				data:{},
				dataType:'json',
				success:function(data){
					if(data == 1){
						emptyTable();
					}else{
					}
				}
			});
		}else{
			return;
		}
	});
});