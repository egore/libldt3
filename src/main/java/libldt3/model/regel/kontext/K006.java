/*
 * Copyright 2016  Christoph Brill <egore911@gmail.com>
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
import static libldt3.model.regel.kontext.KontextregelHelper.findFields;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K006 implements Kontextregel {
	
	private static final Logger LOG = LoggerFactory.getLogger(K006.class);

	private static final Set<String> FIELDTYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("8428", "8430", "8429", "8431")));

	@Override
	public boolean isValid(Object owner) throws IllegalAccessException {

		Map<String, Field> fields = findFields(owner, FIELDTYPES);
		if (fields.size() != FIELDTYPES.size()) {
			LOG.error("Class of {} must have fields {}", owner, FIELDTYPES);
			return false;
		}

		boolean has8431 = false;
		boolean has8428 = true;
		for (Map.Entry<String, Field> entry : fields.entrySet()) {
			if ("8431".equals(entry.getKey())) {
				has8431 = containsAnyString(entry.getValue(), owner);
			} else {
				has8428 |= containsAnyString(entry.getValue(), owner);
			}
		}
		return !has8428 || (has8428 && !has8431);
	}

}
