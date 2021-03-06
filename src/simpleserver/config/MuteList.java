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
package simpleserver.config;

import java.util.LinkedList;
import java.util.List;

public class MuteList extends PropertiesConfig {
  public MuteList() {
    super("mute-list.txt");
  }

  public boolean isMuted(String name) {
    return properties.getProperty(name.toLowerCase()) != null;
  }

  public void addName(String name) {
    if (properties.setProperty(name.toLowerCase(), "") == null) {
      save();
    }
  }

  public boolean removeName(String name) {
    if (properties.remove(name.toLowerCase()) != null) {
      save();
      return true;
    }

    return false;
  }

  @Override
  public void load() {
    super.load();

    List<String> names = new LinkedList<String>();
    for (Object name : properties.keySet()) {
      names.add(((String) name).toLowerCase());
    }

    properties.clear();
    for (String name : names) {
      properties.setProperty(name, "");
    }
  }
}
