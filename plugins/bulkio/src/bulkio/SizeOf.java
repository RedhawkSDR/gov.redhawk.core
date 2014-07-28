
package bulkio;


public class SizeOf <T>  {

    public <T extends Object> int sizeof() {
        return 0;
    }
}


class FloatSize extends SizeOf< Float > { 

    public FloatSize() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 4;
    }
}

class DoubleSize extends SizeOf< Double > { 

    public DoubleSize() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 8;
    }
}

class Int8Size extends SizeOf< Byte > { 

    public Int8Size() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 1;
    }
}

class CharSize extends SizeOf< Character > { 
    public CharSize() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 1;
    }
}

class UInt8Size extends Int8Size { 
    public UInt8Size() {};
}


class OctetSize extends Int8Size { 
    public OctetSize() {};
}


class Int16Size extends SizeOf< Short > { 

    public Int16Size() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 2;
    }
}


class UInt16Size extends Int16Size {
    public UInt16Size() { };
}


class Int32Size extends SizeOf< Integer > { 

    public Int32Size() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 4;
    }
}

class UInt32Size extends Int32Size {
    public UInt32Size() { };
}

class Int64Size extends SizeOf< Long > { 

    public Int64Size() {};
    public < T > int sizeof() {
        return bytes();
    };

    public static int bytes() {
        return 8;
    }
}

class UInt64Size extends Int64Size {
    public UInt64Size() { };
}
