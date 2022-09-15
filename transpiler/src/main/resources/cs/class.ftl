<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#import "members.ftl" as members/>
<#import "attributes.ftl" as attributes/>
<#import "comments.ftl" as comments/>
<#include "header.ftl"/>

<@genusing class=class/>

<@namespace package=class.package>

<@class_ klass=class/>

</@namespace>

<#macro class_ klass>
<@comments.comments comments=klass.comments />
<@attributes.classattributes klass/>
public class ${klass.simpleName}<#if klass.superInterfaces?size gt 0> : <#list klass.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if>
{
    <#list klass.nestedTypes as nestedType>
    <@class_ klass=nestedType/>
    </#list>
    <@members.classmembers klass/>
}
</#macro>