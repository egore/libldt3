<#-- @ftlvariable name="enum" type="spoon.reflect.declaration.CtEnum" -->
<#import "./members.ftl" as members/>
<#import "./comments.ftl" as comments/>
<#include "header.ftl"/>

<@namespace package=enum.package>

<@comments.comments comments=enum.comments />
public enum ${enum.simpleName}
{
    <#list enum.enumValues as value>
    <@comments.comments comments=value.comments />
    ${value.simpleName}
    <#sep>,
    </#list>
}

public static class ${enum.simpleName}Extensions
{
    <@members.enummembers enum/>
}

</@namespace>
