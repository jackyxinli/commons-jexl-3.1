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
package org.apache.commons.jexl3.parser;

import org.apache.commons.jexl3.internal.Scope;

/**
 * Lambda (function).
 */
public final class ASTJexlLambda extends ASTJexlScript {
    ASTJexlLambda(int id) {
        super(id);
    }

    ASTJexlLambda(Parser p, int id) {
        super(p, id);
    }

    /**
     * @return true if outermost script.
     */
    public boolean isTopLevel() {
        return jjtGetParent() == null;
    }

    /**
     * Creates an array of arguments by copying values up to the number of parameters.
     * @param frame the calling frame
     * @param values the argument values
     * @return the arguments array
     */
    public Scope.Frame createFrame(Scope.Frame frame, Object... values) {
        if (getScope() != null) {
            Scope.Frame cframe = getScope().createFrame(frame);
            if (cframe != null) {
                return cframe.assign(values);
            }
        }
        return null;
    }
}
