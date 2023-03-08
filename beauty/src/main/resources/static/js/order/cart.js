$(function(){
    /* total Table에 쓰이는 변수 */
    let headCnt	= 0;
    let count 	= 0;
	let price 	= 0;
	let delivery= '2,500';
	let disPrice= 0;
	let total 	= 0;
    
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
		if ($("input:checkbox[name='chkCart']").is(":checked")==false) {
			/* 전부 선택*/
			sumAll();
		}else {
			/* 개별 선택*/
			sumChecked();
		}
	}
	
	/* 수량증가 */
	$('#wrap table a.btnIncrease').click(function(e){
		e.preventDefault();
		let cartNo = $(this).parent().parent().parent().children('td:nth-child(1)').children('input[type=checkbox]').val();
		$.ajax({
			url:'/Beauty/myshop/cartIncrease',
			type:'POST',
			data:{'cartNo': cartNo},
			dataType:'json',
			success:function(data){
				if(data == 1){
					location.reload();				
				}else{
					alert('변경실패');
				}
			}
		});
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

	/* 선택 구분 함수 */	
	function sumChecked(){
		initVal();
		$('input[name=chkCart]').each(function(){
			if($(this).is(":checked")){
				sum($(this));
			}
		});
		checkDelivery();
		inputTotal();
	}
	function sumAll(){
		initVal();
		$('input[name=chkCart]').each(function(){
			sum($(this));
		});
		checkDelivery();
		inputTotal();
	}
	
	/* 실제 기능 구현 함수 */
	function makeNum(nanValue){
		return parseInt(nanValue.split(',').join(""));
	}
	function initVal(){
		headCnt = 0;
		count 	= 0;
		price 	= 0;
		delivery= '2,500';
		disPrice= 0;
		total 	= 0;
	}
	function sum(arg){
		let tds = arg.parent().parent().children();
		headCnt += 1;
		count 	= makeNum(tds[4].children[0].children[0].value);
		price 	+= (makeNum(tds[3].children[0].innerText) * count);
		if(tds[3].children[1] != null){
			disPrice += ( (makeNum(tds[3].children[0].innerText)-makeNum(tds[3].children[2].innerText) ) * count );
		}
		total 	+= makeNum(tds[6].children[0].innerText);
	}
	function checkDelivery(){
		if(total >= 50000){
			delivery = '무료';
		}else{
			delivery = '2,500원';
			total += 2500;
		}
	}
	function inputTotal(){
		$('#totalCount').text(headCnt);
		$('#totalPrice').text(price.toLocaleString()+'원');
		$('#totalDelivery').text(delivery);
		$('#totalDisPrice').text(disPrice.toLocaleString()+'원');
		$('#totalTotalPrice').text(total.toLocaleString()+'원');
	}

});