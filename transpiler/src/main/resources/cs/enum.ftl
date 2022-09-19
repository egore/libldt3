<#-- @ftlvariable name="enum" type="spoon.reflect.declaration.CtEnum" -->
<#import "./comments.ftl" as comments/>
<#include "header.ftl"/>

<@namespace package=enum.package>

<@comments.comments comments=enum.comments with_summary=true />
public enum ${enum.simpleName}
{
    <#list enum.enumValues as value>
    <@comments.comments comments=value.comments />
    ${value.simpleName}<#sep>,
    </#list>
}

public static class ${enum.simpleName}Extensions
{
    <#list enum.fields as field>
    <#if field.type.simpleName != enum.simpleName>
    public static <@converttype type=field.type/> Get${field.simpleName?cap_first}(this ${enum.simpleName} self)
    {
        switch (self)
        {
            <#list enum.enumValues as value>
            case ${enum.simpleName}.${value.simpleName}: return ${value.defaultExpression.arguments[0]};
            </#list>
            default: throw new Exception();
        }
    }
    </#if>
    </#list>
}
</@namespace>
