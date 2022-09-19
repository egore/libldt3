
<#macro comments comments with_summary=false>
<#list comments as comment_>
<@comment comment=comment_ with_summary=with_summary/>
</#list>
</#macro>

<#macro comment comment with_summary=false>
<#switch comment.commentType>
    <#case "JAVADOC">
        <#if with_summary>
        <@classcomment comment/>
        <#else>
        <@fieldcomment comment/>
        </#if>
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

<#macro classcomment comment>
    /// <summary>
    /// ${comment.shortDescription?replace('\n', '\n/// ')}
    /// </summary>
    <#if comment.longDescription != comment.shortDescription>
    /// ${comment.longDescription?replace('\n', '\n/// ')}
    </#if>
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