$(document).ready(function() {
	//绑定按钮
	$(".login-btn").on("click",login);
	
	function login() {
		var form =$('.login-form').serializeObject();
		if (form.username == "") {
			$('.form-username').addClass('input-error');
			return false;
		}
		if (form.password == "") {
			$('.form-password').addClass('input-error');
			return false;
		}
		$.ajax({
			url : ctx+"/user/login",
			type : 'POST',
			data: form,
			success : function(result) {
				if (result.success) {
					window.location.href=ctx+'/success';
				} else {
					alert(result.errorMsg);
				}
			}
		});
	}
	
	$.backstretch("static/img/1.jpg");

    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
})