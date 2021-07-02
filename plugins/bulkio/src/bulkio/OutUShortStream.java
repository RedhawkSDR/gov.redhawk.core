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
 * WARNING: This file is generated from InPort.java.template.
 *          Do not modify directly.
 */
package bulkio;

import org.apache.log4j.Logger;

import org.ossie.component.RHLogger;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import bulkio.OutStreamBase;
import bulkio.OutUShortPort;

import java.util.Arrays;

public class OutUShortStream extends OutStreamBase {

    protected OutUShortPort _port;

    public OutUShortStream(BULKIO.StreamSRI sri, OutUShortPort port) {
        super(sri);
        this._port = port;
        _bufferSize = 0;
        _bufferOffset = 0;
        _bufferWritePtr = 0;
        _bufferTime = null;
        _buffer = new short[0];
    };

    public int modcount() {
        return 0;
    };

    protected short[] _buffer;
    protected int _bufferSize;
    protected int _bufferOffset;
    protected int _bufferWritePtr;
    protected BULKIO.PrecisionUTCTime _bufferTime;

    /**
     * @brief  Gets the internal buffer size.
     * @returns  Number of real samples to buffer per push.
     * @pre  Stream is valid.
     *
     * The buffer size is in terms of real samples, ignoring the complex
     * mode of the stream. Complex samples count as two real samples for
     * the purposes of buffering.
     *
     * A buffer size of 0 indicates that buffering is disabled.
     */
    public int bufferSize() {
        return _bufferSize;
    };

    public void setBufferSize(int samples) {
        if (samples == _bufferSize) {
            return;
        }
        _bufferSize = samples;
        if (_bufferSize <= _bufferOffset) {
            this.flush();
        } else if (_bufferSize > _buffer.length) {
            _buffer = Arrays.copyOf(_buffer, _bufferSize);
        }
    };

    protected void _writeMultiple(short[] data, bulkio.SampleTimestamp[] times, boolean eos) {
        int offset_multiple = 1;
        if (this._sri.mode == 1) {
            // this is complex data
            offset_multiple = 2;
        }
        if (this.checkSRI()) {
            this._port.pushSRI(this._sri);
        }
        for (int times_idx = 0; times_idx < times.length; times_idx++) {
            if (times.length == 1) {
                this._port.pushPacket(data, times[0].time, eos, this._sri.streamID);
            } else {
                int end_offset = data.length;
                if (times_idx != times.length - 1) {
                    end_offset = times[times_idx+1].offset*offset_multiple;
                }
                if (times[times_idx].offset*offset_multiple >= data.length) {
                    continue;
                }
                if (end_offset > data.length) {
                    end_offset = data.length;
                }
                short[] _data = Arrays.copyOfRange(data, times[times_idx].offset*offset_multiple, end_offset);
                this._port.pushPacket(_data, times[times_idx].time, eos, this._sri.streamID);
            }
        }
    };

    protected void _writeMultiple(CF.complexUShort[] cx_data, bulkio.SampleTimestamp[] times, boolean eos) {
        if (this.checkSRI()) {
            this._port.pushSRI(this._sri);
        }
        short[] data = new short[cx_data.length*2];
        for (int cx_data_idx = 0; cx_data_idx < cx_data.length; cx_data_idx++) {
            data[cx_data_idx*2] = cx_data[cx_data_idx].real;
            data[cx_data_idx*2+1] = cx_data[cx_data_idx].imag;
        }
        // force data description to complex
        this._sri.mode = 1;
        for (int times_idx = 0; times_idx < times.length; times_idx++) {
            if (times.length == 1) {
                this._port.pushPacket(data, times[0].time, eos, this._sri.streamID);
            } else {
                int end_offset = data.length;
                if (times_idx != times.length - 1) {
                    end_offset = times[times_idx+1].offset*2;
                }
                if (times[times_idx].offset*2 >= data.length) {
                    continue;
                }
                if (end_offset > data.length) {
                    end_offset = data.length;
                }
                short[] _data = Arrays.copyOfRange(data, times[times_idx].offset*2, end_offset);
                this._port.pushPacket(_data, times[times_idx].time, eos, this._sri.streamID);
            }
        }
    };
    
    /**
     * @brief  Flushes the internal buffer.
     * @pre  Stream is valid.
     *
     * Any data in the internal buffer is sent to the port to be pushed.
     */
    public void flush() {
        this._flush(false);
    };

    protected void _flush(boolean eos) {
        bulkio.SampleTimestamp[] times = new SampleTimestamp[1];
        if (this._bufferTime != null) {
            times[0] = new bulkio.SampleTimestamp(this._bufferTime, 0, false);
            if (this._bufferSize != 0) {
                if ((this._bufferWritePtr != 0) || (eos)) {
                    short[] tmp_buffer = new short[this._bufferWritePtr];
                    for (int ii=0;ii<this._bufferWritePtr;ii++) {
                        tmp_buffer[ii]=this._buffer[ii];
                    }
                    this._writeMultiple(tmp_buffer, times, eos);
                }
            } else {
                if ((this._bufferOffset == 0) && (eos)) {
                    short[] tmp_buffer = new short[0];
                    this._writeMultiple(tmp_buffer, times, eos);
                } else if (this._bufferOffset != 0) {
                    short[] tmp_buffer = new short[this._bufferOffset];
                    for (int ii=0;ii<this._bufferOffset;ii++) {
                        tmp_buffer[ii]=this._buffer[ii];
                    }
                    this._writeMultiple(tmp_buffer, times, eos);
                }
            }
        }
        this._bufferOffset = 0;
        this._bufferWritePtr = 0;
        this._buffer = new short[this._bufferSize];
        this._bufferTime = null;
    };

    protected void _doBuffer(CF.complexUShort[] cx_data, BULKIO.PrecisionUTCTime time) {
        short[] data = new short[cx_data.length*2];
        for (int cx_data_idx = 0; cx_data_idx < cx_data.length; cx_data_idx++) {
            data[cx_data_idx*2] = cx_data[cx_data_idx].real;
            data[cx_data_idx*2+1] = cx_data[cx_data_idx].imag;
        }
        // force data description to complex
        this._sri.mode = 1;
        this._doBuffer(data, time);
    };

    protected void _doBuffer(short[] data, BULKIO.PrecisionUTCTime time) {
        // If this is the first data being queued, use its timestamp for the start
        // time of the buffered data
        if (this._bufferOffset == 0) {
            this._bufferTime = time;
        }

        // Only buffer up to the currently configured buffer size
        int count = Math.min(data.length, this._bufferSize - this._bufferOffset);
        for (int data_idx=0; data_idx<count; data_idx++) {
            _buffer[data_idx+this._bufferOffset] = data[data_idx];
        }

        // Advance buffer offset, flushing if the buffer is full
        this._bufferOffset += count;
        this._bufferWritePtr = this._bufferOffset;
        if (this._bufferOffset >= this._bufferSize) {
            this._bufferWritePtr = this._bufferSize;
            _flush(false);
        }

        // Handle remaining data
        if (count < data.length) {
            double synthetic_offset = _sri.xdelta * count;
            BULKIO.PrecisionUTCTime next = bulkio.time.utils.add(time, synthetic_offset);
            short [] recursive_data = Arrays.copyOfRange(data, count, data.length);
            this._doBuffer(recursive_data, next);
        }
    }

    /*
        * @brief  Write real sample data to the stream.
        * @param data  %shared_buffer containing real data.
        * @param time  Time stamp of first sample.

        * Sends the real data in @a data as single packet with the time stamp
        * @a time via the associated output port.
        *
        * If there are any pending SRI changes, the new SRI is pushed first.
        */
    public void write(short[] data, BULKIO.PrecisionUTCTime time) {
        if ((this._bufferSize == 0) || ((this._bufferWritePtr == 0) && (data.length >= this._bufferSize))) {
            //if (this._bufferSize == 0) {
            bulkio.SampleTimestamp[] times = new bulkio.SampleTimestamp[1];
            times[0] = new bulkio.SampleTimestamp(time, 0, false);
            this._writeMultiple(data, times, false);
            return;
        }
        this._doBuffer(data, time);
    };

    /**
     * @brief  Write real sample data to the stream.
     * @param data   %shared_buffer containing real data.
     * @param times  List of time stamps, with offsets.
     * @pre  Stream is valid.
     * @pre  @p times is sorted in order of offset.
     * @throw std::logic_error  If @p times is empty.
     *
     * Writes the real data in @a data to the stream, where each element of
     * @a times gives the offset and time stamp of an individual packet.
     * The offset of the first time stamp is ignored and assumed to be 0,
     * while subsequent offsets determine the length of the prior packet.
     * All offsets should be less than @a data.size().
     *
     * For example, given @a data with size 25 and three time stamps with
     * offsets 0, 10, and 20, @a data is broken into three packets of size
     * 10, 10, and 5 samples.
     *
     * If there are any pending SRI changes, the new SRI is pushed first.
     */
    public void write(short[] data, bulkio.SampleTimestamp[] times) {
        if ((this._bufferSize == 0) || ((this._bufferWritePtr == 0) && (data.length >= this._bufferSize))) {
        //if (this._bufferSize == 0) {
                this._writeMultiple(data, times, false);
            return;
        }
        for (int times_idx = 0; times_idx < times.length; times_idx++) {
            if (times.length == 1) {
                this._doBuffer(data, times[0].time);
            } else {
                int end_offset = data.length;
                if (times_idx != times.length) {
                    end_offset = times[times_idx+1].offset;
                }
                short[] _data = Arrays.copyOfRange(data, times[times_idx].offset, end_offset);
                this._doBuffer(_data, times[times_idx].time);
            }
        }
    };

    /**
     * @brief  Write complex sample data to the stream.
     * @param data  %shared_buffer containing complex data.
     * @param time  Time stamp of the first sample.
     * @throw std::logic_error  If stream is not configured for complex
     *                          data.
     *
     * Sends the complex data in @a data as single packet with the time
     * stamp @a time via the associated output port.
     *
     * If there are any pending SRI changes, the new SRI is pushed first.
     */
    public void write(CF.complexUShort[] data, BULKIO.PrecisionUTCTime time) {
        if ((this._bufferSize == 0) || ((this._bufferWritePtr == 0) && (data.length >= this._bufferSize))) {
        //if (this._bufferSize == 0) {
                bulkio.SampleTimestamp[] times = new SampleTimestamp[1];
            times[0] = new bulkio.SampleTimestamp(time, 0, false);
            this._writeMultiple(data, times, false);
            return;
        }
        this._doBuffer(data, time);
    };

    /**
     * @brief  Write complex data to the stream.
     * @param data   %shared_buffer containing complex data.
     * @param times  List of time stamps, with offsets.
     * @pre  Stream is valid.
     * @pre  @p times is sorted in order of offset.
     * @throw std::logic_error  If stream is not configured for complex
     *                          data.
     * @throw std::logic_error  If @p times is empty.
     *
     * Writes the complex data in @a data to the stream, where each element
     * of @a times gives the offset and time stamp of an individual packet.
     * The offset of the first time stamp is ignored and assumed to be 0,
     * while subsequent offsets determine the length of the prior packet.
     * All offsets should be less than @a data.size().
     *
     * For example, given @a data with size 25 and three time stamps with
     * offsets 0, 10, and 20, @a data is broken into three packets of size
     * 10, 10, and 5 samples.
     *
     * If there are any pending SRI changes, the new SRI is pushed first.
     */
    public void write(CF.complexUShort[] data, bulkio.SampleTimestamp[] times) {
        if ((this._bufferSize == 0) || ((this._bufferWritePtr == 0) && (data.length >= this._bufferSize))) {
        //if (this._bufferSize == 0) {
                this._writeMultiple(data, times, false);
            return;
        }
        for (int times_idx = 0; times_idx < times.length; times_idx++) {
            if (times.length == 1) {
                this._doBuffer(data, times[0].time);
            } else {
                int end_offset = data.length;
                if (times_idx != times.length) {
                    end_offset = times[times_idx+1].offset;
                }
                CF.complexUShort[] _data = Arrays.copyOfRange(data, times[times_idx].offset, end_offset);
                this._doBuffer(_data, times[times_idx].time);
            }
        }
    };

    public void close() {
        short [] data = new short[0];
        BULKIO.PrecisionUTCTime time = bulkio.time.utils.now();
        bulkio.SampleTimestamp[] times = new bulkio.SampleTimestamp[1];
        times[0] = new bulkio.SampleTimestamp(time, 0, false);
        this._writeMultiple(data, times, true);
    };
};
