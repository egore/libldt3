<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->

<#import "members.ftl" as members/>

<@namespace package=class.package>

<#list class.annotations as annotation>
[${annotation.name} <#list annotation.allValues as key, value>${key}=${value}</#list>]
</#list>
public class ${class.simpleName} {
	<@members.classmembers class/>
}

</@namespace>