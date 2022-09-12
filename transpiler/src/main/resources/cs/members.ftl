
<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#macro classmembers class>
	<#list class.fields as field>
	${field.visibility} ${field.simpleName};
	</#list>
</#macro>