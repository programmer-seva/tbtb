//product-list 메뉴 슬라이드
$(document).ready(function(){
    $('aside > ul > li > a').click(function(){
        $(this).parent().children('ul').slideToggle();
    });
});

//product-register 카테고리 분류
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