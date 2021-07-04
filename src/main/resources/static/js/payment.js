/**
 * 
 */


function payment(mall_account) {
	let total_price = document.getElementById('total_price').value;
	let payment_number = document.getElementById('paymentway').value;
	let paymentMeans = document.getElementById(payment_number).value;
	let sendData = {
		"means": paymentMeans,
		"payment_number": payment_number,
		"total_price": total_price,
		"mall_account_number": mall_account,
		"messageType": "payment"
	};


	$.ajax({
		url: "http://localhost:8081",
		contentType: "application/json;charset=UTF-8",
		method: "post",
		data: JSON.stringify(sendData),
		success: function(data) {
			if (data.messageType == "success") {
				opener.window.location="http://localhost:8000";
				self.close();
			}
			else {
				alert(data.message);
				return false;
			}
		}, error: function(jqXHR, textStatus, errorThrown) {
			alert('은행 접속이 거부되었습니다.');
			return false;
		}
	});
}



function pay_ajax() {
	let pg_code = document.getElementById('pg_code').value;
	let mall_account = document.getElementById('mall_account').value;

	sendData = {
		"messageType": "connect",
		"pg_code": pg_code,
		"mall_account": mall_account
	};

	$.ajax({
		url: "http://localhost:8081",
		method: "post",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(sendData),
		success: function(data) {
			if (data.messageType == "success") {
				payment(mall_account);
			}
			else {
				alert(data.message);
			}
		},
		error: function() {
			alert('은행에 접속할 수 없습니다.');
		}
	})
}