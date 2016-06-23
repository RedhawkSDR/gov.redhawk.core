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
 * WARNING: This file is generated from ComplexProperty.template.
 *          Do not modify directly.
 */

package org.ossie.properties;

import org.omg.CORBA.Any;

import CF.complexOctet;
import CF.complexOctetHelper;

public class ComplexOctetProperty extends AbstractSimpleProperty<complexOctet> {
    public ComplexOctetProperty(String id, String name, complexOctet value, Mode mode, Action action, Kind[] kinds) {
        super(id, name, "octet", value, mode, action, kinds);
    }

    protected complexOctet extract(Any any) {
        try {
            return (complexOctet)AnyUtils.convertAny(any);
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException("Incorrect any type recevied");
        }
    }

    protected void insert(Any any, complexOctet value) {
        complexOctetHelper.insert(any, value);
    }

    protected complexOctet parseString(String str) {
        return ComplexUtils.parseComplexOctet(str);
    }
}
