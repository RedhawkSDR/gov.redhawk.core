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

public class ULongLongProperty extends NumericProperty<Long> {
    public ULongLongProperty(String id, String name, Long value, Mode mode,
                           Action action, Kind[] kinds) {
        super(id, name, "ulonglong", value, mode, action, kinds);
    }

    public ULongLongProperty(String id, String name, Long value, Mode mode,
                           Action action, Kind[] kinds, boolean optional) {
        super(id, name, "ulonglong", value, mode, action, kinds, optional);
    }

    protected Long fromNumber(Number val) {
        if (val != null){
            return val.longValue();
        }else{
            return null;
        }
    }

    protected void insert(org.omg.CORBA.Any any, Long value) {
        any.insert_ulonglong(value);
    }

    protected Long parseString(String str) {
        return UnsignedUtils.parseULongLong(str);
    }

    protected int compare(Long lhs, Long rhs) {
        return UnsignedUtils.compareULongLong(lhs, rhs);
    }

    protected Long subtract(Long lhs, Long rhs) {
        return (long)((long)lhs - (long)rhs);
    }

    protected Long add(Long lhs, Long rhs) {
        return (long)((long)lhs + (long)rhs);
    }
}
