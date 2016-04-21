<#macro masterTemplate title="Welcome">
    <!DOCTYPE html
            PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
            "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title>${title} | Inventory</title>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <script>
        function myFunction(a, b) {
            return a * b;
        }

        function showMatchingFiles (str) {
            var matches = document.getElementsByClassName("fileresult");

            for(var i = 0; i < matches.length; i++)
            {
                if(matches[i].id.indexOf(str) == 0)
                {
                    matches[i].className = "fileresult";
                } else {
                    matches[i].className = "hidden fileresult";
                }

            }

        }

        function showInputMatchingFiles () {
            var matches = document.getElementsByClassName("fileresult");
            var str = document.getElementById("autocomplete_input").value;

            for(var i = 0; i < matches.length; i++)
            {
                if(matches[i].id.indexOf(str) == 0)
                {
                    matches[i].className = "fileresult";
                } else {
                    matches[i].className = "hidden fileresult";
                }

            }

        }

        function traverseToDirectory (subdir) {
            document.getElementById("target_subdir").value = subdir;

            document.getElementById("gotodirform").submit();
        }

        </script>
    </head>
    <body>
		<div class="page">
	  		<h1>${title}</h1>
		  	<div class="navigation">
		  	</div>
		  	<div class="body">
		  		<#nested />
		  	</div>
		  	<div class="footer">

		  	</div>
		</div>



        <p id="demo"></p>
    </body>
    </html>
</#macro>