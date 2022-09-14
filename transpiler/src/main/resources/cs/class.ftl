<#-- @ftlvariable name="class" type="spoon.reflect.declaration.CtClass" -->
<#import "members.ftl" as members/>
<#import "attributes.ftl" as attributes/>
<#import "comments.ftl" as comments/>
/*
 * Copyright ${year}  Christoph Brill <opensource@christophbrill.de>
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
<@genusing class=class/>

<@namespace package=class.package>

<@class_ klass=class/>

</@namespace>

<#macro class_ klass>
<@comments.comments comments=klass.comments />
<@attributes.classattributes klass/>
public class ${klass.simpleName}<#if klass.superInterfaces?size gt 0> : <#list klass.superInterfaces as interface>${interface.simpleName}<#sep>, </#list></#if>
{
    <#list klass.nestedTypes as nestedType>
    <@class_ klass=nestedType/>
    </#list>
    <@members.classmembers klass/>
}
</#macro>