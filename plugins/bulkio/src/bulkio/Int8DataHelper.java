/*
 * This file is protected by Copyright. Please refer to the COPYRIGHT file
 * distributed with this source distribution.
 *
 * This file is part of REDHAWK bulkioInterfaces.
 *
 * REDHAWK bulkioInterfaces is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * REDHAWK bulkioInterfaces is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
/*
 * WARNING: This file is generated from NumericDataHelper.java.template.
 *          Do not modify directly.
 */
package bulkio;

import java.util.Arrays;

class Int8DataHelper implements DataHelper<char[]> {
    public int elementSize() {
        return 1;
    }

    public int bitSize() {
        return 1 * 8;
    }

    public int arraySize(char[] data) {
        return data.length;
    }

    public char[] emptyArray() {
        return new char[0];
    }

    public char[] slice(char[] data, int start, int end) {
        return Arrays.copyOfRange(data, start, end);
    }
}
