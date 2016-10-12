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
package libldt3.model.regel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class to implement regular expression based rules
 */
abstract class RegularExpressionRegel implements Regel {

	private static final Logger LOG = LoggerFactory.getLogger(RegularExpressionRegel.class);

	private final Pattern pattern;

	RegularExpressionRegel(Pattern pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean isValid(Object value) {
		if (value == null) {
			return true;
		}

		// CharSequence is allowed, try to interpret the value as this first
		if (value instanceof CharSequence) {
			return pattern.matcher((CharSequence) value).matches();
		}

		// Iterable<CharSequence> is also OK, next try ...
		if (value instanceof Iterable) {
			for (Object o : (Iterable<?>) value) {
				// the Iterable shall only contain CharSequence
				if (!(o instanceof CharSequence)) {
					LOG.debug("Object in given liste was not a CharSequence, but {}", o.getClass());
					return false;
				}
				// If any element did not match, abort
				if (!pattern.matcher((CharSequence) o).matches()) {
					return false;
				}
			}

			// If all matched or list was empty, we're good
			return true;
		}

		// If the rule was applied to anything else, abort
		LOG.debug("Given object was not a CharSequence, but {}", value.getClass());
		return false;
	}

}
