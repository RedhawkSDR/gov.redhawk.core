/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package nxm.redhawk.prim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nxm.sys.lib.Args.SplitArg;
import nxm.sys.lib.Data;
import nxm.sys.lib.DataFile;
import nxm.sys.lib.DataOp;
import nxm.sys.lib.Primitive;

/**
 * Takes in type 2000 frames, zeroing out all the data except specific bins.
 * @since 8.0
 */
public class fftfilter extends Primitive { //SUPPRESS CHECKSTYLE ClassName
	
	private DataFile inputFile = null;
	private DataFile outputFiltered = null;
	
	private Data data;
	
	private List<Range> displayRanges = new ArrayList<Range>();
	
	@Override
	public int open() {
		// Get input
		inputFile = MA.getDataFile("INPUT", "2000", "S#,C#", DataFile.INPUT);
		inputFile.open();
		
		// Get input params
		String format = inputFile.getFormat();
		
		// Open output
		outputFiltered = MA.getDataFile("FILTER", "2000", format, DataFile.OUTPUT);
		outputFiltered.propagate(inputFile, DataFile.PM_DEFAULT);
		outputFiltered.open();
		
		// Setup data buffers
		data = inputFile.getDataBuffer(1);
		
		SplitArg initialRange = MA.getSplitArg(MA.get("RANGES"), '|');
		for (int i = 0; i < initialRange.getFields(); i++) {
			String arg = initialRange.getField(i);
			String[] rangeStrs = arg.split("-", 2);
			if (rangeStrs.length != 2) {
				continue;
			}
			
			try {
			    int start = Integer.parseInt(rangeStrs[0]);
			    int end = Integer.parseInt(rangeStrs[1]);
			    addSelection(start, end);
			} catch (NumberFormatException e) {
				continue;
			}
		}
		return NORMAL;
	}
	
	@Override
	public int process() {
		int elemread = inputFile.read(data);
		if (elemread == 0) {
			return NOOP;
		}
		
		if (elemread < 0) {
			return FINISH;
		}
		
		if (displayRanges.size() == 0) {
			DataOp.zero(data, 0, data.ape);
		} else {
			int start = 0;
			int end = 0;
			for (Range range : displayRanges) {
				end = Math.min(range.start, data.ape);
				if (start < end) {
					DataOp.zero(data, start, end - start);
				}
				start = range.end + 1;
			}
			if (start < data.ape) {
				DataOp.zero(data, start, data.ape - start);
			}
		}
		
		outputFiltered.write(data);
		return NORMAL;
	}

	@Override
	public int close() {
	    if (inputFile != null && inputFile.isOpen()) {
	    	inputFile.close();
	    }
	    if (outputFiltered != null && outputFiltered.isOpen()) {
	    	outputFiltered.close();
	    }
	    return NORMAL;
	}
	
	/**
	 * Add a new range of elements to keep
	 * 
	 * @param start The starting bin (inclusive)
	 * @param end The ending bin (inclusive)
	 */
	public void addSelection(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("End bin must be >= start bin");
		} else if (start < 0 || end < 0) {
			throw new IllegalArgumentException("Start and end bins cannot be negative");
		}
		List<fftfilter.Range> displayRangesCopy = new ArrayList<fftfilter.Range>(displayRanges);
		displayRangesCopy.add(new Range(start, end));
		Collections.sort(displayRangesCopy);
		displayRanges = displayRangesCopy;
	}
	
	/**
	 * Clears all selections (all elements will be zeroed in outputed frames)
	 */
	public void clearSelections() {
		displayRanges = new ArrayList<fftfilter.Range>();
	}
	
	private class Range implements Comparable<Range> {
		
		private int start;
		private int end;
		
		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}

        public int compareTo(Range o) {
			if (start != o.start) {
				return start - o.start;
			} else {
				return end - o.end;
			}
        }
	}
	
}
