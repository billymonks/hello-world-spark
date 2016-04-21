<#import "masterTemplate.ftl" as layout />

<@layout.masterTemplate title="Inventory">
    <h2>${pageTitle}</h2>
    <div class="twitbox">
        <h3>List your car:</h3>
        <form action="/submit" method="post">


            <dl>
              <dt>Make:
              <dd><input type="text" name="make" maxlength="50" size="30" value="${make!}">
              <dt>Model:
              <dd><input type="text" name="model" maxlength="50" size="30" value="${model!}">
              <dt>Price:
              <dd><input type="text" name="price" maxlength="20" size="30" value=${price!}>
            </dl>
            <div class="actions"><input type="submit" value="Submit"></div>
        </form>
    </div>
    <ul class="messages">
    <#if cars??>
    <#list cars as car>
		<li><p>
		<strong>${car.make}</strong>
		${car.model}
		<small>$ ${car.price}</small>
	<#else>
		<li><em>No cars.</em>
	</#list>
	<#else>
		<li><em>No cars.</em>
	</#if>
	</ul>
<button type="button" style=""
        onclick="showMatchingFiles('2')">hide all but 2</button>
</@layout.masterTemplate>