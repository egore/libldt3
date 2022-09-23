
<#macro comments comments >
<#list comments as comment_>
<@comment comment=comment_ />
</#list>
</#macro>

<#macro comment comment >
<#switch comment.commentType>
    <#case "JAVADOC">
        <@fieldcomment comment/>
        <#break>
    <#case "BLOCK">
        <@blockcomment comment/>
        <#break>
    <#case "INLINE">
        // ${comment.content}
        <#break>
    <#default>
        // XXX comments ${comment.commentType} is unknown
</#switch>
</#macro>

<#macro fieldcomment comment>
/// ${comment.longDescription?replace('\n', '\n/// ')}
</#macro>

<#macro blockcomment comment>
<#if comment.content?contains('\n')>
/*
 * ${comment.content?replace('\n', '\n/// *')}
 */
<#else>
/* ${comment.content} */
</#if>
</#macro>