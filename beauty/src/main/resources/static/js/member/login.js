
let regPhone = /^\d{3}-\d{3,4}-\d{4}$/;

$(function() {
	$('.member').click(function() {
		$('.m1').css('display', 'block');
		$('.m2').css('display', 'none');
		$('.member').addClass('current');
		$('.nomember').removeClass('current');
	});
	$('.nomember').click(function() {
		$('.m2').css('display', 'block');
		$('.m1').css('display', 'none');
		$('.nomember').addClass('current');
		$('.member').removeClass('current');
	});
	$('.findId').click(function() {
		$('.m1').css('display', 'block');
		$('.m2').css('display', 'none');
		$('.findId').addClass('current');
		$('.findPw').removeClass('current');
	});
	$('.findPw').click(function() {
		$('.m2').css('display', 'block');
		$('.m1').css('display', 'none');
		$('.findPw').addClass('current');
		$('.findId').removeClass('current');
	});
	$('input[name=uid]').keydown(function() {
		if ($(this).val().trim() != "") {
			$(this).next().css('display', 'block');
		} else {
			$(this).next().css('display', 'none');
		}
	});
	$('input[name=name]').keydown(function() {
		if ($(this).val().trim() != "") {
			$(this).next().css('display', 'block');
		} else {
			$(this).next().css('display', 'none');
		}
	});
	$('.close').click(function() {
		$(this).prev().val("");
		$(this).css('display', 'none');
	});
	$('.eye').click(function() {
		let t = $(this).text();
		if (t == "visibility_off") {
			$(this).text("visibility");
			$('input[name=password]').attr('type', 'text');
		} else {
			$(this).text("visibility_off");
			$('input[name=password]').attr('type', 'password');
		}
	});
	
	
	
	
	
	
	
	
	
});