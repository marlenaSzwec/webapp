<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="author" content="Maciej Malik">
	<title>Web App</title>
	<style>
		body {
			width: 70vw;
			margin: 10vh auto;
			text-align: center;
		}
		
		#error {
			color: red;
			margin: 1vh auto
		}
		
		#buttonsContainer {
			width: 30vw;
			margin: 10vh auto;
		}
		
		.button {
			background-color: #555;
			border: none;
			color: white;
			padding: 15px 32px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 2em;
			margin: 4px 2px;
			cursor: pointer;
		}
		
		.button:hover {
			background-color: #444;
		}
		
		form {
			width: 40vw;
			margin: 1vh auto;
			font-size: 1.2em;
		}
		
		a:hover {
			color: blue;
		}
	</style>
</head>
<body>