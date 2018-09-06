/**
 * SpringLimit 前端分页组件
 * @authors Spring Bless (429829320@qq.com)
 * @date    2018-05-26 00:29:10
 * @version V1.1
 */
!function(param){
    function SpringLimitPage(mJson){
        /*
            mJson = {
                parentContainer : 父级容器,--》值是字符串
                idx : 页面序号,
                max : 记录总数,
                pageSize : 每页大小
            }
        */
    	$(mJson.parentContainer).empty();
        this.mJson = mJson;
        this.parentContainer = this.mJson.parentContainer;
        this.maxLimitPage = Math.ceil(this.mJson.max/this.mJson.pageSize);
        this.idxLImitPage = this.mJson.idx;
        this.curentPageSize = this.mJson.pageSize;
        this.limitTemplate = '<div class="limit-box">'
                             +'   <div class="limit-left">'
                             +'       <div class="left-box">'
                             +'           <p class="left-lc">当前页第:<span limit-curent-size="limit-curent-size">'+this.idxLImitPage+'</span>页</p>'
                             +'           <p class="left-ra">共:<span limit-all-size="limit-all-size">'+this.maxLimitPage+'</span>页</p>'
                             +'       </div>'
                             +'   </div>'
                             +'   <div class="limit-right">'
                             +'       <div class="right-box">'
                             +'           <ul>'
                             +'               <li linit-page="limit-page-item">首页</li>'
                             +'               <li linit-page="limit-page-item">上一页</li>'
                             +'               <li linit-page="limit-page-item">下一页</li>'
                             +'               <li linit-page="limit-page-item">'
                             +'                   <input limit-input="limit-input" type="text" value="'+this.idxLImitPage+'">'
                             +'               </li>'
                             +'               <li linit-page="limit-page-item">跳转</li>'
                             +'               <li linit-page="limit-page-item">末页</li>'
                             +'           </ul>'
                             +'       </div>'
                             +'   </div>'
                             +'</div>';
        $(this.parentContainer).append(this.limitTemplate);
    };

    SpringLimitPage.prototype = {
        init : function(url,initCallBack){
            /*
                initJson = {
                    url : URL,
                    callBack : 回调函数
                }
            */
            var This = this;
            This.URLAction = url;
            var limitDom = $(This.parentContainer).find('li[linit-page="limit-page-item"]');
            var curentSize = $(This.parentContainer).find('span[limit-curent-size="limit-curent-size"]');
            var allSize = $(This.parentContainer).find('span[limit-all-size="limit-all-size"]');
            var tpage = $(This.parentContainer).find(limitDom[3]).find('input[type="text"]');
            for(var i=0;i<limitDom.length;i++){
                switch (i) {
                    case 0:
                        $(limitDom[i]).click(function(){
                            if(This.idxLImitPage == 1)return alert("已经是首页了");
                            This.idxLImitPage = 1;
                            This.doLimitPage(This.getLImitStartPageSize(This.curentPageSize,This.idxLImitPage),This.getLImitEndPageSize(This.curentPageSize,This.idxLImitPage),function(data,statues){
                                var flag = initCallBack && initCallBack.call(This,data,statues);
                                if(flag == true || flag == undefined || flag == null){
                                	This.changeValue({'tpage':tpage,'curentSize':curentSize,'idx':This.idxLImitPage});
                                };
                            });
                        });
                        break;
                    case 1:
                        $(limitDom[i]).click(function(){
                            if(This.idxLImitPage == 1)return alert("已经是第一页了");
                            This.idxLImitPage = This.idxLImitPage - 1;
                            This.doLimitPage(This.getLImitStartPageSize(This.curentPageSize,This.idxLImitPage),This.getLImitEndPageSize(This.curentPageSize,This.idxLImitPage),function(data,statues){
                                var flag = initCallBack && initCallBack.call(This,data,statues);
                                if(flag == true || flag == undefined || flag == null){
                                	This.changeValue({'tpage':tpage,'curentSize':curentSize,'idx':This.idxLImitPage});
                                };
                            });
                        });
                        break;
                    case 2:
                        $(limitDom[i]).click(function(){
                            if(This.idxLImitPage == This.maxLimitPage)return alert("已经是最后一页了");
                            This.idxLImitPage = This.idxLImitPage + 1;
                            This.doLimitPage(This.getLImitStartPageSize(This.curentPageSize,This.idxLImitPage),This.getLImitEndPageSize(This.curentPageSize,This.idxLImitPage),function(data,statues){
                                var flag = initCallBack && initCallBack.call(This,data,statues);
                                if(flag == true || flag == undefined || flag == null){
                                	This.changeValue({'tpage':tpage,'curentSize':curentSize,'idx':This.idxLImitPage});
                                };
                            });
                        });
                        break;
                    case 4:
                        $(limitDom[i]).click(function(){
                            var text = $(tpage).val().trim();
                            var reg = /^[1-9]{1,}/;
                            if(reg.test(text)){
                                if(text == This.idxLImitPage){
                                    alert("已经在当前页了");
                                    return;
                                };
                                (text >= This.maxLimitPage)?This.idxLImitPage = This.maxLimitPage - 1:This.idxLImitPage = text;
                                if(This.idxLImitPage == This.maxLimitPage)return alert("已经是最后一页了");
                                This.idxLImitPage = This.idxLImitPage + 1;
                                This.doLimitPage(This.getLImitStartPageSize(This.curentPageSize,This.idxLImitPage),This.getLImitEndPageSize(This.curentPageSize,This.idxLImitPage),function(data,statues){
                                    var flag = initCallBack && initCallBack.call(This,data,statues);
                                    if(flag == true || flag == undefined || flag == null){
                                    	This.changeValue({'tpage':tpage,'curentSize':curentSize,'idx':This.idxLImitPage});
                                    };
                                });
                            }else{
                                $(tpage).focus();
                            }
                        });
                        break;
                    case 5:
                        $(limitDom[i]).click(function(){
                            if(This.idxLImitPage == This.maxLimitPage)return alert("已经是最后一页了");
                            This.idxLImitPage = This.maxLimitPage;
                            This.doLimitPage(This.getLImitStartPageSize(This.curentPageSize,This.idxLImitPage),This.getLImitEndPageSize(This.curentPageSize,This.idxLImitPage),function(data,statues){
                                var flag = initCallBack && initCallBack.call(This,data,statues);
                                if(flag == true || flag == undefined || flag == null){
                                	This.changeValue({'tpage':tpage,'curentSize':curentSize,'idx':This.idxLImitPage});
                                };
                            });
                        });
                    break;
                }
            }
        },
        getLImitStartPageSize : function(pageSize,idxPage){
        	idxPage = idxPage - 1;
            return pageSize*idxPage;
        },
        getLImitEndPageSize : function(pageSize,idxPage){
            pageSize = pageSize - 1;
            idxPage = idxPage - 1;
            return (idxPage*10) + pageSize;
        },
        changeValue : function(cjson){
            $(cjson.tpage).val(cjson.idx);
            $(cjson.curentSize).text(cjson.idx);
        },
        doLimitPage : function(page,size,callBack){
            var This = this;
            //console.log(page+"---"+size);
            $.ajax({
                url : This.URLAction,
                type : "POST",
                async:true,
                data : {
                    "start" : page,
                    "end" : size,
                    "userType" : This.mJson.userType
                },
                success : function(msg){
                    callBack&&callBack.call(This,msg,200);
                },
                error : function(er){
                    callBack&&callBack.call(This,er,500);
                }
            });
        }
    };
    window.SpringLimitPage = SpringLimitPage;
}(window);
