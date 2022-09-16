<#macro renderStatement statement>
	<#switch statement.class.simpleName>
		<#case "CtBlockImpl">
			<@renderBlockStatement statement/>
			<#break>
		<#case "CtIfImpl">
			<@renderIfStatement statement/>
			<#break>
		<#case "CtReturnImpl">
			<@renderReturnStatement statement/>
			<#break>
		<#default>
			// XXX ${statement.class.simpleName} is unknown
			<#break>
	</#switch>
</#macro>

<#macro renderBlockStatement statement>
	<#list statement.statements as statement_>
		<@renderStatement statement=statement_/>
	</#list>
</#macro>

<#macro renderIfStatement statement>
	if (<@expression expression=statement.condition force_array=false/>)
	{
		<@renderStatement statement=statement.thenStatement/>
	}
	<#if statement.elseStatement??>
	else
	{
		<@renderStatement statement=statement.elseStatement/>
	}
	</#if>
</#macro>

<#macro renderReturnStatement statement>
	return <#if statement.returnedExpression??><@expression expression=statement.returnedExpression force_array=false/></#if>;
</#macro>
