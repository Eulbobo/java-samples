<html>
	<head>
		<script type="text/javascript">
		<!--
			function doLink(target){
				open('<%=request.getContextPath() %>'+target, 'Popup', 'scrollbars=1,resizable=1,height=10,width=10'); 
				return false;
			}
		-->
		</script>
	</head>
	<body>
		<h2>Hello World!</h2><br/>
		<h3>Links : </h3><br/>
		<a href="javascript:doLink('/hello')" >Hello</a><br />
		<a href="javascript:doLink('/stillAlive')" >still alive</a><br />
		<a href="javascript:doLink('/goodBye')" >goodBye</a><br />
	</body>
</html>
