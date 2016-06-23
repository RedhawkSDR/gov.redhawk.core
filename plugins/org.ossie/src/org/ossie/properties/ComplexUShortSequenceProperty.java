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
 * WARNING: This file is generated from ComplexSequenceProperty.template.
 *          Do not modify directly.
 */

package org.ossie.properties;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.omg.CORBA.Any;

import CF.complexUShort;
import CF.complexUShortSeqHelper;

public class ComplexUShortSequenceProperty extends AbstractSequenceProperty<complexUShort> {
    public ComplexUShortSequenceProperty(String id, String name, List<complexUShort> value, Mode mode,
                                         Action action, Kind[] kinds) {
        super(id, name, "ushort", value, mode, action, kinds);
    }

    public ComplexUShortSequenceProperty(String id, String name, List<complexUShort> value, Mode mode,
                                         Action action, Kind[] kinds, boolean optional) {
        super(id, name, "ushort", value, mode, action, kinds, optional);
    }

    public static List<complexUShort> asList(complexUShort... array) {
        return new ArrayList<complexUShort>(Arrays.asList(array));
    }

    protected List<complexUShort> extract(Any any) {
        complexUShort[] array = complexUShortSeqHelper.extract(any);
        List<complexUShort> list = new ArrayList<complexUShort>(array.length);
        for (complexUShort item : array) {
            list.add(item);
        }
        return list;
    }

    protected void insert(Any any, List<complexUShort> value) {
        complexUShort[] array = value.toArray(new complexUShort[value.size()]);
        complexUShortSeqHelper.insert(any, array);
    }
}
