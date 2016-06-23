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

public class LongProperty extends NumericProperty<Integer> {
    public LongProperty(String id, String name, Integer value, Mode mode,
                           Action action, Kind[] kinds) {
        super(id, name, "long", value, mode, action, kinds);
    }

    protected Integer fromNumber(Number val) {
        return val.intValue();
    }

    protected void insert(org.omg.CORBA.Any any, Integer value) {
        any.insert_long(value);
    }

    protected Integer parseString(String str) {
        return Integer.decode(str);
    }

    protected int compare(Integer lhs, Integer rhs) {
        return Double.compare(lhs, rhs);
    }

    protected Integer subtract(Integer lhs, Integer rhs) {
        return (int)((int)lhs - (int)rhs);
    }

    protected Integer add(Integer lhs, Integer rhs) {
        return (int)((int)lhs + (int)rhs);
    }
}
