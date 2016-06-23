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

import CF.complexShort;
import CF.complexShortSeqHelper;

public class ComplexShortSequenceProperty extends AbstractSequenceProperty<complexShort> {
    public ComplexShortSequenceProperty(String id, String name, List<complexShort> value, Mode mode,
                                         Action action, Kind[] kinds) {
        super(id, name, "short", value, mode, action, kinds);
    }

    public static List<complexShort> asList(complexShort... array) {
        return new ArrayList<complexShort>(Arrays.asList(array));
    }

    protected List<complexShort> extract(Any any) {
        complexShort[] array = complexShortSeqHelper.extract(any);
        List<complexShort> list = new ArrayList<complexShort>(array.length);
        for (complexShort item : array) {
            list.add(item);
        }
        return list;
    }

    protected void insert(Any any, List<complexShort> value) {
        complexShort[] array = value.toArray(new complexShort[value.size()]);
        complexShortSeqHelper.insert(any, array);
    }
}
