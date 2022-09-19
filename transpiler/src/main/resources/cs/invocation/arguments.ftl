<#-- Copy from renderInvocationExpression -->
<#import "../expressions.ftl" as expressions>
<#list expression.arguments as argument><@expressions.renderExpression expression=argument/><#sep>, </#list>