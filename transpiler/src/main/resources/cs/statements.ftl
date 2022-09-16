<#import "./expressions.ftl" as expressions/>
<#import "./comments.ftl" as comments/>

<#macro renderStatement statement>
	<@comments.comments comments=statement.comments />
    <#switch statement.class.simpleName>
        <#case "CtAssignmentImpl">
            <@renderAssignmentStatement statement/>
            <#break>
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
            // XXX renderStatement ${statement.class.simpleName} is unknown
            <#break>
    </#switch>
</#macro>

<#macro renderAssignmentStatement statement>
    <@expressions.renderExpression expression=statement.assigned/> = <@expressions.renderExpression expression=statement.assignment/>;
</#macro>

<#macro renderBlockStatement statement>
    <#list statement.statements as statement_>
        <@renderStatement statement=statement_/>
    </#list>
</#macro>

<#macro renderIfStatement statement>
    if (<@compress single_line=true><@expressions.renderExpression expression=statement.condition/></@compress>)
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
return <#if statement.returnedExpression??><@expressions.renderExpression expression=statement.returnedExpression/></#if>;
</#macro>
