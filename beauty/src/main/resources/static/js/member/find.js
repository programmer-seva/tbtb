
let regPhone = /^\d{3}-\d{3,4}-\d{4}$/;
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

let checkEmail  = false;
let checkPhone  = false;

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
	
	
	//휴대폰 유효성 검사
	$('input[name=phone]').focusout(function() {
		let phone = $(this).val();

		if (!phone.match(regPhone)) {
			checkPhone = false;
			$('.resultPhone').css('color', 'red').text('휴대폰 번호 형식이 맞지 않습니다.');
		} else {
			checkPhone = true;
			$('.resultPhone').css('color', 'green').text('유효한 휴대폰 입니다.');
		}
	});
	
	
	//email
	$('.sendCode').click(function(e){
		e.preventDefault();
		let email = $('input[name=uid]').val();
		if(email == ''){
			alert('이메일을 입력해주세요');
			return;
		}else{
			alert('인증번호가 발송되었습니다.');
		}
		$.ajax({
			url: '/Beauty/member/emailAuth',
			method: 'post',
			data: {"email": email},
			dataType: 'json',
			success: function(data){
				if(data.status > 0){
					//메일전송 성공
					$('.auth').show();
					receivedCode = data.code;
				}else{
					//메일전송 실패
					alert('메일 전송에 실패했습니다. 다시 시도해주세요');
				}
			}
		});
	});
	
	//email code check
	$('.btnPw').click(function(){
		let insertCode = $('input[name=insertCode]').val();
		if(insertCode == ''){
			alert('이메일 확인 후 코드를 입력해주세요.');
			return;
		}

		if(code == receivedCode){
			$('.auth').hide();
			$('input[name=email]').attr('readonly', true);
			$('.emailResult').css('color','green').text('인증완료 되었습니다.');
			checkEmail = true;
		}
	});
	
	
	
	$('.lg > section').submit(function(){
		////////////////////////////////////
		// 폼 데이터 유효성 검증(Validation)
		////////////////////////////////////
	
		//이메일 검증
		if(!checkEmail){
			alert('이메일을 확인 하십시오.');
			return false;
		}
		//휴대폰 검증
		if(!checkPhone){
			alert('휴대폰을 확인 하십시오.');
			return false;
		}
		//최종 전송
		return true;
	});
	
	
});