<%@ page language="java" session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

    <title>basic curd admin : 404</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/"%>resource/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/"%>resource/css/bootstrap-theme.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() + "/"%>resource/css/layout.min.css" />
    <!--[if IE 8]>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <script type="text/javascript" src="resource/script/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="main-container container">
	
		<div class="panel panel-default">
							
			<div class="panel-heading">
				<h3 class="panel-title"><span class="glyphicon glyphicon-trash"></span> 404</h3>
			</div>
			
			<div class="panel-body">
		        <div class="server-exception"> 
		    		<p><center><h1><span class="glyphicon glyphicon-trash"></span></h1></p></center>
		    		<h3>找不到页面</h3>
		    		<hr>
			   		<p>你访问的页面不存在或已经被删除.</p> 
			   		<p>
			   			<a class="btn btn-default" href="javascript:history.back()">
							<span class="glyphicon glyphicon-backward"></span> 返回
						</a>
			   		</p>
		    	</div>
    		</div>
    		
    	</div>
    	
	</div>
</body>
</html>