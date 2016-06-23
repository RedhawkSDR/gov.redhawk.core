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

import CF.complexBoolean;
import CF.complexBooleanSeqHelper;

public class ComplexBooleanSequenceProperty extends AbstractSequenceProperty<complexBoolean> {
    public ComplexBooleanSequenceProperty(String id, String name, List<complexBoolean> value, Mode mode,
                                         Action action, Kind[] kinds) {
        super(id, name, "boolean", value, mode, action, kinds);
    }

    public ComplexBooleanSequenceProperty(String id, String name, List<complexBoolean> value, Mode mode,
                                         Action action, Kind[] kinds, boolean optional) {
        super(id, name, "boolean", value, mode, action, kinds, optional);
    }

    public static List<complexBoolean> asList(complexBoolean... array) {
        return new ArrayList<complexBoolean>(Arrays.asList(array));
    }

    protected List<complexBoolean> extract(Any any) {
        complexBoolean[] array = complexBooleanSeqHelper.extract(any);
        List<complexBoolean> list = new ArrayList<complexBoolean>(array.length);
        for (complexBoolean item : array) {
            list.add(item);
        }
        return list;
    }

    protected void insert(Any any, List<complexBoolean> value) {
        complexBoolean[] array = value.toArray(new complexBoolean[value.size()]);
        complexBooleanSeqHelper.insert(any, array);
    }
}
