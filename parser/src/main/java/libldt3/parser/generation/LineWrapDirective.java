package libldt3.parser.generation;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModel;
import org.apache.commons.text.WordUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class LineWrapDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws IOException {

        TemplateModel textModel = (TemplateModel) params.get("text");
        if (textModel == null) {
            return;
        }
        String text;
        if (textModel instanceof StringModel) {
            text = ((StringModel) textModel).getAsString();
        } else if (textModel instanceof SimpleScalar) {
            text = ((SimpleScalar) textModel).getAsString();
        } else {
            throw new UnsupportedOperationException("Cannot handle class template model " + textModel.getClass().getSimpleName());
        }

        TemplateModel prefixModel = (TemplateModel) params.get("prefix");
        String prefix;
        if (prefixModel instanceof StringModel) {
            prefix = ((StringModel) prefixModel).getAsString();
        } else if (prefixModel instanceof SimpleScalar) {
            prefix = ((SimpleScalar) prefixModel).getAsString();
        } else {
            throw new UnsupportedOperationException("Cannot handle class template model " + prefixModel.getClass().getSimpleName());
        }

        text = text.replaceAll("\r?\n", "\n");
        text = text.replaceAll("[„“”]", "\"");

        Writer writer = env.getOut();
        String[] split = text.split("\n");
        boolean first = true;
        for (String t : split) {
            if (!first) {
                writer.append("\n ").append(prefix);
            }
            writer.append(WordUtils.wrap(t, 80, "\n " + prefix, false));
            first = false;
        }
    }
}
