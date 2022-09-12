<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->

<#import "members.ftl" as members/>
<#import "attributes.ftl" as attributes/>

<@namespace package=class.package>

<@attributes.classattributes class/>
public class ${class.simpleName}<#if class.superInterfaces?size gt 0> : <#list class.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if>
{
	<@members.classmembers class/>
}

</@namespace>