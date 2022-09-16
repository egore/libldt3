<#import "statements.ftl" as statements/>

<#macro signature method>
<@converttype type=method.type/> ${method.simpleName}(<#list method.parameters as parameter><@converttype type=parameter.type/> ${parameter.simpleName}<#sep>, </#list>)
</#macro>

<#macro body method>
{
	<@statements.renderBlockStatement statement=method.body/>
}
</#macro>