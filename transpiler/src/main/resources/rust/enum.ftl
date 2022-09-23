pub struct ${enum.simpleName}<'a>(&'a str);

impl ${enum.simpleName}<'static> {
<#list enum.enumValues as value>
    pub const <@snakecase name=value.simpleName uppercase=true/>: ${enum.simpleName}<'static> = ${enum.simpleName}("${value.simpleName}");
</#list>
}
