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

public class ShortProperty extends NumericProperty<Short> {
    public ShortProperty(String id, String name, Short value, Mode mode,
                           Action action, Kind[] kinds) {
        super(id, name, "short", value, mode, action, kinds);
    }

    public ShortProperty(String id, String name, Short value, Mode mode,
                           Action action, Kind[] kinds, boolean optional) {
        super(id, name, "short", value, mode, action, kinds, optional);
    }

    protected Short fromNumber(Number val) {
        if (val != null){
            return val.shortValue();
        }else{
            return null;
        }
    }

    protected void insert(org.omg.CORBA.Any any, Short value) {
        any.insert_short(value);
    }

    protected Short parseString(String str) {
        return Short.decode(str);
    }

    protected int compare(Short lhs, Short rhs) {
        return Double.compare(lhs, rhs);
    }

    protected Short subtract(Short lhs, Short rhs) {
        return (short)((short)lhs - (short)rhs);
    }

    protected Short add(Short lhs, Short rhs) {
        return (short)((short)lhs + (short)rhs);
    }
}
