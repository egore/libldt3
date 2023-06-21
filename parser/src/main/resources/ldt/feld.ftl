<#-- @ftlvariable name="feld" type="libldt3.parser.model.Objekt.FeldExtended" -->
<#macro attributes feld>
@Feld(value = "${feld.feld.fk}", feldart = Feldart.${feld.feldart.readable()})
@Regelsatz(<#if feld.feldregeln?hasContent>value = <#if feld.feldregeln?size gt 1>{</#if><#list feld.feldregeln as regel>${regel.regelnummer}.class<#sep>, </#list><#if feld.feldregeln?size gt 1>}</#if>, </#if><#if feld.feld.minLaenge??>minLaenge = ${feld.feld.minLaenge}<#if feld.feld.maxLaenge??>, maxLaenge = ${feld.feld.maxLaenge}</#if><#else><#if feld.feld.maxLaenge??>maxLaenge = ${feld.feld.maxLaenge}<#else>laenge = ${feld.feld.laenge}</#if></#if>)
</#macro>