<#-- @ftlvariable name="interface" type="spoon.reflect.declaration.CtInterface" -->
<#import "./attributes.ftl" as attributes/>
<#import "./comments.ftl" as comments/>
<#import "./method.ftl" as method_/>
<#include "header.ftl"/>

<@genusing class=interface/>

<@namespace package=interface.package>

<@interface_ interface=interface/>

</@namespace>
<#macro interface_ interface>
<@comments.comments comments=interface.comments with_summary=true />
<@attributes.classattributes interface/>
public interface ${interface.simpleName}<#if interface.superInterfaces?size gt 0> : <#list interface.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if>
{
    <#list interface.nestedTypes as nestedType>
    <@interface_ interface=nestedType/>
    </#list>

    <#list interface.fields as field>
    <@attributes.fieldattributes field/>
    ${field.visibility!} <@converttype type=field.type/> ${field.simpleName?cap_first};
    </#list>

    <#list interface.methods as method>
    <@method_.signature method=method/>;
    </#list>
}
</#macro>