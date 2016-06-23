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

import CF.complexOctet;
import CF.complexOctetSeqHelper;

public class ComplexOctetSequenceProperty extends AbstractSequenceProperty<complexOctet> {
    public ComplexOctetSequenceProperty(String id, String name, List<complexOctet> value, Mode mode,
                                         Action action, Kind[] kinds) {
        super(id, name, "octet", value, mode, action, kinds);
    }

    public static List<complexOctet> asList(complexOctet... array) {
        return new ArrayList<complexOctet>(Arrays.asList(array));
    }

    protected List<complexOctet> extract(Any any) {
        complexOctet[] array = complexOctetSeqHelper.extract(any);
        List<complexOctet> list = new ArrayList<complexOctet>(array.length);
        for (complexOctet item : array) {
            list.add(item);
        }
        return list;
    }

    protected void insert(Any any, List<complexOctet> value) {
        complexOctet[] array = value.toArray(new complexOctet[value.size()]);
        complexOctetSeqHelper.insert(any, array);
    }
}
