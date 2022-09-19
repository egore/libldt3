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
        <#case "CtCommentImpl">
            <@comments.comment statement/>
            <#break>
        <#case "CtForEachImpl">
            <@renderForEachStatement statement/>
            <#break>
        <#case "CtIfImpl">
            <@renderIfStatement statement/>
            <#break>
        <#case "CtInvocationImpl">
            <@renderInvocationStatement statement/>
            <#break>
        <#case "CtLocalVariableImpl">
            <@renderLocalVariableStatement statement/>
            <#break>
        <#case "CtReturnImpl">
            <@renderReturnStatement statement/>
            <#break>
        <#case "CtSwitchImpl">
            <@renderSwitchStatement statement/>
            <#break>
        <#case "CtThrowImpl">
            <@renderThrowStatement statement/>
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

<#macro renderForEachStatement statement>
    foreach (<@converttype type=statement.variable.type/> ${statement.variable.simpleName} in <@expressions.renderExpression expression=statement.expression/>)
    {
        <@renderBlockStatement statement=statement.body/>
    }
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

<#macro renderInvocationStatement statement>
<@expressions.renderInvocationExpression statement/>;
</#macro>

<#macro renderLocalVariableStatement statement>
    <@converttype type=statement.type/> ${statement.simpleName}<#if statement.defaultExpression??> = <@expressions.renderExpression expression=statement.defaultExpression/></#if>;
</#macro>

<#macro renderReturnStatement statement>
return <#if statement.returnedExpression??><@expressions.renderExpression expression=statement.returnedExpression/></#if>;
</#macro>

<#macro renderSwitchStatement statement>
switch (<@expressions.renderExpression expression=statement.selector/>) {
<#list statement.cases as case>
	<#if case.caseExpression??>
    case <@expressions.renderExpression expression=case.caseExpression/>:
   	<#else>
   	default:
   	</#if>
        <#list case.statements as caseStatement>
            <@renderStatement statement=caseStatement/>
        </#list>
</#list>
}
</#macro>

<#macro renderThrowStatement statement>
    throw <@expressions.renderExpression expression=statement.thrownExpression/>;
</#macro>
