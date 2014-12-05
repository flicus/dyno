/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 schors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.schors.dyno.plugins.plugin1.commands;

import org.schors.dyno.api.CommandContext;
import org.schors.dyno.api.annotations.Command;
import org.schors.dyno.api.annotations.CommandExecute;
import org.schors.dyno.plugins.plugin1.Plugin1Facility;
import org.schors.dyno.plugins.plugin1.TestClass2;

@Command(
        name = "command1",
        group = "Others",
        shortDescription = "command1 description",
        longDescription = "command1 long description",
        prefixes = {"!c1", "!c1command"})
public class Command1 {

    @CommandExecute
    public void execute(CommandContext context) {
        System.out.println("Command 1 execute");
        Plugin1Facility facility = (Plugin1Facility) context.getFacility(Plugin1Facility.getName());
        facility.method1();
        TestClass2 testClass2 = new TestClass2();
        testClass2.execute();
    }
}
