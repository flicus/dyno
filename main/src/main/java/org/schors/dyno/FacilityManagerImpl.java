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

import org.schors.dyno.api.FacilityManager;
import org.schors.dyno.api.annotations.Facility;
import org.schors.dyno.api.annotations.FacilityStart;
import org.schors.dyno.api.annotations.FacilityStop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class FacilityManagerImpl implements FacilityManager {

    private Map<String, FacilityAdapter> facilities = new HashMap<String, FacilityAdapter>();

    public void addNewFacility(Class<?> facility) {
        System.out.println("addNewFacility:: " + facility);
        boolean ok = true;
        String name = null;
        FacilityAdapter facilityAdapter = null;
        if (facility.isAnnotationPresent(Facility.class)) {
            name = facility.getAnnotation(Facility.class).name();
        }
        if (name == null) {
            ok = false;
        }

        try {
            Object o = facility.newInstance();
            facilityAdapter = new FacilityAdapter(o);
            facilityAdapter.start();
        } catch (Exception e) {
            ok = false;
        }

        if (ok) {
            System.out.println("addNewFacility:: done:: " + facilityAdapter);
            facilities.put(name, facilityAdapter);
        }

    }

    public void stop() {
        for (FacilityAdapter adapter : facilities.values()) {
            try {
                adapter.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getFacility(String name) {
        return facilities.get(name).getFacility();
    }

    public class FacilityAdapter<T> {
        private T facility;
        private Method start;
        private Method stop;

        public FacilityAdapter(T facility) {
            this.facility = facility;
            for (Method method : facility.getClass().getMethods()) {
                if (method.isAnnotationPresent(FacilityStart.class)) start = method;
                if (method.isAnnotationPresent(FacilityStop.class)) stop = method;
                if (start != null && stop != null) break;
            }
        }

        public T getFacility() {
            return facility;
        }

        public void start() throws InvocationTargetException, IllegalAccessException {
            start.invoke(facility);
        }

        public void stop() throws InvocationTargetException, IllegalAccessException {
            stop.invoke(facility);
        }

        @Override
        public String toString() {
            return "FacilityAdapter{" +
                    "facility=" + facility +
                    ", start=" + start +
                    ", stop=" + stop +
                    '}';
        }
    }
}
