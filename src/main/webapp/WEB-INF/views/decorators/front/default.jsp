<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/_taglib.jsf" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><sitemesh:write property="title" /></title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
        <!-- Bootstrap Core CSS -->
    	<link href="/assets/plugin/startbootstrap-clean-blog-1.0.2/css/bootstrap.min.css" rel="stylesheet">

	    <!-- Custom CSS -->
	    <link href="/assets/plugin/startbootstrap-clean-blog-1.0.2/css/clean-blog.min.css" rel="stylesheet">

	    <!-- Custom Fonts -->
	    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	    <link href='http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
	    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
        
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        
        <sitemesh:write property="head" />
    </head>
    <body>
    	<jsp:include page="/WEB-INF/views/decorators/front/top.jsp" />
		
		<div class="container">
			<sitemesh:write property="body" />
		</div>
		<jsp:include page="/WEB-INF/views/decorators/front/bottom.jsp" />	
		
		
		<!-- script -->
		<script src="/assets/plugin/jquery/jquery-1.12.0.min.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	    
		<script src="/assets/plugin/jquery_validation/jquery.validate.min.js"></script>
    	<script src="/assets/plugin/jquery_validation/additional-methods.min.js"></script>
	    <script src="/assets/plugin/jquery/jquery.form.min.js"></script>
	    <script src="/assets/plugin/jquery/jshashtable.js"></script>
	    <script src="/assets/plugin/jquery/jquery.numberformatter-1.2.4.min.js"></script>
	    <script src="/assets/plugin/jquery/jquery.bpopup.min.js"></script>
	    
	    <!-- jplayer cdns 
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/add-on/jplayer.playlist.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/add-on/jquery.jplayer.inspector.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/jplayer/jquery.jplayer.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jplayer/2.9.2/popcorn/popcorn.jplayer.min.js"></script> -->
		
		<script src="/assets/front/js/board.js"></script>
		
		
    </body>
</html>