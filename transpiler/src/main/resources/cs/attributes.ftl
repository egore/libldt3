<#import "./expressions.ftl" as expressions/>

<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#macro classattributes class>
<#list class.annotations as annotation>
<#if annotation.name != "SuppressWarnings">
[${annotation.name}<#if annotation.values?size gt 0>(<#list annotation.values as key, value>${key?cap_first} = <@expressions.renderExpression expression=value force_array=(key == "kontextregeln")/><#sep>, </#list>)</#if>]
</#if>
</#list>
</#macro>

<#-- @ftlvariable name="field" type="spoon.reflect.declaration.CtField" -->
<#macro fieldattributes field>
<#list field.annotations as annotation>
<#if annotation.name != "SuppressWarnings">
    [${annotation.name}<#if annotation.values?size gt 0>(<#list annotation.values as key, value>${key?cap_first} = <@expressions.renderExpression expression=value force_array=(annotation.name == "Regelsatz" && key == "value")/><#sep>, </#list>)</#if>]
</#if>
</#list>
</#macro>