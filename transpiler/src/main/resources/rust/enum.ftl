#![allow(non_camel_case_types)]

pub struct ${enum.simpleName}<'a>(&'a str);

impl ${enum.simpleName}<'static> {
<#list enum.enumValues as value>
    pub const ${value.simpleName}: ${enum.simpleName}<'static> = ${enum.simpleName}("${value.simpleName}");
</#list>
}
