<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#import "./attributes.ftl" as attributes/>
<#import "./comments.ftl" as comments/>
<#import "./expressions.ftl" as expressions/>
<#import "./method.ftl" as method_/>
<#include "header.ftl"/>

<@genusing class=class/>

<@namespace package=class.package>
<@class_ klass=class/>
</@namespace>
<#macro class_ klass>
<@comments.comments comments=klass.comments with_summary=true />
<@attributes.classattributes klass/>
<@compress single_line=true>${klass.visibility!} ${klass.abstract?string("abstract", "")} class <@convertclass class=klass/><#if klass.superInterfaces?size gt 0> : <#list klass.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if><#if klass.superclass??><#if klass.superInterfaces?size gt 0>, <#else> : </#if>${klass.superclass.simpleName}</#if></@compress>
{
    <#list klass.nestedTypes as nestedType>
    <@class_ klass=nestedType/>
    </#list>
    <#if klass.fields?size gt 0>
    <#list klass.fields as field>
    <#if field.simpleName != "LOG">
        <@attributes.fieldattributes field/>
        ${field.visibility!}<#if field.static> static</#if><#if field.final> readonly</#if> <@converttype type=field.type/> ${field.simpleName?cap_first}<#if field.assignment??> = <@expressions.renderExpression expression=field.assignment/></#if>;
    </#if>
    </#list>

    </#if>
    <#list class.constructors as constructor>
        <#if ! constructor.implicit>
            <@method_.constructor_signature constructor=constructor/>
            <@method_.constructor_body constructor=constructor/>
        </#if>
    </#list>
    <#list class.methods as method>
    <@comments.comments comments=method.comments />
    <@method_.signature method=method/>
    <@method_.body method=method/>
    </#list>
}
</#macro>