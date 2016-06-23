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

import CF.complexLongLong;
import CF.complexLongLongSeqHelper;

public class ComplexLongLongSequenceProperty extends AbstractSequenceProperty<complexLongLong> {
    public ComplexLongLongSequenceProperty(String id, String name, List<complexLongLong> value, Mode mode,
                                         Action action, Kind[] kinds) {
        super(id, name, "longlong", value, mode, action, kinds);
    }

    public static List<complexLongLong> asList(complexLongLong... array) {
        return new ArrayList<complexLongLong>(Arrays.asList(array));
    }

    protected List<complexLongLong> extract(Any any) {
        complexLongLong[] array = complexLongLongSeqHelper.extract(any);
        List<complexLongLong> list = new ArrayList<complexLongLong>(array.length);
        for (complexLongLong item : array) {
            list.add(item);
        }
        return list;
    }

    protected void insert(Any any, List<complexLongLong> value) {
        complexLongLong[] array = value.toArray(new complexLongLong[value.size()]);
        complexLongLongSeqHelper.insert(any, array);
    }
}
