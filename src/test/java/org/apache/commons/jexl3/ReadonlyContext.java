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

import java.math.MathContext;
import java.nio.charset.Charset;

/**
 * A readonly context wrapper.
 * @since 3.0
 */
public final class ReadonlyContext implements JexlContext, JexlEngine.Options {
    /** The wrapped context. */
    private final JexlContext wrapped;
    /** The wrapped engine options. */
    private final JexlEngine.Options options;

    /**
     * Creates a new readonly context.
     * @param context the wrapped context
     * @param eopts the engine evaluation options
     */
    public ReadonlyContext(JexlContext context, JexlEngine.Options eopts) {
        wrapped = context;
        options = eopts;
    }

    @Override
    public Object get(String name) {
        return wrapped.get(name);
    }

    /**
     * Will throw an UnsupportedOperationException when called; the JexlEngine deals with it appropriately.
     * @param name the unused variable name
     * @param value the unused variable value
     */
    @Override
    public void set(String name, Object value) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean has(String name) {
        return wrapped.has(name);
    }

    @Override
    public Boolean isSilent() {
        return options == null? null : options.isSilent();
    }

    @Override
    public Boolean isStrict() {
        return options == null? null : options.isStrict();
    }

    @Override
    public Boolean isCancellable() {
        return options == null? null : options.isCancellable();
    }

    @Override
    public Boolean isStrictArithmetic() {
        return options == null? null : options.isStrict();
    }

    @Override
    public MathContext getArithmeticMathContext() {
        return options == null? null : options.getArithmeticMathContext();
    }

    @Override
    public int getArithmeticMathScale() {
        return options == null? -1 : options.getArithmeticMathScale();
    }

    @Override
    public Charset getCharset() {
        return options == null? Charset.defaultCharset() : options.getCharset();
    }
}
