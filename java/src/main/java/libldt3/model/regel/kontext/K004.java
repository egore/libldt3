/*
 * Copyright 2016-2022  Christoph Brill <opensource@christophbrill.de>
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

import static libldt3.model.regel.kontext.KontextregelHelper.containsAnyString;
import static libldt3.model.regel.kontext.KontextregelHelper.findField;

import java.lang.reflect.Field;

import libldt3.model.enums.Abrechnungsinfo;

public class K004 implements Kontextregel {

    @Override
    public boolean isValid(Object owner) throws IllegalAccessException {
        // TODO should also check for FK 8000 = 8205

        Field source1 = findField(owner, "8401");
        if (source1 == null) {
            return true;
        }
        Object o1 = source1.get(owner);
        if (o1 == null) {
            return true;
        }
        if (!(o1 instanceof String)) {
            return false;
        }
        if (!"E".equals(o1) && !"N".equals(o1)) {
            return true;
        }

        Field source2 = findField(owner, "7303");
        if (source2 == null) {
            return true;
        }

        Object o2 = source2.get(owner);
        if (o2 == null) {
            return true;
        }
        if (!(o2 instanceof Abrechnungsinfo)) {
            return false;
        }

        switch ((Abrechnungsinfo) o2) {
            case GkvLaborfacharzt:
            case GkvLg:
            case Asv:
            case GkvLaborfacharztPraeventiv:
            case GkgLgPraeventiv:
                Field f = findField(owner, "4241");
                return containsAnyString(f, owner);
            default:
                return true;
        }
    }

}
