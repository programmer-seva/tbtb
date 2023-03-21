/**
 * 
 */
$(function(){
	$('.selectColor').click(function() {
		$('.selectColor').removeClass('select');
		$(this).addClass('select on');
		$(this).siblings('span').children().text($(this).text());
	});
	$(document).on('click', '#close', function() {
		let tot = $('input[name=total]').val();
		let cnt = $(this).prevAll('div').children('.count').text();
		result(tot * cnt * -1);

		$(this).closest('.orderItems').remove();
		if ($('#close').length == 0) {
			$('.result').css('display', 'none');
		}
	});
	$(document).on('click', '#remove', function() {
		let cnt = $(this).next().text();
		if (cnt == 1) {
			alert('최소 1개이상 주문가능합니다.');
			return false;
		}
		cnt = Number(cnt) - 1;
		let tot = $('input[name=total]').val();
		$(this).parents().siblings('.total').text(format(tot * cnt));
		$(this).next().text(cnt);
		result(tot * -1);
	});
	$(document).on('click', '#add', function() {
		let cnt = $(this).prev().text();
		if (cnt == 10) {
			alert('최대 10개까지 주문가능합니다.');
			return false;
		}
		cnt = Number(cnt) + 1;
		let tot = $('input[name=total]').val();
		$(this).parents().siblings('.total').text(format(tot * cnt));
		$(this).prev().text(cnt);
		result(tot);
	});
	function format(num) {
		return Number(num).toLocaleString();
	}
	function result(num) {
		let result = $('.resultprice').text();
		$('.resultprice').text(Number(result) + Number(num));
	}
	//장바구니
	$('.cart').click(function() {
		check();
		let jsonArray = resultArray();
		$.ajax({
			url: '/Beauty/addCart',
			method: 'POST',
			data: { jsonArray: JSON.stringify(jsonArray) },
			dataType: 'JSON',
			success: function(data) {
				if (data.result > 0) {
					$('.goCart').css('display', 'block');
				}
			}
		});
	});
	//바로구매
	$('.order').click(function() {
		check();
		let jsonArray = resultArray();
		console.log(jsonArray);
		$.ajax({
			url: '/Beauty/addOrder',
			method: 'POST',
			data: { jsonArray: JSON.stringify(jsonArray) },
			dataType: 'JSON',
			success: function(data) {
				if (data.result > 0) {
					location.href = "/Beauty/order/orderform";
				}
			}
		});
	});

	function check() {
		if ($('.orderItems').length == 0) {
			alert('상품옵션을 선택해주세요.');
			return false;
		}
	}
	function resultArray() {
		console.log($('article.orderItems').length);
		let array = [];
		$("article.orderItems").each(function(index, item) {
			let color = $(item).children('input[name=color]').val();
			let size = $(item).children('input[name=size]').val();
			let count = $(item).children('div').children('span.count').text();
			let jsonData = {
				'prodNo': prodNo,
				'count': count,
				'color': color,
				'size': size
			}
			array.push(jsonData);
		});
		return array;
	}

	$('.continue').click(function(e) {
		e.preventDefault;
		$('.goCart').css('display', 'none');
	});

	$('#itemInfo').click(function() {
		$('.detail').children().children().children().attr('class', 'off');
		$(this).addClass('current');
		$('#d1').css('display', 'block');
		$('#d2').css('display', 'none');
		$('#d3').css('display', 'none');
		$('#d4').css('display', 'none');
	});
	$('#shopGuide').click(function() {
		$('.detail').children().children().children().attr('class', 'off');
		$(this).addClass('current');
		$('#d1').css('display', 'none');
		$('#d2').css('display', 'block');
		$('#d3').css('display', 'none');
		$('#d4').css('display', 'none');
	});
	$('#itemReview').click(function() {
		$('.detail').children().children().children().attr('class', 'off');
		$(this).addClass('current');
		$('#d1').css('display', 'none');
		$('#d2').css('display', 'none');
		$('#d3').css('display', 'block');
		$('#d4').css('display', 'none');
	});
	$('#itemQna').click(function() {
		$('.detail').children().children().children().attr('class', 'off');
		$(this).addClass('current');
		$('#d1').css('display', 'none');
		$('#d2').css('display', 'none');
		$('#d3').css('display', 'none');
		$('#d4').css('display', 'block');
	});
	$(document).on('click', 'article.review', function() {
		let txt = $(this).children().children('.more').text();
		if (txt == "... 더보기") {
			$(this).children().children('p').css('height', 'auto');
			$(this).children().children('.more').text('리뷰 접기');
		} else {
			$(this).children().children('p').css('height', '55px');
			$(this).children().children('.more').text('... 더보기');
		}
	});
	$('.orderby').click(function() {
		$('.orderby').removeClass('sold');
		$(this).addClass('sold');
	});
});