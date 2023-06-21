<#-- @ftlvariable name="feld" type="libldt3.parser.model.Objekt.FeldExtended" -->
<#macro attributes feld prefix>
${prefix}@Feld(value = "${feld.feld.fk}", feldart = Feldart.${feld.feldart.readable()})<#if feld.feldregeln?hasContent || feld.feld.laenge??>
${prefix}@Regelsatz(<#if feld.feldregeln?hasContent>value = <#if feld.feldregeln?size gt 1>{</#if><#list feld.feldregeln as regel>${regel.regelnummer}.class<#sep>, </#list><#if feld.feldregeln?size gt 1>}</#if>, </#if><#if feld.feld.minLaenge??>minLaenge = ${feld.feld.minLaenge}<#if feld.feld.maxLaenge??>, maxLaenge = ${feld.feld.maxLaenge}</#if><#else><#if feld.feld.maxLaenge??>maxLaenge = ${feld.feld.maxLaenge}<#else><#if feld.feld.laenge??>laenge = ${feld.feld.laenge}</#if></#if></#if>)</#if>
</#macro>