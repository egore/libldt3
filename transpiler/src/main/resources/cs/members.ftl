<#import "attributes.ftl" as attributes/>
<#import "method.ftl" as method_/>

<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#macro classmembers class>
    <#list class.fields as field>
    <@attributes.fieldattributes field/>
    ${field.visibility!} <@converttype type=field.type/> ${field.simpleName};
    </#list>
    
    <#list class.methods as method>
    <@method_.signature method=method/>
    <@method_.body method=method/>
    </#list>
</#macro>

<#macro interfacemembers interface>
    <#list interface.fields as field>
    <@attributes.fieldattributes field/>
    ${field.visibility!} <@converttype type=field.type/> ${field.simpleName};
    </#list>
    
    <#list interface.methods as method>
    <@method_.signature method=method/>;
    </#list>
</#macro>


<#macro enummembers enum>
    <#list enum.fields as field>
    <#if field.type.simpleName != enum.simpleName>
	public static <@converttype type=field.type/> Get${field.simpleName?cap_first}(this ${enum.simpleName} self)
	{
		switch (self)
		{
			<#list enum.enumValues as value>
			case ${enum.simpleName}.${value.simpleName}: return ${value.defaultExpression.arguments[0]};
			</#list>
			default: throw new Exception();
		}
	}
    </#if>
    </#list>
</#macro>