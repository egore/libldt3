package libldt3.model.regel.erlaubt;

import libldt3.model.regel.Regel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class E028 implements Regel {

    private static final Logger LOG = LoggerFactory.getLogger(E028.class);

    @Override
    public boolean isValid(String value) {
        LOG.warn("Ignoring rule E028");
        return true;
    }
}
