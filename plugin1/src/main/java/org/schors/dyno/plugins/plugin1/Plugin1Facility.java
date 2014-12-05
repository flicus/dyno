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

package org.schors.dyno.plugins.plugin1;

import org.schors.dyno.api.FacilityManager;
import org.schors.dyno.api.annotations.Facility;
import org.schors.dyno.api.annotations.FacilityStart;
import org.schors.dyno.api.annotations.FacilityStop;
import org.schors.dyno.api.annotations.Version;

@Facility(name = "Plugin1Facility", version = @Version(major = 1, minor = 0))
public class Plugin1Facility {

    private FacilityManager manager;

    public static String getName() {
        return Plugin1Facility.class.getAnnotation(Facility.class).name();
    }

    @FacilityStart
    public void start(FacilityManager manager) {
        this.manager = manager;
        System.out.println("Plugin1Facility start");
    }

    @FacilityStop
    public void stop() {
        System.out.println("Plugin1Facility stop");

    }

    public void method1() {
        System.out.println("Plugin1Facility method1");
    }
}
