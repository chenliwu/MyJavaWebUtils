(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-approveDetails-approveDetails"],{"03cd":function(t,e,a){e=t.exports=a("2350")(!1),e.push([t.i,'div[data-v-51e10e47],uni-scroll-view[data-v-51e10e47],uni-swiper[data-v-51e10e47]{-webkit-box-sizing:border-box;box-sizing:border-box}.box[data-v-51e10e47]{display:-webkit-box;display:-ms-flexbox;display:flex;display:-webkit-flex}.height100vh[data-v-51e10e47]{height:100vh}.text-orange[data-v-51e10e47]{color:#f37b1d}.text-selected[data-v-51e10e47]{color:#6a91f8}.text-black[data-v-51e10e47]{color:#333}.bg-white[data-v-51e10e47]{background-color:#fff}.padding[data-v-51e10e47]{padding:%?30?%}.margin[data-v-51e10e47]{margin:%?30?%}.margin-top[data-v-51e10e47]{margin-top:%?30?%}.text-center[data-v-51e10e47]{text-align:center}.label-text[data-v-51e10e47]{font-size:16px;color:"#808080"}',""])},"1aa5":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i={name:"wuc-tab",data:function(){return{}},props:{tabList:{type:Array,default:function(){return[]}},tabCur:{type:Number,default:function(){return 0}},tabClass:{type:String,default:function(){return""}},tabStyle:{type:String,default:function(){return""}},textFlex:{type:Boolean,default:function(){return!1}},selectClass:{type:String,default:function(){return"text-blue"}}},methods:{tabSelect:function(t,e){if(this.currentTab===t)return!1;this.$emit("update:tabCur",t),this.$emit("change",t)}},computed:{scrollLeft:function(){return 60*(this.tabCur-1)}}};e.default=i},"1f69":function(t,e,a){"use strict";a.r(e);var i=a("1aa5"),n=a.n(i);for(var c in i)"default"!==c&&function(t){a.d(e,t,function(){return i[t]})}(c);e["default"]=n.a},"251e":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-uni-view",{staticClass:"uni-flex uni-column",staticStyle:{height:"100vh"}},[a("wuc-tab",{staticStyle:{"background-color":"red"},attrs:{"tab-list":t.tabList,textFlex:"",tabCur:t.TabCur,"tab-class":"text-center text-black ","select-class":"text-selected"},on:{"update:tabCur":function(e){e=t.$handleEvent(e),t.TabCur=e},"update:tab-cur":function(e){e=t.$handleEvent(e),t.TabCur=e}}}),a("v-uni-swiper",{staticClass:"swiper uni-flex uni-column",staticStyle:{height:"100vh"},attrs:{current:t.TabCur,duration:"300",circular:!1,"indicator-color":"rgba(255,255,255,0)","indicator-active-color":"#6a91f8"},on:{change:function(e){e=t.$handleEvent(e),t.swiperChange(e)}}},[a("v-uni-swiper-item",[a("v-uni-scroll-view",{staticStyle:{height:"100vh","background-color":"#0A98D5"},attrs:{"scroll-top":0,"scroll-y":"true"}},[a("v-uni-view",{staticClass:"padding text-black uni-flex uni-column",staticStyle:{flex:"1",height:"100vh"}},[a("v-uni-view",{staticStyle:{display:"flex","flex-direction":"row","background-color":"red"}},[a("v-uni-image",{staticStyle:{width:"20px",height:"20px"},attrs:{src:"../../static/icon/icon_unread@2x.png"}}),a("v-uni-text",[t._v("流程分类")])],1),a("v-uni-view",{staticStyle:{display:"flex","flex-direction":"column",flex:"1","background-color":"#576B95"}},[a("v-uni-text",{staticClass:"label-text"},[t._v("业务单号：XXXX")]),a("v-uni-text",{staticClass:"label-text"},[t._v("申请单位：XXXX")]),a("v-uni-text",{staticClass:"label-text"},[t._v("申请人：XXXX")]),a("v-uni-text",{staticClass:"label-text"},[t._v("申请时间：XXXX")])],1),0==t.TabCur?a("v-uni-web-view",{staticStyle:{"margin-top":"60vh",height:"70%"},attrs:{"webview-styles":t.webviewStyles,src:t.approveDetailUrl}}):t._e()],1)],1)],1),a("v-uni-swiper-item",[a("v-uni-scroll-view",{staticStyle:{height:"100vh","background-color":"#1AAD19"},attrs:{"scroll-top":0,"scroll-y":"true"}},[a("v-uni-view",{staticClass:"bg-white padding text-center text-black"},[t._v("流程跟踪页面")])],1)],1)],1)],1)},n=[];a.d(e,"a",function(){return i}),a.d(e,"b",function(){return n})},"2fc5":function(t,e,a){"use strict";var i=a("80c0"),n=a.n(i);n.a},"38da":function(t,e,a){"use strict";a.r(e);var i=a("78dc"),n=a("1f69");for(var c in n)"default"!==c&&function(t){a.d(e,t,function(){return n[t]})}(c);a("2fc5");var r=a("2877"),l=Object(r["a"])(n["default"],i["a"],i["b"],!1,null,"35c4ebfc",null);e["default"]=l.exports},"78dc":function(t,e,a){"use strict";var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-uni-scroll-view",{staticClass:"wuc-tab",class:t.tabClass,style:t.tabStyle,attrs:{"scroll-with-animation":"","scroll-x":"","scroll-left":t.scrollLeft}},[t.textFlex?t._e():a("div",t._l(t.tabList,function(e,i){return a("div",{key:i,staticClass:"wuc-tab-item",class:[i===t.tabCur?t.selectClass+" cur":""],attrs:{id:i},on:{click:function(e){e=t.$handleEvent(e),t.tabSelect(i,e)}}},[a("v-uni-text",{class:e.icon}),a("span",[t._v(t._s(e.name))])],1)}),0),t.textFlex?a("div",{staticClass:"flex text-center"},t._l(t.tabList,function(e,i){return a("div",{key:i,staticClass:"wuc-tab-item flex-sub",class:i===t.tabCur?t.selectClass+" cur":"",attrs:{id:i},on:{click:function(e){e=t.$handleEvent(e),t.tabSelect(i,e)}}},[a("v-uni-text",{class:e.icon}),a("span",[t._v(t._s(e.name))])],1)}),0):t._e()])},n=[];a.d(e,"a",function(){return i}),a.d(e,"b",function(){return n})},"80c0":function(t,e,a){var i=a("8e8c");"string"===typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);var n=a("4f06").default;n("7d527797",i,!0,{sourceMap:!1,shadowMode:!1})},"8e8c":function(t,e,a){e=t.exports=a("2350")(!1),e.push([t.i,"div[data-v-35c4ebfc],uni-scroll-view[data-v-35c4ebfc],uni-swiper[data-v-35c4ebfc]{-webkit-box-sizing:border-box;box-sizing:border-box}.wuc-tab[data-v-35c4ebfc]{white-space:nowrap}.wuc-tab-item[data-v-35c4ebfc]{height:%?90?%;display:inline-block;line-height:%?90?%;margin:0 %?10?%;padding:0 %?20?%}.wuc-tab-item.cur[data-v-35c4ebfc]{border-bottom:%?4?% solid}.wuc-tab.fixed[data-v-35c4ebfc]{position:fixed;width:100%;top:0;z-index:1024;-webkit-box-shadow:0 %?1?% %?6?% rgba(0,0,0,.1);box-shadow:0 %?1?% %?6?% rgba(0,0,0,.1)}.flex[data-v-35c4ebfc]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex}.text-center[data-v-35c4ebfc]{text-align:center}.flex-sub[data-v-35c4ebfc]{-webkit-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1}.text-blue[data-v-35c4ebfc]{color:#0081ff}.text-white[data-v-35c4ebfc]{color:#fff}.bg-white[data-v-35c4ebfc]{background-color:#fff}.bg-blue[data-v-35c4ebfc]{background-color:#0081ff}.text-orange[data-v-35c4ebfc]{color:#f37b1d}.text-xl[data-v-35c4ebfc]{font-size:%?36?%}",""])},"8fa5":function(t,e,a){var i=a("03cd");"string"===typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);var n=a("4f06").default;n("3e063e6c",i,!0,{sourceMap:!1,shadowMode:!1})},aafc:function(t,e,a){"use strict";a.r(e);var i=a("251e"),n=a("dbcd");for(var c in n)"default"!==c&&function(t){a.d(e,t,function(){return n[t]})}(c);a("d6ec");var r=a("2877"),l=Object(r["a"])(n["default"],i["a"],i["b"],!1,null,"51e10e47",null);e["default"]=l.exports},d6ec:function(t,e,a){"use strict";var i=a("8fa5"),n=a.n(i);n.a},dbcd:function(t,e,a){"use strict";a.r(e);var i=a("ee55"),n=a.n(i);for(var c in i)"default"!==c&&function(t){a.d(e,t,function(){return i[t]})}(c);e["default"]=n.a},ee55:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0;var i=n(a("38da"));function n(t){return t&&t.__esModule?t:{default:t}}var c={data:function(){return{TabCur:0,webViewHeight:"30vh",tabList:[{name:"业务详情"},{name:"流程跟踪"}],approveDetailUrl:"http://192.168.0.193:8080/t2/api/flow/detailPage?access_token=eac77a7ee4a651e819cd47d4104f2734&id=250e4675-8e49-11e9-9fa3-8c89a59654fd&type=0&categoryId=netbankPay&businessId=18bb35003de14a7790ceb1eb36fdf0b3&_btv=1560936726314",webviewStyles:{progress:{color:"#FF3333",top:100}}}},components:{WucTab:i.default},methods:{tabChange:function(t){this.TabCur=t},swiperChange:function(t){var e=t.target.current;this.TabCur=e}}};e.default=c}}]);