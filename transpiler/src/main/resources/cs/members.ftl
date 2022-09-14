<#import "attributes.ftl" as attributes/>

<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#macro classmembers class>
    <#list class.fields as field>
    <@attributes.fieldattributes field/>
    ${field.visibility} <@converttype type=field.type/> ${field.simpleName};
    </#list>
</#macro>