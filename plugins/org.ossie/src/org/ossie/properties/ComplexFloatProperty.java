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

import CF.complexFloat;
import CF.complexFloatHelper;

public class ComplexFloatProperty extends AbstractSimpleProperty<complexFloat> {
    public ComplexFloatProperty(String id, String name, complexFloat value, Mode mode, Action action, Kind[] kinds) {
        super(id, name, "float", value, mode, action, kinds);
    }

    public ComplexFloatProperty(String id, String name, complexFloat value, Mode mode, Action action, Kind[] kinds, boolean optional) {
        super(id, name, "float", value, mode, action, kinds, optional);
    }

    protected complexFloat extract(Any any) {
        try {
            return (complexFloat)AnyUtils.convertAny(any);
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException("Incorrect any type recevied");
        }
    }

    protected void insert(Any any, complexFloat value) {
        complexFloatHelper.insert(any, value);
    }

    protected complexFloat parseString(String str) {
        return ComplexUtils.parseComplexFloat(str);
    }
}
