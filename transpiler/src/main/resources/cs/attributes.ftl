<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#macro classattributes class>
<#list class.annotations as annotation>
[${annotation.name}(<#list annotation.allValues as key, value>${key?cap_first} = ${value}<#sep>, </#list>)]
</#list>
</#macro>

<#-- @ftlvariable name="field" type="spoon.reflect.declaration.CtField" -->
<#macro fieldattributes field>
<#list field.annotations as annotation>
[${annotation.name}(<#list annotation.allValues as key, value>${key?cap_first} = ${value}<#sep>, </#list>)]
</#list>
</#macro>