<#-- @ftlvariable name="field" type="spoon.reflect.declaration.CtField" -->
<#macro fieldattributes field>
<#list field.annotations as annotation>
<#if annotation.name != "SuppressWarnings">
    #[${annotation.name?lower_case}<#if annotation.values?size gt 0>(<#list annotation.values as key, value>${key?cap_first} = ${value}<#sep>, </#list>)</#if>]
</#if>
</#list>
</#macro>