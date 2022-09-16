
<#macro comments comments>
<#list comments as comment>
<#switch comment.commentType>
	<#case "JAVADOC">
		/// ${comment.longDescription?replace('\n', '\n/// ')}
		<#break>
	<#case "INLINE">
		// ${comment.content}
		<#break>
	<#default>
        // XXX comments ${comment.commentType} is unknown
</#switch>
</#list>
</#macro>