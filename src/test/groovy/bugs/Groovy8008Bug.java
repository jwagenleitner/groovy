/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package groovy.bugs;

import junit.framework.TestCase;
import org.codehaus.groovy.ast.ClassNode;

import java.lang.reflect.Constructor;

public class Groovy8008Bug extends TestCase {

    public void testConstructorParamAnnotationsWithSyntheticParam() throws Exception {
        Class<Inner> innerClass = Inner.class;
        Constructor<Inner> ctor = innerClass.getDeclaredConstructor(Groovy8008Bug.class, String.class, String.class);

        assertEquals(3, ctor.getParameterTypes().length); //Groovy8008Bug,String,String
        assertEquals(2, ctor.getParameterAnnotations().length); //[],[@Deprecated]

        ClassNode cn = new ClassNode(innerClass);

        assertTrue(cn.getDeclaredConstructors().size() > 0);
    }

    private class Inner {
        private Inner(String arg1, @Deprecated String arg2) { }
    }
}
