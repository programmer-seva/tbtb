$(function(){
	getOrderList(formatDate(new Date().setMonth(new Date().getMonth() - 1)), formatDate(new Date()));
	/* 기간별 조회 */
	$('div.dateBtns > input:radio[name="chkDate"]').click(function(){
		let chkDate = $('div.dateBtns > input:radio:checked[name="chkDate"]').val();
		let today = new Date();
		let start;
		let end = formatDate(today);
		if(chkDate == '1month'){
			today.setMonth(today.getMonth() - 1);
		}else if(chkDate == '6month'){
			today.setMonth(today.getMonth() - 6);
		}else if(chkDate == '1year'){
			today.setMonth(today.getMonth() - 12);
		}
		start = formatDate(today);
		
		getOrderList(start, end);
		
	});
	
	$('#btnSearchDate').click(function(){
		let start = $('#startDate').val();
		let end = $('#endDate').val();
		if(start == "" || end == ""){
			alert('날짜를 선택해주세요.');
			return;
		}
		getOrderList(start, end);
	});
	
	
	//리스트 가져오기
	function getOrderList(start, end){
		$.ajax({
			url:'/Beauty/myshop/myorderSearchDate',
			type:'POST',
			data:{
				'start'	: start,
				'end'	: end
			},
			dataType:'json',
			success:function(data){
				$('#orderListTable > tbody').empty();
				inputOrderList(data);
				//테이블이 비었으면
				if($('#orderListTable > tbody').children().length == 0){
					emptyTable();
				}
			}
		});
	}
	
	
	//데이터 입력
	function inputOrderList(data){
		for (order of data) {
			let tag = "";
			tag += '<tr><td><div>';
			tag += '<a href="#"><img src="/Beauty/image/'+ order.thumb1 +'" alt=""/></a>';
			tag += '<ul class="info">';
			tag += '<li id="company"><a href="#">'+ order.company +'</a></li>';
			tag += '<li id="prodName"><a href="/shop/view?pno='+ order.prodNo +'">'+ order.prodName +'</a></li>';
			tag += '<li id="option">[옵션 : '+ order.color +', '+ order.size +']</li>';
			tag += '</ul></div></td>';
			tag += '<td>'+ order.rdate +'</td>';
			tag += '<td><a href="#">'+ order.ordNo +'</a></td>';
			tag += '<td><span id="disPrice">'+ order.totalPrice.toLocaleString() +'원</span><br/>';
			tag += '<span id="count">'+ order.count.toLocaleString() +'개</span></td>';
			tag += '<td><div class="btn-set tooltip">';
			tag += '<button type="button" class="btn btnTrack">배송 조회</button><br/>';
			tag += '<button type="button" class="btn">리뷰 쓰기</button><br/>';
			tag += '<button type="button" class="btn">반품/교환</button>';
			tag += '</div></td></tr>';
			
			$('#orderListTable > tbody').append(tag);
		}
	}
	
	//빈 테이블
	function emptyTable(){
		//카트 비었으면
		//테이블, 토탈테이블 삭제, div.emptyCart 삽입
		let tag = "<tr><td colspan='5'>장바구니에 담긴 상품이 없습니다.</td></tr>";
		$('#orderListTable > tbody').append(tag);
	}
	
	//날짜 포매팅 'yyyy-MM-dd'
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
	
	    return [year, month, day].join('-');
	}

});