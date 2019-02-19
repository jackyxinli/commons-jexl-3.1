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

import java.util.Map;

import org.apache.commons.jexl3.junit.Asserter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for array access operator []
 *
 * @since 2.0
 */
@SuppressWarnings({"UnnecessaryBoxing", "AssertEqualsBetweenInconvertibleTypes"})
public class SideEffectTest extends JexlTestCase {

    private Asserter asserter;

    public SideEffectTest() {
        super("SideEffectTest");
    }

    @Override
    @Before
    public void setUp() {
        asserter = new Asserter(JEXL);
    }

    @Test
    public void testSideEffectVar() throws Exception {
        Map<String,Object> context = asserter.getVariables();
        Integer i41 = Integer.valueOf(4141);
        Object foo = i41;

        context.put("foo", foo);
        asserter.assertExpression("foo += 2", i41 + 2);
        Assert.assertEquals(context.get("foo"), i41 + 2);

        context.put("foo", foo);
        asserter.assertExpression("foo -= 2", i41 - 2);
        Assert.assertEquals(context.get("foo"), i41 - 2);

        context.put("foo", foo);
        asserter.assertExpression("foo *= 2", i41 * 2);
        Assert.assertEquals(context.get("foo"), i41 * 2);

        context.put("foo", foo);
        asserter.assertExpression("foo /= 2", i41 / 2);
        Assert.assertEquals(context.get("foo"), i41 / 2);

        context.put("foo", foo);
        asserter.assertExpression("foo %= 2", i41 % 2);
        Assert.assertEquals(context.get("foo"), i41 % 2);

        context.put("foo", foo);
        asserter.assertExpression("foo &= 3", (long) (i41 & 3));
        Assert.assertEquals(context.get("foo"), (long)(i41 & 3));

        context.put("foo", foo);
        asserter.assertExpression("foo |= 2", (long)(i41 | 2));
        Assert.assertEquals(context.get("foo"), (long)(i41 | 2));

        context.put("foo", foo);
        asserter.assertExpression("foo ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(context.get("foo"), (long)(i41 ^ 2));
    }

    @Test
    public void testSideEffectVarDots() throws Exception {
        Map<String,Object> context = asserter.getVariables();
        Integer i41 = Integer.valueOf(4141);
        Object foo = i41;

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux += 2", i41 + 2);
        Assert.assertEquals(context.get("foo.bar.quux"), i41 + 2);

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux -= 2", i41 - 2);
        Assert.assertEquals(context.get("foo.bar.quux"), i41 - 2);

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux *= 2", i41 * 2);
        Assert.assertEquals(context.get("foo.bar.quux"), i41 * 2);

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux /= 2", i41 / 2);
        Assert.assertEquals(context.get("foo.bar.quux"), i41 / 2);

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux %= 2", i41 % 2);
        Assert.assertEquals(context.get("foo.bar.quux"), i41 % 2);

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux &= 3", (long) (i41 & 3));
        Assert.assertEquals(context.get("foo.bar.quux"), (long)(i41 & 3));

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux |= 2", (long)(i41 | 2));
        Assert.assertEquals(context.get("foo.bar.quux"), (long)(i41 | 2));

        context.put("foo.bar.quux", foo);
        asserter.assertExpression("foo.bar.quux ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(context.get("foo.bar.quux"), (long)(i41 ^ 2));
    }

    @Test
    public void testSideEffectArray() throws Exception {
        Integer i41 = Integer.valueOf(4141);
        Integer i42 = Integer.valueOf(42);
        Integer i43 = Integer.valueOf(43);
        String s42 = "fourty-two";
        String s43 = "fourty-three";
        Object[] foo = new Object[3];
        foo[1] = i42;
        foo[2] = i43;
        asserter.setVariable("foo", foo);
        foo[0] = i41;
        asserter.assertExpression("foo[0] += 2", i41 + 2);
        Assert.assertEquals(foo[0], i41 + 2);
        foo[0] = i41;
        asserter.assertExpression("foo[0] -= 2", i41 - 2);
        Assert.assertEquals(foo[0], i41 - 2);
        foo[0] = i41;
        asserter.assertExpression("foo[0] *= 2", i41 * 2);
        Assert.assertEquals(foo[0], i41 * 2);
        foo[0] = i41;
        asserter.assertExpression("foo[0] /= 2", i41 / 2);
        Assert.assertEquals(foo[0], i41 / 2);
        foo[0] = i41;
        asserter.assertExpression("foo[0] %= 2", i41 % 2);
        Assert.assertEquals(foo[0], i41 % 2);
        foo[0] = i41;
        asserter.assertExpression("foo[0] &= 3", (long) (i41 & 3));
        Assert.assertEquals(foo[0], (long)(i41 & 3));
        foo[0] = i41;
        asserter.assertExpression("foo[0] |= 2", (long)(i41 | 2));
        Assert.assertEquals(foo[0], (long)(i41 | 2));
        foo[0] = i41;
        asserter.assertExpression("foo[0] ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(foo[0], (long)(i41 ^ 2));
    }

    @Test
    public void testSideEffectDotArray() throws Exception {
        Integer i41 = Integer.valueOf(4141);
        Integer i42 = Integer.valueOf(42);
        Integer i43 = Integer.valueOf(43);
        String s42 = "fourty-two";
        String s43 = "fourty-three";
        Object[] foo = new Object[3];
        foo[1] = i42;
        foo[2] = i43;
        asserter.setVariable("foo", foo);
        foo[0] = i41;
        asserter.assertExpression("foo.0 += 2", i41 + 2);
        Assert.assertEquals(foo[0], i41 + 2);
        foo[0] = i41;
        asserter.assertExpression("foo.0 -= 2", i41 - 2);
        Assert.assertEquals(foo[0], i41 - 2);
        foo[0] = i41;
        asserter.assertExpression("foo.0 *= 2", i41 * 2);
        Assert.assertEquals(foo[0], i41 * 2);
        foo[0] = i41;
        asserter.assertExpression("foo.0 /= 2", i41 / 2);
        Assert.assertEquals(foo[0], i41 / 2);
        foo[0] = i41;
        asserter.assertExpression("foo.0 %= 2", i41 % 2);
        Assert.assertEquals(foo[0], i41 % 2);
        foo[0] = i41;
        asserter.assertExpression("foo.0 &= 3", (long) (i41 & 3));
        Assert.assertEquals(foo[0], (long)(i41 & 3));
        foo[0] = i41;
        asserter.assertExpression("foo.0 |= 2", (long)(i41 | 2));
        Assert.assertEquals(foo[0], (long)(i41 | 2));
        foo[0] = i41;
        asserter.assertExpression("foo.0 ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(foo[0], (long)(i41 ^ 2));
    }

    @Test
    public void testSideEffectAntishArray() throws Exception {
        Integer i41 = Integer.valueOf(4141);
        Integer i42 = Integer.valueOf(42);
        Integer i43 = Integer.valueOf(43);
        Object[] foo = new Object[3];
        foo[1] = i42;
        foo[2] = i43;
        asserter.setVariable("foo.bar", foo);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] += 2", i41 + 2);
        Assert.assertEquals(foo[0], i41 + 2);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] -= 2", i41 - 2);
        Assert.assertEquals(foo[0], i41 - 2);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] *= 2", i41 * 2);
        Assert.assertEquals(foo[0], i41 * 2);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] /= 2", i41 / 2);
        Assert.assertEquals(foo[0], i41 / 2);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] %= 2", i41 % 2);
        Assert.assertEquals(foo[0], i41 % 2);
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] &= 3", (long) (i41 & 3));
        Assert.assertEquals(foo[0], (long)(i41 & 3));
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] |= 2", (long)(i41 | 2));
        Assert.assertEquals(foo[0], (long)(i41 | 2));
        foo[0] = i41;
        asserter.assertExpression("foo.bar[0] ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(foo[0], (long)(i41 ^ 2));
    }

    public static class Foo {
        int value;
        Foo(int v) {
            value = v;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }

        public void setValue(long v) {
            value = (int) v;
        }
        public int getValue() {
            return value;
        }

        public void setBar(int x, long v) {
            value = (int) v + x;
        }

        public int getBar(int x) {
            return value + x;
        }
    }

    @Test
    public void testSideEffectBean() throws Exception {
        Integer i41 = Integer.valueOf(4141);
        Foo foo = new Foo(0);
        asserter.setVariable("foo", foo);
        foo.value = i41;
        asserter.assertExpression("foo.value += 2", i41 + 2);
        Assert.assertEquals(foo.value, i41 + 2);
        foo.value = i41;
        asserter.assertExpression("foo.value -= 2", i41 - 2);
        Assert.assertEquals(foo.value, i41 - 2);
        foo.value = i41;
        asserter.assertExpression("foo.value *= 2", i41 * 2);
        Assert.assertEquals(foo.value, i41 * 2);
        foo.value = i41;
        asserter.assertExpression("foo.value /= 2", i41 / 2);
        Assert.assertEquals(foo.value, i41 / 2);
        foo.value = i41;
        asserter.assertExpression("foo.value %= 2", i41 % 2);
        Assert.assertEquals(foo.value, i41 % 2);
        foo.value = i41;
        asserter.assertExpression("foo.value &= 3", (long) (i41 & 3));
        Assert.assertEquals(foo.value, (long)(i41 & 3));
        foo.value = i41;
        asserter.assertExpression("foo.value |= 2", (long)(i41 | 2));
        Assert.assertEquals(foo.value, (long)(i41 | 2));
        foo.value = i41;
        asserter.assertExpression("foo.value ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(foo.value, (long)(i41 ^ 2));
    }

    @Test
    public void testSideEffectBeanContainer() throws Exception {
        Integer i41 = Integer.valueOf(4141);
        Foo foo = new Foo(0);
        asserter.setVariable("foo", foo);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] += 2", i41 + 2);
        Assert.assertEquals(foo.value, i41 + 2);
        foo.value = i41;
        asserter.assertExpression("foo.bar[1] += 2", i41 + 3);
        Assert.assertEquals(foo.value, i41 + 4);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] -= 2", i41 - 2);
        Assert.assertEquals(foo.value, i41 - 2);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] *= 2", i41 * 2);
        Assert.assertEquals(foo.value, i41 * 2);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] /= 2", i41 / 2);
        Assert.assertEquals(foo.value, i41 / 2);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] %= 2", i41 % 2);
        Assert.assertEquals(foo.value, i41 % 2);
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] &= 3", (long) (i41 & 3));
        Assert.assertEquals(foo.value, (long)(i41 & 3));
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] |= 2", (long)(i41 | 2));
        Assert.assertEquals(foo.value, (long)(i41 | 2));
        foo.value = i41;
        asserter.assertExpression("foo.bar[0] ^= 2", (long)(i41 ^ 2));
        Assert.assertEquals(foo.value, (long)(i41 ^ 2));
    }

    @Test
    public void testArithmeticSelf() throws Exception {
        JexlEngine jexl = new JexlBuilder().cache(64).arithmetic(new SelfArithmetic(false)).create();
        JexlContext jc = null;
        runSelfOverload(jexl, jc);
        runSelfOverload(jexl, jc);
    }

    @Test
    public void testArithmeticSelfNoCache() throws Exception {
        JexlEngine jexl = new JexlBuilder().cache(0).arithmetic(new SelfArithmetic(false)).create();
        JexlContext jc = null;
        runSelfOverload(jexl, jc);
    }

    protected void runSelfOverload(JexlEngine jexl, JexlContext jc) {
        JexlScript script;
        Object result;
        script = jexl.createScript("(x, y)->{ x += y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115 + 15,  result);
        Var v0 = new Var(3115);
        result = script.execute(jc, v0, new Var(15));
        Assert.assertEquals(result, v0);
        Assert.assertEquals(3115 + 15,  v0.value);

        script = jexl.createScript("(x, y)->{ x -= y}");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115 - 15,  result);
        Var v1 = new Var(3115);
        result = script.execute(jc, v1, new Var(15));
        Assert.assertNotEquals(result, v1); // not a real side effect
        Assert.assertEquals(3115 - 15,  ((Var) result).value);

        script = jexl.createScript("(x, y)->{ x *= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115 * 15,  result);
        Var v2 = new Var(3115);
        result = script.execute(jc, v2, new Var(15));
        Assert.assertEquals(result, v2);
        Assert.assertEquals(3115 * 15,  v2.value);

        script = jexl.createScript("(x, y)->{ x /= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115 / 15,  result);
        Var v3 = new Var(3115);
        result = script.execute(jc, v3, new Var(15));
        Assert.assertEquals(result, v3);
        Assert.assertEquals(3115 / 15,  v3.value);

        script = jexl.createScript("(x, y)->{ x %= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115 % 15,  result);
        Var v4 = new Var(3115);
        result = script.execute(jc, v4, new Var(15));
        Assert.assertEquals(result, v4);
        Assert.assertEquals(3115 % 15,  v4.value);

        script = jexl.createScript("(x, y)->{ x &= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115L & 15,  result);
        Var v5 = new Var(3115);
        result = script.execute(jc, v5, new Var(15));
        Assert.assertEquals(result, v5);
        Assert.assertEquals(3115 & 15,  v5.value);

        script = jexl.createScript("(x, y)->{ x |= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115L | 15,  result);
        Var v6 = new Var(3115);
        result = script.execute(jc, v6, new Var(15));
        Assert.assertEquals(result, v6);
        Assert.assertEquals(3115L | 15,  v6.value);

        script = jexl.createScript("(x, y)->{ x ^= y }");
        result = script.execute(jc, 3115, 15);
        Assert.assertEquals(3115L ^ 15,  result);
        Var v7 = new Var(3115);
        result = script.execute(jc, v7, new Var(15));
        Assert.assertEquals(result, v7);
        Assert.assertEquals(3115L ^ 15,  v7.value);
    }


    @Test
    public void testOverrideGetSet() throws Exception {
        JexlEngine jexl = new JexlBuilder().cache(64).arithmetic(new SelfArithmetic(false)).create();
        JexlContext jc = null;

        JexlScript script;
        Object result;
        Var v0 = new Var(3115);
        script = jexl.createScript("(x)->{ x.value}");
        result = script.execute(jc, v0);
        Assert.assertEquals(3115, result);
        script = jexl.createScript("(x)->{ x['VALUE']}");
        result = script.execute(jc, v0);
        Assert.assertEquals(3115, result);
        script = jexl.createScript("(x,y)->{ x.value = y}");
        result = script.execute(jc, v0, 42);
        Assert.assertEquals(42, result);
        script = jexl.createScript("(x,y)->{ x['VALUE'] = y}");
        result = script.execute(jc, v0, 169);
        Assert.assertEquals(169, result);
    }

    public static class Var {
        int value;

        Var(int v) {
            value = v;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    // an arithmetic that performs side effects
    public static class SelfArithmetic extends JexlArithmetic {
        public SelfArithmetic(boolean strict) {
            super(strict);
        }

        public Object propertyGet(Var var, String property) {
            return "value".equals(property)? var.value : JexlEngine.TRY_FAILED;
        }

        public Object propertySet(Var var, String property, int v) {
            return "value".equals(property)? var.value = v : JexlEngine.TRY_FAILED;
        }

        public Object arrayGet(Var var, String property) {
            return "VALUE".equals(property)? var.value : JexlEngine.TRY_FAILED;
        }

        public Object arraySet(Var var, String property, int v) {
            return "VALUE".equals(property)? var.value = v : JexlEngine.TRY_FAILED;
        }

        public JexlOperator selfAdd(Var lhs, Var rhs) {
            lhs.value += rhs.value;
            return JexlOperator.ASSIGN;
        }

        // for kicks, this one does not side effect but overloads nevertheless
        public Var selfSubtract(Var lhs, Var rhs) {
            return new Var(lhs.value - rhs.value);
        }

        public JexlOperator selfDivide(Var lhs, Var rhs) {
            lhs.value /= rhs.value;
            return JexlOperator.ASSIGN;
        }

        public JexlOperator selfMultiply(Var lhs, Var rhs) {
            lhs.value *= rhs.value;
            return JexlOperator.ASSIGN;
        }

        public JexlOperator selfMod(Var lhs, Var rhs) {
            lhs.value %= rhs.value;
            return JexlOperator.ASSIGN;
        }

        public Var and(Var lhs, Var rhs) {
            return new Var(lhs.value & rhs.value);
        }

        public JexlOperator selfAnd(Var lhs, Var rhs) {
            lhs.value &= rhs.value;
            return JexlOperator.ASSIGN;
        }

        public Var or(Var lhs, Var rhs) {
            return new Var(lhs.value | rhs.value);
        }

        public JexlOperator selfOr(Var lhs, Var rhs) {
            lhs.value |= rhs.value;
            return JexlOperator.ASSIGN;
        }

        public Var xor(Var lhs, Var rhs) {
            return new Var(lhs.value ^ rhs.value);
        }

        public JexlOperator selfXor(Var lhs, Var rhs) {
            lhs.value ^= rhs.value;
            return JexlOperator.ASSIGN;
        }
    }
}
