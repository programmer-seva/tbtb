/**
 * 날짜 : 2023/03/07
 * 이름 : 강중현 
 * 내용 : Beauty member register js
 */
//정규식
//let regUid   = /^[a-z]+[a-z0-9]{3,12}$/g;
let regName  = /^[가-힣]{2,4}$/;
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
let regPhone = /^\d{3}-\d{3,4}-\d{4}$/;
let regPass  = /^.*(?=^.{8,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
// 폼 데이터 검증 결과 상태변수
//let checkUid    = false;
let checkPass   = false;
let checkName   = false;
let checkPhone  = false;
let checkEmail  = false;

$(function(){

	// 이메일 유효성 검사
	$('input[name=uid]').focusout(function(){
		let email = $(this).val();
		if(!email.match(regEmail)){
			checkEmail = false;
			$('.resultUid').css('color', 'red').text('이메일이 유효하지 않습니다.');
		}else{
			checkEmail = true;
			$('.resultUid').css('color', 'green').text('유효한 이메일 입니다.');
		}			
	});
	
	// 비밀번호 유효성 검사
	$('input[name=pass2]').focusout(function(){
		checkPass = false;
		let pass1 = $('input[name=pass1]').val();
		let pass2 = $(this).val();
		
		if(pass1 == pass2){
			if(pass1.match(regPass)){
				$('.resultPass').css('color','green').text('비밀번호가 일치합니다.');
				checkPass = true;
			}else{
				$('.resultPass').css('color','red').text('영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해주세요.');
			}
		}else{
			$('.resultPass').css('color','red').text('비밀번호가 일치하지 않습니다.');
		}
	});
	
	// 이름 유효성 검증
	$('input[name=name]').focusout(function(){
		let name = $(this).val();
		
		if(!name.match(regName)){
			checkName = false;
			$('.resultName').css('color', 'red').text('이름은 한글 2자 이상 이어야 합니다.');
		}else{
			checkName = true;
			$('.resultName').css('color', 'green').text('이름은 한글 2자 이상 이어야 합니다.');
		}
	});
	
	// 휴대폰 유효성 검사
	$('input[name=phone]').focusout(function(){
		let phone = $(this).val();
		
		if(!phone.match(regPhone)){
			checkPhone = false;
			$('.resultPhone').css('color', 'red').text('휴대폰이 유효하지 않습니다.');
		}else{
			checkPhone = true;
			$('.resultPhone').css('color', 'green').text('유효한 휴대폰 입니다.');
		}
	});
	$('.mb > form').submit(function(){
		////////////////////////////////////
		// 폼 데이터 유효성 검증(Validation)
		////////////////////////////////////
		//비밀번호 검증
		if(!checkPass){
			alert('비밀번호를 확인 하십시오.');
			return false;
		}
		//이름 검증
		if(!checkName){
			alert('이름을 확인 하십시오.');
			return false;
		}
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