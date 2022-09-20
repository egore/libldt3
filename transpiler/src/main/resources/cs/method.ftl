<#import "./statements.ftl" as statements/>
<#import "./expressions.ftl" as expressions/>

<#macro constructor_signature constructor>
<#assign supers = (constructor.body.statements)?filter(x -> x.class.simpleName == "CtInvocationImpl" && x.executable.simpleName == "<init>" && x.type.simpleName != "Object")>
${constructor.visibility!} ${constructor.parent.simpleName}(<#list constructor.parameters as parameter><@converttype type=parameter.type/> ${parameter.simpleName}<#sep>, </#list>)<#if supers?size == 1> : base(<#list supers[0].arguments as argument><@expressions.renderExpression expression=argument/><#sep>, </#list>)</#if>
</#macro>

<#macro constructor_body constructor>
{
    <#list constructor.body.statements?filter(x -> x.class.simpleName != "CtInvocationImpl" || x.executable.simpleName != "<init>") as statement_>
        <@statements.renderStatement statement=statement_/>
    </#list>
}
</#macro>

<#macro signature method>
${method.visibility!}<#if method.static> static</#if> <@converttype type=method.type/> ${method.simpleName?cap_first}(<#list method.parameters as parameter><@converttype type=parameter.type/> ${parameter.simpleName}<#sep>, </#list>)</#macro>

<#macro body method>
{
    <@statements.renderBlockStatement statement=method.body/>
}
</#macro>