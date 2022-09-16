<#-- @ftlvariable name="interface" type="spoon.reflect.declaration.CtInterface" -->
<#import "members.ftl" as members/>
<#import "attributes.ftl" as attributes/>
<#import "comments.ftl" as comments/>
<#include "header.ftl"/>

<@genusing class=interface/>

<@namespace package=interface.package>

<@interface_ klass=interface/>

</@namespace>

<#macro interface_ klass>
<@comments.comments comments=klass.comments />
<@attributes.classattributes klass/>
public interface ${klass.simpleName}<#if klass.superInterfaces?size gt 0> : <#list klass.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if>
{
    <#list klass.nestedTypes as nestedType>
    <@interface_ klass=nestedType/>
    </#list>
    <@members.interfacemembers klass/>
}
</#macro>