//获取查询的参数  
function GetQueryString(name) {  
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
	var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
	var context = "";  
	if (r != null)  
		 context = r[2];  
	reg = null;  
	r = null;  
	return context == null || context == "" || context == "undefined" ? "" : context;  
  }  
  function goback() {
	  window.history.go(-1); 
  }
  //js模拟form请求
  function postcall( url, params, target){  
		var tempform = document.createElement("form");  
		tempform.action = url;  
		tempform.method = "post";  
		tempform.style.display="none";
		tempform.target="_blank";
		if(target) {  
			tempform.target = target;  
		}  
	  
		for (var x in params) {  
			var opt = document.createElement("input");  
			opt.name = x;  
			opt.value = params[x];  
			tempform.appendChild(opt);  
		}  
	  
		var opt = document.createElement("input");  
		opt.type = "submit";  
		tempform.appendChild(opt);  
		document.body.appendChild(tempform);  
		tempform.submit();  
		document.body.removeChild(tempform);  
	}  

  //序列化表单
	$.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};