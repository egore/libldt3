<#import "./comments.ftl" as comments/>
#![allow(dead_code)]

<@genuse struct=struct/>

<#list struct.nestedTypes as nestedType>
pub struct ${struct.simpleName}_${nestedType.simpleName} {
<#list nestedType.fields as field>
    <@snakecase name=field.simpleName/>: <@converttype type=field.type/><#sep>,
</#list>

}

</#list>
<@comments.comments comments=struct.comments />
pub struct ${struct.simpleName} {
<#list struct.fields as field>
<#if field.simpleName != "LOG">
    <@snakecase name=field.simpleName/>: <@converttype type=field.type/><#sep>,
</#if>
</#list>

}
<#list struct.superInterfaces as interface>

impl ${interface.simpleName} for ${struct.simpleName} {
}
</#list>
