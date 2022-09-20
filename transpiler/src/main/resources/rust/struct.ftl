#![allow(unused_imports, non_camel_case_types)]

<@genuse struct=struct/>

<#list struct.nestedTypes as nestedType>
pub struct ${struct.simpleName}_${nestedType.simpleName} {
<#list nestedType.fields as field>
    ${field.simpleName}: <@converttype type=field.type/><#sep>,
</#list>

}

</#list>
pub struct ${struct.simpleName} {
<#list struct.fields as field>
<#if field.simpleName != "LOG">
    ${field.simpleName}: <@converttype type=field.type/><#sep>,
</#if>
</#list>

}
