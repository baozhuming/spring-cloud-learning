$(function(){
    var wwww = $(window).width(),
        hhhh = $(window).height(),
        offHeight = window.screen.availHeight,
        offWidth = window.screen.availWidth;
    console.log("wwww："+wwww+",hhhh:"+hhhh+",wid:"+offWidth+",hei:"+offHeight);
    $("body").css({
        "width":wwww,
        "height":hhhh,
        "overflow":"hidden"
    });
    Resize = function(){
        wwww = $(window).width();//这里用Jquery
        if(wwww<= (offWidth * 4/5) || hhhh <= (offHeight * 4/5)){
            $("body").css({
                "overflow":"auto"
            });
        }else{
            $("body").css({
                "overflow":"hidden"
            });
        }
    };
});