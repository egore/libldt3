
<#macro comments comments>
<#list comments as comment>
<#if comment.commentType == "JAVADOC">
/// ${comment.longDescription?replace('\n', '\n/// ')}
<#else>
UNHANDLED COMMENT TYPE ${comment.commentType}
</#if>
</#list>
</#macro>