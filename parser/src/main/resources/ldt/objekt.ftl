<#-- @ftlvariable name="objekt" type="libldt3.parser.model.Objekt" -->
<#import "./feld.ftl" as feldFtl/>
/*
 * Copyright 2016-${year}  Christoph Brill <opensource@christophbrill.de>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package libldt3.model.objekte;

<#if objekt.usingLocalDate>
import java.time.LocalDate;
</#if>
<#if objekt.usingList>
import java.util.List;

</#if>
import libldt3.annotations.Feld;
import libldt3.annotations.Feldart;
import libldt3.annotations.Objekt;
import libldt3.annotations.Regelsatz;
import libldt3.model.Kontext;
<#list objekt.imports as import>
import libldt3.model.${import};
</#list>

/**
 * <@linewrap text=objekt.beschreibung prefix="* "/>
 */
@Objekt(<#if objekt.kontextregeln?hasContent>value = "${objekt.nummer}", kontextregeln = {<#list objekt.kontextregeln as regel>${regel.regelnummer}.class<#sep>, </#list>}<#else>"${objekt.nummer}"</#if>)
public class ${objekt.name} implements Kontext {
<#list objekt.children as child>

    @Objekt<#if child.kontextregeln?hasContent>(kontextregeln = <#if child.kontextregeln?size gt 1>{</#if><#list child.kontextregeln as regel>${regel.regelnummer}.class<#sep>, </#list><#if child.kontextregeln?size gt 1>}</#if>)</#if>
    public static class ${child.name} implements Kontext {
<#list child.felder as feld>
<#if feld.name != 'value'>
        <@feldFtl.attributes feld=feld prefix="        "/>
<#else>
        @SuppressWarnings("unused")
</#if>
        public <#if feld.vorkommen.wert == 'n'>List<${feld.typ}><#else>${feld.typ}</#if> ${feld.name};
</#list>
    }
</#list>

<#list objekt.felder as feld>
    <@feldFtl.attributes feld=feld prefix="    "/>
    public <#if feld.vorkommen.wert == 'n'>List<${feld.typ}><#else>${feld.typ}</#if> ${feld.name};
</#list>

}
