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
package simpleserver.config.xml;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import simpleserver.Coordinate.Dimension;

public class DimensionConfig extends PermissionContainer {
  Dimension dimension;
  AreaStorage topAreas;
  public DimensionAreaStorage areas;

  DimensionConfig() {
    super("dimension");
  }

  @Override
  public void finish() {
    areas.buildTree();
  }

  @Override
  void addStorages() {
    areas = DimensionAreaStorage.newInstance();
    addStorage("area", topAreas = new AreaStorage());
    super.addStorages();
  }

  @Override
  void setAttribute(String name, String value) throws SAXException {
    if (name.equals("name")) {
      dimension = Dimension.get(value);
    }
  }

  @Override
  void saveAttributes(AttributeList attributes) {
    attributes.addAttribute("name", dimension);
  }

  @Override
  protected void save(ContentHandler handler, boolean childs, boolean pcdata) throws SAXException {
    if (!topAreas.isEmpty()) {
      super.save(handler, childs, pcdata);
    }
  }

  public void add(Area area) {
    topAreas.add(area);
  }
}
