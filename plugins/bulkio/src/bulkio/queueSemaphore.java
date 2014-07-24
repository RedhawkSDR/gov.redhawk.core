package bulkio;
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

import java.util.concurrent.locks.*;

class queueSemaphore
{
    private   int           maxValue;
    private   int           currValue;
    private   Lock          mutex = new ReentrantLock(); 
    private   Condition     condition = mutex.newCondition();

    public queueSemaphore( int initialMaxValue) {
            maxValue = initialMaxValue;
    }

    public void release() {
	  currValue=0;
	  condition.signalAll();
    }

    public void setMaxValue( int newMaxValue) {
	mutex.lock();
	try {
	    maxValue = newMaxValue;
	}
	finally {
	    mutex.unlock();
	}
    }

    public int getMaxValue() {
	mutex.lock();
	try {
	    return maxValue;
	}
	finally {
	    mutex.unlock();
	}
    }

    public void setCurrValue( int newValue) {
	mutex.lock();
	try {
            if (newValue < maxValue) {
                int oldValue = currValue;
                currValue = newValue;

                if (oldValue > newValue) {
                    condition.signal();
                }
            }

	}
	finally {
	    mutex.unlock();
	}
    }

    public void incr() {
	mutex.lock();
	try {
            while (currValue >= maxValue) {
		try {
		    condition.await();
		}catch( InterruptedException e) {};
		
            }
            ++currValue;
	}
	finally {
	    mutex.unlock();
	}
    }

    public void decr() {
	mutex.lock();
	try {
            if (currValue > 0) {
                --currValue;
            }
            condition.signal();
	}
	finally {
	    mutex.unlock();
	}
    }

};
