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

package org.schors.dyno;

import org.schors.dyno.api.CommandContext;
import org.schors.dyno.api.CommandManager;
import org.schors.dyno.api.FacilityManager;

public class Main implements PluginLoader {
    private FacilityManager facilityManager;
    private CommandManager commandManager;
    private PluginManager pluginManager;


    public Main() {
        facilityManager = new FacilityManagerImpl();
        commandManager = new CommandManagerImpl();
        pluginManager = new PluginManager(this);
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        Thread.sleep(10000);
        main.test("!c1 test");
        main.run();
    }

    public void run() {
        synchronized (Thread.currentThread()) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test(String test) {
        String[] aa = test.split(" ");
        CommandContext commandContext = new CommandContext(facilityManager, aa, "me");
        commandManager.processCommand(commandContext);
    }

    @Override
    public void onFacilityDiscovered(Class<?> clazz) {
        facilityManager.addNewFacility(clazz);
    }

    @Override
    public void onCommandDiscovered(Class<?> clazz) {
        commandManager.addNewCommand(clazz);
    }
}
