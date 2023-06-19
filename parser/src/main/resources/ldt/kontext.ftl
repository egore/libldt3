<#-- @ftlvariable name="enum" type="libldt3.parser.model.Regel" -->
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
package libldt3.model.regel.kontext;

<#if kontext.usedFields?has_content>
import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

</#if>
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <@linewrap text=kontext.pruefung prefix="* "/>
<#if kontext.erlaeuterung?length gt 0>
 *
 * <@linewrap text=kontext.erlaeuterung prefix="* "/>
</#if>
 */
public class ${kontext.regelnummer} implements Kontextregel {

    private static final Logger LOG = LoggerFactory.getLogger(${kontext.regelnummer}.class);
<#if kontext.usedFields?has_content>

    private static final Set<String> FIELDTYPES = Set.of(<#list kontext.usedFields as field>"${field.fk}"<#sep>, </#list>);
</#if>

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {
<#if kontext.usedFields?has_content>

        Map<String, Field> fields = findFields(owner, FIELDTYPES);
        if (fields.size() != FIELDTYPES.size()) {
            LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
            return false;
        }

<#if kontext.mandatoryFields?has_content>
        for (Field f : fields.values()) {
            if (containsAnyString(f, owner)) {
                return true;
            }
        }
        return false;
</#if>
<#if kontext.mustRules?has_content>
<#list kontext.mustRules as mustRule>
        // ${mustRule.comment}
        if (<#list mustRule.felder as feld>${feld.feld} == ${feld.init}<#sep> || </#list>) {
            return containsAnyString(fields.get("${mustRule.must.fk}"), owner);
        }
</#list>
</#if>
</#if>
    }

}
