<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>
<link href="css/404.css" rel="stylesheet">

<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/directive/head.jspf"%>
<body>
<!--start-wrap--->
<div class="wrap">
	<!--start-content------>
	<div class="content">
		<img src="imgs/error-img.png" title="error" />
		<p><span><label>O</label>hh.....</span>You Requested the page that is no longer There.</p>
		<a href="main.do">Back To Home</a>
	</div>
	<!--End-Cotent------>
</div>
<!--End-wrap--->
</body>
</html>