(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-testWebView-testWebView"],{"7f92":function(e,t,n){"use strict";n.r(t);var a=n("8608"),i=n("d3d3");for(var s in i)"default"!==s&&function(e){n.d(t,e,function(){return i[e]})}(s);var r=n("2877"),o=Object(r["a"])(i["default"],a["a"],a["b"],!1,null,"021e969c",null);t["default"]=o.exports},8608:function(e,t,n){"use strict";var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("v-uni-view",[n("v-uni-web-view",{attrs:{"webview-styles":e.webviewStyles,src:e.approveDetailUrl},on:{message:function(t){t=e.$handleEvent(t),e.handleMessage(t)}}})],1)},i=[];n.d(t,"a",function(){return a}),n.d(t,"b",function(){return i})},d3d3:function(e,t,n){"use strict";n.r(t);var a=n("ec10"),i=n.n(a);for(var s in a)"default"!==s&&function(e){n.d(t,e,function(){return a[e]})}(s);t["default"]=i.a},ec10:function(e,t,n){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var a={onReady:function(){},data:function(){return{webviewUrl:"http://192.168.0.178:8088/webUtils/testUniAppWebView",approveDetailUrl:"http://192.168.0.178:8080/t2/api/flow/detailPage?access_token=eac77a7ee4a651e819cd47d4104f2734&id=250e4675-8e49-11e9-9fa3-8c89a59654fd&type=0&categoryId=netbankPay&businessId=18bb35003de14a7790ceb1eb36fdf0b3&_btv=1560936726314",webviewStyles:{progress:{color:"#FF3333",top:100}}}},methods:{handleMessage:function(e){console.log("接收到的消息："+JSON.stringify(e.detail.data)),uni.showToast({title:"接收到的消息："+JSON.stringify(e.detail.data),icon:"success",mask:!0})}}};t.default=a}}]);