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

public class OctetProperty extends NumericProperty<Byte> {
    public OctetProperty(String id, String name, Byte value, Mode mode,
                           Action action, Kind[] kinds) {
        super(id, name, "octet", value, mode, action, kinds);
    }

    public OctetProperty(String id, String name, Byte value, Mode mode,
                           Action action, Kind[] kinds, boolean optional) {
        super(id, name, "octet", value, mode, action, kinds, optional);
    }

    protected Byte fromNumber(Number val) {
        if (val != null){
            return val.byteValue();
        }else{
            return null;
        }
    }

    protected void insert(org.omg.CORBA.Any any, Byte value) {
        any.insert_octet(value);
    }

    protected Byte parseString(String str) {
        return Byte.decode(str);
    }

    protected int compare(Byte lhs, Byte rhs) {
        return Double.compare(lhs, rhs);
    }

    protected Byte subtract(Byte lhs, Byte rhs) {
        return (byte)((byte)lhs - (byte)rhs);
    }

    protected Byte add(Byte lhs, Byte rhs) {
        return (byte)((byte)lhs + (byte)rhs);
    }
}
