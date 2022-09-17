
<#macro comments comments>
<#list comments as comment>
<#switch comment.commentType>
    <#case "JAVADOC">
        /// ${comment.longDescription?replace('\n', '\n/// ')}
        <#break>
    <#case "BLOCK">
        <#if comment.content?contains('\n')>
        /*
         * ${comment.content?replace('\n', '\n/// *')}
         */
        <#else>
        /* ${comment.content} */
        </#if>
        <#break>
    <#case "INLINE">
        // ${comment.content}
        <#break>
    <#default>
        // XXX comments ${comment.commentType} is unknown
</#switch>
</#list>
</#macro>