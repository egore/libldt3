<#macro renderExpression expression force_array=false>
    <@comments.comments comments=expression.comments />
    <@compress single_line=true>
    <#switch expression.class.simpleName>
        <#case "CtBinaryOperatorImpl">
            <@renderBinaryOperatorExpression expression/>
            <#break>
        <#case "CtConstructorCallImpl">
            <@renderConstructorCallExpression expression/>
            <#break>
        <#case "CtFieldReadImpl">
        <#case "CtFieldWriteImpl">
            <@renderFieldReadExpression expression force_array/>
            <#break>
        <#case "CtInvocationImpl">
            <@renderInvocationExpression expression/>
            <#break>
        <#case "CtLiteralImpl">
            <@renderLiteralExpression expression/>
            <#break>
        <#case "CtNewArrayImpl">
            <@renderNewArrayExpression expression/>
            <#break>
        <#case "CtThisAccessImpl">
            <@renderThisAccessExpression expression/>
            <#break>
        <#case "CtTypeAccessImpl">
            <@renderTypeAccessExpression expression/>
            <#break>
        <#case "CtUnaryOperatorImpl">
            <@renderUnaryOperatorExpression expression/>
            <#break>
        <#case "CtVariableReadImpl">
        <#case "CtVariableAccessImpl">
            <@renderVariableAccessExpression expression/>
            <#break>
        <#default>
            // XXX renderExpression ${expression.class.simpleName} is unknown
            <#break>
    </#switch>
    </@compress>
</#macro>

<#macro renderBinaryOperatorExpression expression>
    <@renderExpression expression=expression.leftHandOperand/>
        <#switch expression.kind>
            <#case "AND">&&<#break>
            <#case "BITAND">&<#break>
            <#case "BITOR">|<#break>
            <#case "BITXOR">^<#break>
            <#case "DIV">/<#break>
            <#case "EQ">==<#break>
            <#case "GE">>=<#break>
            <#case "GT">><#break>
            <#case "INSTANCEOF"> is <#break>
            <#case "LE"><<#break>
            <#case "LT"><=<#break>
            <#case "MINUS">-<#break>
            <#case "MOD">%<#break>
            <#case "MUL">*<#break>
            <#case "NE">!=<#break>
            <#case "OR">||<#break>
            <#case "PLUS">+<#break>
            <#case "SL"><<<#break>
            <#case "SR">>><#break>
            <#case "USR"><<<<#break>
            <#default>// XXX renderBinaryOperatorExpression ${expression.kind} is unknown
        </#switch>
    <@renderExpression expression=expression.rightHandOperand/>
</#macro>

<#macro renderConstructorCallExpression expression>
    <@converttype type=expression.executable.type/>(<#list expression.arguments as argument><@renderExpression expression=argument/><#sep>, </#list>)
</#macro>

<#macro renderFieldReadExpression expression force_array>
    <#if force_array!false>new [] { </#if>
    <#assign fieldName>${expression.variable.simpleName}</#assign>
    <#switch expression.target.class.simpleName>
        <#case "CtTypeAccessImpl">
            <#assign typeName>${expression.target.accessedType.simpleName}</#assign>
            <#if fieldName == "class">
                typeof(${typeName})
            <#else>
                <@renderTypeAccessExpression expression=expression.target/>.${fieldName}
            </#if>
            <#break>
        <#case "CtThisAccessImpl">
            <@renderThisAccessExpression expression=expression.target/>.${fieldName}
            <#break>
        <#case "CtVariableReadImpl">
            <@renderVariableAccessExpression expression=expression.target/>.${fieldName}
            <#break>
        <#default>
            // XXX renderFieldReadExpression ${expression.target.class.simpleName} is unknown
    </#switch>
    <#if force_array!false> }</#if>
</#macro>

<#macro renderInvocationExpression expression>
<#if expression.target?? && expression.target.class.simpleName == "CtTypeAccessImpl" && expression.target.accessedType.simpleName == "Pattern" && expression.executable.simpleName == "compile">
    new Regex(<#list expression.arguments as argument><@renderExpression expression=argument/><#sep>, </#list>)
<#else>
    <#if expression.target??><@renderExpression expression=expression.target/>.</#if>
    ${expression.executable.simpleName}(<#list expression.arguments as argument><@renderExpression expression=argument/><#sep>, </#list>)
</#if>
</#macro>

<#macro renderLiteralExpression expression>
    <#if expression.value??>
        <#if expression.value?is_string>
            "${expression.value?replace("\\", "\\\\")}"
        <#elseif expression.value?is_boolean>
            ${expression.value?c}
        <#else>
            ${expression.value}
        </#if>
    <#else>
        null
    </#if>
</#macro>

<#macro renderNewArrayExpression expression>
    new [] { <#list expression.elements as element><@renderExpression expression=element/><#sep>, </#list> }
</#macro>

<#macro renderTypeAccessExpression expression>
    <@converttype type=expression.accessedType with_nullability=false/>
</#macro>

<#macro renderThisAccessExpression expression>
    this
</#macro>

<#macro renderUnaryOperatorExpression expression>
    <#switch expression.kind>
        <#case "POS">+<#break>
        <#case "NEG">-<#break>
        <#case "NOT">!<#break>
        <#case "COMPL">~<#break>
        <#case "PREINC">++<#break>
        <#case "PREDEC">--<#break>
        <#case "POSTINC">
        <#case "POSTDEC">
            <#break>
        <#default>// XXX renderUnaryOperatorExpression ${expression.kind} is unknown
    </#switch>
    <@renderExpression expression=expression.operand/>
    <#switch expression.kind>
        <#case "POSTINC">++<#break>
        <#case "POSTDEC">--<#break>
    </#switch>
</#macro>

<#macro renderVariableAccessExpression expression>
    ${expression.variable.simpleName}
</#macro>
