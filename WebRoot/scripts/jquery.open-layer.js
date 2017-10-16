///-------------------------------------------------------------------------
//jQuery弹出窗口
//--------------------------------------------------------------------------
/*参数：[可选参数在调用时可写可不写,其他为必写]
----------------------------------------------------------------------------
title:	窗口标题
order:页面定义div的后缀号码，从0开始
width:	内容宽度
height:	内容高度
drag:  是否可以拖动(true为是,false为否)
time:	自动关闭等待的时间，为空是则不自动关闭
showbg:设置是否显示遮罩层(true为显示,false为不显示)
show_close_btn:是否显示右上角的关闭按钮

------------------------------------------------------------------------*/
//示例:
//------------------------------------------------------------------------
//tipsWindown("标题内容","0","500","400","true","3000","true")
//------------------------------------------------------------------------

var _member_order = 0; //全局变量。div的后缀号码
var _member_switch = true;//全局变量。 只允许单窗口弹出

function tipsWindown(title, order, width, height, show_close_btn, time, showbg) {
    if (!_member_switch) {return;}
    _member_order = order;
    _member_switch = false;
    var width = width >= 950 ? this.width = 950 : this.width = width;     //设置最大窗口宽度
    var height = height >= 527 ? this.height = 527 : this.height = height;  //设置最大窗口高度
    $("#windown-box" + order).fadeIn('fast');
    $("#windown-title"+order+" label").html(title);
    if (showbg == true) { 
    	$("#windownbg").show();
    } else { 
    	$("#windownbg").hide(); 
    };
    if (show_close_btn == true) { 
    	$("#windown-close" + order).show();
    } else {
    	$("#windown-close" + order).hide();
    }
    $("#windownbg").animate({ opacity: "0.5" }, "normal"); //设置透明度
    $("#windownbg").css("height", $(document).height() + "px");//根据页面的高度，动态为遮罩层赋高度值
    if (height >= 527) {
        $("#windown-title" + order).css({ width: (parseInt(width) + 22) + "px" });
        $("#windown-content"+order).css({ width: (parseInt(width) + 17) + "px", height: height + "px" });
    } else {
        $("#windown-title" + order).css({ width: (parseInt(width) + 10) + "px" });
        $("#windown-content" + order).css({ width: width + "px", height: height + "px" });
    }
    var cw = document.documentElement.clientWidth, ch = document.documentElement.clientHeight, est = document.documentElement.scrollTop;
    var _version = $.browser.version;
    if (_version == 6.0) {
        $("#windown-box" + order).css({ left: "50%", top: (parseInt((ch) / 2) + est) + "px", marginTop: -((parseInt(height) + 53) / 2) + "px", marginLeft: -((parseInt(width) + 32) / 2) + "px", zIndex: "999999" });
    } else {
        $("#windown-box" + order).css({ left: "50%", top: "50%", marginTop: -((parseInt(height) + 53) / 2) + "px", marginLeft: -((parseInt(width) + 32) / 2) + "px", zIndex: "999999" });
    };
    var Drag_ID = document.getElementById("windown-box" + order), DragHead = document.getElementById("windown-title" + order);

    var moveX = 0, moveY = 0, moveTop, moveLeft = 0, moveable = false;
    if (_version == 6.0) {
        moveTop = est;
    } else {
        moveTop = 0;
    }
    var sw = Drag_ID.scrollWidth, sh = Drag_ID.scrollHeight;
    DragHead.onmouseover = function (e) {
        //if (drag == "true") { DragHead.style.cursor = "move"; } else { DragHead.style.cursor = "default"; }
    };
    DragHead.onmousedown = function (e) {
        //if (drag == "true") { moveable = true; } else { moveable = false; }
        e = window.event ? window.event : e;
        var ol = Drag_ID.offsetLeft, ot = Drag_ID.offsetTop - moveTop;
        moveX = e.clientX - ol;
        moveY = e.clientY - ot;
        document.onmousemove = function (e) {
            if (moveable) {
                e = window.event ? window.event : e;
                var x = e.clientX - moveX;
                var y = e.clientY - moveY;
                if (x > 0 && (x + sw < cw) && y > 0 && (y + sh < ch)) {
                    Drag_ID.style.left = x + "px";
                    Drag_ID.style.top = parseInt(y + moveTop) + "px";
                    Drag_ID.style.margin = "auto";
                }
            }
        }
        document.onmouseup = function () { moveable = false; };
        Drag_ID.onselectstart = function (e) { return false; }
    }
    if (time == "" || typeof (time) == "undefined") {
        $("#windown-close" + order).click(function () {
            showselect('t123_')
            $("#windownbg").hide();
            $("#windown-box" + order).fadeOut("fast");
            _member_switch = true;
        });
    } else {
        setTimeout(closeWindown, time);
    }
    hideselect('t123_');
}
var closeWindown = function () {
    showselect('t123_');
    $("#windownbg").hide();
    $("#windown-box" + _member_order).fadeOut("fast");
    _member_switch = true;
}
var closeSimple = function () {
    showselect('t123_');
    $("#windownbg").hide();
    $("#windown-box" + _member_order).hide();
    _member_switch = true;
}

function hideselect(per) {
    var _version = $.browser.version;
    if (_version == 6.0) {
        $("select", document).each(function () {
            if ($(this).attr('name')) {
                if ($(this).attr('name').substring(0, 5) == per) {
                    name = $(this).attr('name').substring(5);
                    $(this).attr('name', name);
                    $(this).css('display', '');
                }
                if ($(this).css('display') != 'none') {
                    name = per + $(this).attr('name');
                    $(this).attr('name', name);
                }
                $(this).css('display', 'none');
            }
        });
    }
}
function showselect(per) {
    var _version = $.browser.version;
    if (_version == 6.0) {
        $("select", document).each(function () {
            if ($(this).attr('name')) {
                if ($(this).attr('name').substring(0, 5) == per) {
                    name = $(this).attr('name').substring(5);
                    $(this).attr('name', name);
                    $(this).css('display', '');
                }
            }
        });
    }
}