/* admin-prodcut-list */
$(document).ready(function(){
	/* 메뉴 제어 */
	$(".allCheck").attr("checked", false);
	//메뉴 클릭했을 때 대분류별 페이지 이동
    $('aside > ul > li > a').click(function(){
		var arg1 = $(this).text();
		
		location.href="/Beauty/admin/product/list?param1=prodNo&arg1="+arg1;
	});
	
	//기본적으로 메뉴가 전체체크 되어있게
	$("ul:visible").find(".allCheck").attr("checked", true);
	
	if($("ul:visible").find('.allCheck').prop("checked")== true){
		$("ul:visible").find('input[name=category2]').attr("checked", true);
	}
	
	//메뉴 클릭하면 전체체크
	$("aside> ul > li > a").on("click", function(){
		$('ul:visible').find("input[type=checkbox]").attr("checked", true);
	});
	
	//전체체크박스 클릭
	$("ul:visible").find('.allCheck').on("click", function(){    
		if($(this).prop("checked")){
			$("ul:visible").find("input[name=category2]").prop("checked", true);	
		}else{
			$("ul:visible").find("input[name=category2]").prop("checked", false);	
		}
	});
	
    //체크박스 개별선택
    $("ul:visible").find("input[name=category2]").on("click",function(){
    	if($(this).prop("checked") == false){
    		$("ul:visible").find(".allCheck").prop("checked", false);
    	}else if($(this).closest("ul").find(":checked").length == $(this).closest("ul").find("input[name=category2]").length){
    		$("ul:visible").find(".allCheck").prop("checked", true);
    	}
    });

	/* 상품목록 체크박스 제어 */
	//상품목록 전체체크박스 클릭
	$("#checkAll").on("click", function(){    
		if($(this).prop("checked")){
			$(".rowCheck").prop("checked", true);	
		}else{
			$(".rowCheck").prop("checked", false);	
		}
	});
	
    //상품목록 체크박스 개별선택
    $(".rowCheck").on("click",function(){
    	if($(this).prop("checked") == false){
    		$("#checkAll").prop("checked", false);
    	}else if($(".rowCheck:checked").length == $(".rowCheck").length){
    		$("#checkAll").prop("checked", true);
    	}
    });
    
    //체크박스 선택삭제
	$(".delete").on("click",function(){
		
		var checkBoxArr = new Array();
		
		$(".rowCheck:checked").each(function(){
			checkBoxArr.push($(this).val());
			
		});
		
		if(checkBoxArr.length == 0){
			alert("선택된 글이 없습니다.");
		}else{
			$.ajax({
				url:'/Beauty/admin/product/list/delete',
				type:'post',
				data:{'checkBoxArr':checkBoxArr},
				success:function(data){
					console.log("data : ", data);
					location.reload();
				}	
			});
		}
	});
});

/* product-register 카테고리 분류 */
function cateChange(){
    		let outer = ["가디건","자켓/코트","패딩/점퍼","집업/조끼"];
    		let top = ["티셔츠","니트/스웨터","맨투맨/후드","조끼/나시"];
    		let bottom = ["스커트","데님","팬츠","슬랙스","레깅스"];
    		let dress = ["원피스","투피스","점프수트"];
    		let etc = ["신발","가방","모자","쥬얼리"];
    		
    		let target = $("#cate1").val();
    		let opt;
    		console.log(target);
    		if(target == '1'){
    			opt = outer;
    		}else if(target == '2'){
    			opt = top;
    		}else if(target == '3'){
    			opt = bottom;
    		}else if(target == '4'){
    			opt = dress;
    		}else if(target == '5'){
    			opt = etc;
    		}
    		
    		$("#cate2").empty();
    		$("#cate2").append('<option>소분류</option>');
    		
    		for(var i = 0; i < opt.length; i++){
    			$("#cate2").append('<option>'+opt[i]+'</option>');
    		}
    		
    	}