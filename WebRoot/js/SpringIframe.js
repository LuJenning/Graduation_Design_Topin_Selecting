/**
 * Spring内置Iframe框架js
 * @authors Spring Bless (429829320@qq.com)
 * @date    2018-05-24 17:19:53
 * @version v1.0
 */
function getStyle(obj,attr){
    return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj)[attr];
};
function SpringIframe(ms,target,mJson,callBack){
    /*s
        mJson = {
            url:'url',
            tTitle:'显示的标题'
        }
    */
    var This = this;
    var child = document.getElementById('spring-iframe-box');
    if(child)document.body.removeChild(child);
    var springIframe = document.createElement('div');
    mJson.tTitle = mJson.tTitle || '在这里显示操作标题';
    mJson.url = mJson.url || 'https://gitee.com/index/ent_poster/banner_5_2_a.png';
    springIframe.id = "spring-iframe-box";
    springIframe.innerHTML = '<div id="iframe-wrap">'
                           + '   <div id="iframe-top"><h3>'+mJson.tTitle+'</h3><span>X</span></div>'
                           + '   <div class="iframe-body">'
                           + '       <iframe class="spring-iframe" name="spring-iframe" src="'+mJson.url+'" frameborder="0"></iframe>'
                           + '   </div>'
                           + '</div>';
    ms.onmouseup = function(){
        callBack && callBack.call(this,springIframe,target,This);
    };
};
SpringIframe.prototype = {
    init : function(){
        var springIframeBox = document.getElementById('spring-iframe-box');
        var iframeTop = document.getElementById('iframe-top');
        var closeSpan = document.getElementById('iframe-top').getElementsByTagName('span')[0];
        var springIframeBody = document.getElementById('spring-iframe-box').querySelector('.iframe-body');
        var maxW = window.innerWidth,
            maxH = window.innerHeight,
            cw = getStyle(iframeTop.parentNode,'width').replace(/px/,'');

        springIframeBox.style.width = maxW + 'px';
        springIframeBox.style.height = maxH + 'px';
        iframeTop.parentNode.style.left = (maxW-cw)/2 + "px";
        iframeTop.parentNode.style.top = (maxH-600)/2 + "px";
        iframeTop.onmousedown = function(ev){
            ev = ev || window.event;
            var cx = ev.clientX,
                cy = ev.clientY;
            var p = iframeTop.parentNode;
            var ofl = p.offsetLeft,
                oft = p.offsetTop;
            document.onmousemove = function(eve){
                eve = eve || window.event;
                var mx = eve.clientX;
                var my = eve.clientY;
                var x = mx - cx + ofl;
                var y = my - cy + oft;
                p.style.left = x + "px";
                p.style.top =  y + "px";
                this.onmouseup = function(){
                    this.onmousemove = null;
                    this.onmouseup = null;
                };
            };
        };
        closeSpan.onclick = function(){
            springIframeBox.style.display = 'none';
        };
    }
};

function doIframe(param,mJson){
    new SpringIframe(param,document.body,mJson,function(item,target,obj){
        target.appendChild(item);
        obj.init();
        window.onresize = function(){
            obj.init();
        };
    });
};