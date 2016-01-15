<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Job Portal</title>
		<script src="app/lib/angular/angular.js"></script>
		<script src="app/lib/angular/angular-route.js"></script>
		<script src="app/lib/angular/angular-resource.js"></script>
		<script type="text/javascript" src="app/js/app.js"></script>
		<script type="text/javascript" src="app/js/controllers.js"></script>
		<!-- <script type="text/javascript" src="app/js/services.js"></script> -->
		
		<script src="app/lib/jquery-1.11.2.js"></script>
		<script src="app/lib/bootstrap.min.js"></script>
		<script type="text/javascript">
		    $(document).ready(function(){
		        $('[data-toggle="floatLabel"]').attr('data-value', $(this).val()).on('keyup change', function() {
		            $(this).attr('data-value', $(this).val());
		        });
		    });
		</script>
		
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"></link>
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"></link>
        <link rel="stylesheet" href="app/css/login_style.css"></link>
	</head>
	<body ng-app="job-app">
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<img src="app/images/userlogo.png" class="logo"></img> <a
						class="navbar-brand" href="#">&nbsp;&nbsp;USER PORTAL</a>
				</div>
			</div>
		</nav>
		<ng-view></ng-view>
	</body>
</html>