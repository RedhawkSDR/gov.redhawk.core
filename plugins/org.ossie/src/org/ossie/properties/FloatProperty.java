/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file
 * distributed with this source distribution.
 *
 * This file is part of REDHAWK core.
 *
 * REDHAWK core is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * REDHAWK core is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

/*
 * WARNING: This file is generated from NumericProperty.template.
 *          Do not modify directly.
 */

package org.ossie.properties;

public class FloatProperty extends NumericProperty<Float> {
    public FloatProperty(String id, String name, Float value, Mode mode,
                           Action action, Kind[] kinds) {
        super(id, name, "float", value, mode, action, kinds);
    }

    protected Float fromNumber(Number val) {
        return val.floatValue();
    }

    protected void insert(org.omg.CORBA.Any any, Float value) {
        any.insert_float(value);
    }

    protected Float parseString(String str) {
        return Float.parseFloat(str);
    }

    protected int compare(Float lhs, Float rhs) {
        return Double.compare(lhs, rhs);
    }

    protected Float subtract(Float lhs, Float rhs) {
        return (float)((float)lhs - (float)rhs);
    }

    protected Float add(Float lhs, Float rhs) {
        return (float)((float)lhs + (float)rhs);
    }
}
