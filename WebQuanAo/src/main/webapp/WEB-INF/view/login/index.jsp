<!--
	Author: W3layouts
	Author URL: http://w3layouts.com
	License: Creative Commons Attribution 3.0 Unported
	License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

<title>Existing Login Form a Flat Responsive Widget Template :: W3layouts</title>

<!-- Meta-Tags -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="Existing Login Form Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //Meta-Tags -->

<link href="../../../css/popuo-box.css" rel="stylesheet" type="text/css" media="all" />

<!-- Style --> <link rel="stylesheet" href="../../../css/style.css" type="text/css" media="all">

<!-- Fonts -->
<link href="//fonts.googleapis.com/css?family=Quicksand:300,400,500,700" rel="stylesheet">
<!-- //Fonts -->

</head>
<!-- //Head -->

<!-- Body -->
<body>

	<h1>EXISTING LOGIN FORM</h1>

	<div class="w3layoutscontaineragileits">
	<h2>Login here</h2>

		<form action="/checkLogin" method="post">

			<input type="email" Name="email" placeholder="EMAIL" required="">
			<input type="password" Name="matKhau" placeholder="PASSWORD" required="">


			<ul class="agileinfotickwthree">
				<li>
					<input type="checkbox" id="brand1" value="">
					<label for="brand1"><span></span>Remember me</label>
					<a href="/forgot_password">Forgot password?</a>
				</li>
			</ul>
				<input type="submit" value="LOGIN">

			<br>
				<p> Đăng kí tài khoản ở đây <span>→</span> <a href="/dang-ki"> Click Here</a></p>
		</form>

<%--		<form id="loginForm" action="/checkLogin" method="post">--%>
<%--			<input type="email" name="email" id="email" placeholder="EMAIL" required>--%>
<%--			<input type="password" name="matKhau" id="password" placeholder="PASSWORD" required>--%>
<%--			<ul class="agileinfotickwthree">--%>
<%--				<li>--%>
<%--					<input type="checkbox" id="brand1" value="">--%>
<%--					<label for="brand1"><span></span>Remember me</label>--%>
<%--					<a href="/forgot_password">Forgot password?</a>--%>
<%--				</li>--%>
<%--			</ul>--%>
<%--			<input type="submit" value="LOGIN">--%>
<%--			<br>--%>
<%--			<p>Đăng kí tài khoản ở đây <span>→</span> <a href="/dang-ki">Click Here</a></p>--%>
<%--		</form>--%>







	</div>
	
	<!-- for register popup -->
	<div id="small-dialog1" class="mfp-hide">
		<div class="contact-form1">
			<div class="contact-w3-agileits">


			</div>
		</div>	
	</div>
	<!-- //for register popup -->
	
	<div class="w3footeragile">
		<p> &copy; 2017 Existing Login Form. All Rights Reserved | Design by <a href="http://w3layouts.com" target="_blank">W3layouts</a></p>
	</div>

	<script>
		// Function to validate the form
		function validateForm(event) {
			const email = document.getElementById('email').value;
			const password = document.getElementById('password').value;

			// You can add your validation logic here
			if (email === "" || password === "") {
				alert("Email and password are required fields.");
				event.preventDefault(); // Prevent form submission
			}
		}

		// Add an event listener to the form
		const loginForm = document.getElementById('loginForm');
		loginForm.addEventListener('submit', validateForm);
	</script>
	<script type="text/javascript" src="../../../js/jquery-2.1.4.min.js"></script>

	<!-- pop-up-box-js-file -->  
		<script src="../../../js/jquery.magnific-popup.js" type="text/javascript"></script>
	<!--//pop-up-box-js-file -->
	<script>
		$(document).ready(function() {
		$('.w3_play_icon,.w3_play_icon1,.w3_play_icon2').magnificPopup({
			type: 'inline',
			fixedContentPos: false,
			fixedBgPos: true,
			overflowY: 'auto',
			closeBtnInside: true,
			preloader: false,
			midClick: true,
			removalDelay: 300,
			mainClass: 'my-mfp-zoom-in'
		});
																		
		});
	</script>

</body>
<!-- //Body -->

</html>