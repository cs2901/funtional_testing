package translator.business;

import translator.entities.Language;

public interface Translator {

    public String translate(Language from, Language to, String text);
}
