/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.jexl3;

/**
 * Helper class to carry information such as a url/file name, line and column for
 * debugging information reporting.
 */
public class JexlInfo {

    /** line number. */
    private final int line;

    /** column number. */
    private final int column;

    /** name. */
    private final String name;

    /**
     * @return the detailed information in case of an error
     */
    public Detail getDetail() {
        return null;
    }

    /**
     * Describes errors more precisely.
     */
    public interface Detail {
        /**
         * @return the start column on the line that triggered the error
         */
        int start();

        /**
         * @return the end column on the line that triggered the error
         */
        int end();

        /**
         * @return the actual part of code that triggered the error
         */

        @Override
        String toString();
    }

    /**
     * Create info.
     * 
     * @param source source name
     * @param l line number
     * @param c column number
     */
    public JexlInfo(String source, int l, int c) {
        name = source;
        line = l;
        column = c;
    }

    /**
     * Creates info reusing the name.
     * 
     * @param l the line
     * @param c the column
     * @return a new info instance
     */
    public JexlInfo at(int l, int c) {
        return new JexlInfo(name, l, c);
    }

    /**
     * The copy constructor.
     * 
     * @param copy the instance to copy
     */
    protected JexlInfo(JexlInfo copy) {
        name = copy.getName();
        line = copy.getLine();
        column = copy.getColumn();
    }

    /**
     * Formats this info in the form 'name&#064;line:column'.
     * 
     * @return the formatted info
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name != null? name : "");
        if (line > 0) {
            sb.append("@");
            sb.append(line);
            if (column > 0) {
                sb.append(":");
                sb.append(column);
            }
        }
        JexlInfo.Detail dbg = getDetail();
        if (dbg!= null) {
            sb.append("![");
            sb.append(dbg.start());
            sb.append(",");
            sb.append(dbg.end());
            sb.append("]: '");
            sb.append(dbg.toString());
            sb.append("'");
        }
        return sb.toString();
    }

    /**
     * Gets the file/script/url name.
     * 
     * @return template name
     */
    public final String getName() {
        return name;
    }

    /**
     * Gets the line number.
     * 
     * @return line number.
     */
    public final int getLine() {
        return line;
    }

    /**
     * Gets the column number.
     * 
     * @return the column.
     */
    public final int getColumn() {
        return column;
    }
}

