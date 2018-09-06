/**
 * param tip提示组件
 * @authors Spring Bless (429829320@qq.com)
 * @date    2018-05-26 00:29:10
 * @version V1.1
 */
!function(param){
	var w = window.innerWidth,
		h = window.innerHeight;
	function loading(mJson){
        /*
            mJson = {
                el : tagNameId，
                content ： '提示内容',
            }
        */
        var tipDom = document.getElementById(mJson.el);
        if(tipDom)tipDom.parentNode.removeChild(tipDom);
        var inTip = document.createElement('div');
        inTip.id = mJson.el;
        inTip.className = 'me_loading magictime bottomToCenter';
        inTip.innerHTML = mJson.content;
        inTip.style.left = (w-200)/2 + 'px';
        inTip.style.top = (h+17)/2 + 'px';
        document.body.appendChild(inTip);
    };
    window.onresize = function(){
    	w = window.innerWidth,
		h = window.innerHeight;
    };
    window.loading = loading;
}(window);
