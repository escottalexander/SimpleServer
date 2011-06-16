/*
 * Copyright (c) 2010 SimpleServer authors (see CONTRIBUTORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package simpleserver.lang;

import java.util.HashMap;
import java.util.Map;

import simpleserver.Resource;

public class Translations implements Resource {
  protected String translationName;
  private final Map<String, TranslationFile> translations;

  public Translations() {
    translationName = "en";

    translations = new HashMap<String, TranslationFile>();
    translations.put("template", new TranslationFile("template"));
  }

  public String get(String key) {
    if (translationName.equals("en")) {
      return key;
    } else {
      return translations.get(translationName).get(key);
    }
  }

  public boolean setLanguage(String languageCode) {
    if (translations.get(languageCode) != null ||
        languageCode.equals("en")) {
      translationName = languageCode;
      return true;
    } else {
      System.out.println(languageCode + "is an unknown language! Using English (en) instead.");
      translationName = "en";
      return false;
    }
  }

  public void load() {
    for (String key : translations.keySet()) {
      translations.get(key).load();
    }
  }

  public void save() {
    return;
  }

  private static class TranslationsHolder {
    public static final Translations INSTANCE = new Translations();
  }

  public static Translations getInstance() {
    return TranslationsHolder.INSTANCE;
  }
}
