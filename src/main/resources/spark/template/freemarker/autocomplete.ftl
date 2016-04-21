<#import "masterTemplate.ftl" as layout />

<script>

</script>

<@layout.masterTemplate title="Autocomplete">
    <h2>${pageTitle}</h2>
    <div class="twitbox">
        <h3>Search Files:</h3>


        <dl>
          <dt>Search files:
          <dd><input type="text" id="autocomplete_input" name="make" maxlength="50" size="30" onkeyup="showInputMatchingFiles()">

        </dl>
        <form id="gotodirform" action="/gotodir" method="post">
            <input style="display:none" id="target_subdir" type="text" name="subdir" maxlength="50" size="30" value="${subdir!}">
        </form>
        <form id="gotoroot" action="/gotoroot" method="post">
            <input type="submit" value="Go Up">
        </form>
        <p>${fullDir}</p>
    </div>

    <ul class="files">


    <#list directories as directory>
        <li id="${directory}" class="fileresult">
        <a href="#" onclick="traverseToDirectory('${directory}')"><strong>${directory}</strong></a>
        </li>
    </#list>

    <#list files as filestring>
		<li id="${filestring}" class="fileresult">
		<strong>${filestring}</strong>
		</li>
	</#list>



	</ul>

</@layout.masterTemplate>