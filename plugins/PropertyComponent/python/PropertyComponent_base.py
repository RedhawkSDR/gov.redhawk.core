#!/usr/bin/env python
#
# AUTO-GENERATED CODE.  DO NOT MODIFY!
#
# Source: PropertyComponent.spd.xml
# Generated on: Tue Jul 17 11:08:16 EDT 2012
# Redhawk IDE
# Version:@buildLabel@
# Build id: @buildId@
from ossie.cf import CF, CF__POA
from ossie.utils import uuid

from ossie.resource import Resource
from ossie.properties import simple_property
from ossie.properties import simpleseq_property
from ossie.properties import struct_property
from ossie.properties import structseq_property

import Queue, copy, time, threading
from ossie.resource import usesport
from ossie.events import PropertyEventSupplier

NOOP = -1
NORMAL = 0
FINISH = 1
class ProcessThread(threading.Thread):
    def __init__(self, target, pause=0.0125):
        threading.Thread.__init__(self)
        self.setDaemon(True)
        self.target = target
        self.pause = pause
        self.stop_signal = threading.Event()

    def stop(self):
        self.stop_signal.set()

    def updatePause(self, pause):
        self.pause = pause

    def run(self):
        state = NORMAL
        while (state != FINISH) and (not self.stop_signal.isSet()):
            state = self.target()
            if (state == NOOP):
                # If there was no data to process sleep to avoid spinning
                time.sleep(self.pause)

class PropertyComponent_base(CF__POA.Resource, Resource):
        # These values can be altered in the __init__ of your derived class

        PAUSE = 0.0125 # The amount of time to sleep if process return NOOP
        TIMEOUT = 5.0 # The amount of time to wait for the process thread to die when stop() is called
        DEFAULT_QUEUE_SIZE = 100 # The number of BulkIO packets that can be in the queue before pushPacket will block
        
        def __init__(self, identifier, execparams):
            loggerName = (execparams['NAME_BINDING'].replace('/', '.')).rsplit("_", 1)[0]
            Resource.__init__(self, identifier, execparams, loggerName=loggerName)
            self.process_thread = None
            # self.auto_start is deprecated and is only kept for API compatability
            # with 1.7.X and 1.8.0 components.  This variable may be removed
            # in future releases
            self.auto_start = False
            
        def initialize(self):
            Resource.initialize(self)
            
            # Instantiate the default implementations for all ports on this component

            self.port_propEvent = PropertyEventSupplier(self)

        def start(self):
            Resource.start(self)
            if self.process_thread == None:
                self.process_thread = ProcessThread(target=self.process, pause=self.PAUSE)
                self.process_thread.start()

        def process(self):
            """The process method should process a single "chunk" of data and then return.  This method will be called
            from the processing thread again, and again, and again until it returns FINISH or stop() is called on the
            component.  If no work is performed, then return NOOP"""
            raise NotImplementedError

        def stop(self):
            # Technically not thread-safe but close enough for now
            process_thread = self.process_thread
            self.process_thread = None

            if process_thread != None:
                process_thread.stop()
                process_thread.join(self.TIMEOUT)
                if process_thread.isAlive():
                    raise CF.Resource.StopError(CF.CF_NOTSET, "Processing thread did not die")
            Resource.stop(self)

        def releaseObject(self):
            try:
                self.stop()
            except Exception:
                self._log.exception("Error stopping")
            Resource.releaseObject(self)

        ######################################################################
        # PORTS
        # 
        # DO NOT ADD NEW PORTS HERE.  You can add ports in your derived class, in the SCD xml file, 
        # or via the IDE.
        
        def compareSRI(self, a, b):
            if a.hversion != b.hversion:
                return False
            if a.xstart != b.xstart:
                return False
            if a.xdelta != b.xdelta:
                return False
            if a.xunits != b.xunits:
                return False
            if a.subsize != b.subsize:
                return False
            if a.ystart != b.ystart:
                return False
            if a.ydelta != b.ydelta:
                return False
            if a.yunits != b.yunits:
                return False
            if a.mode != b.mode:
                return False
            if a.streamID != b.streamID:
                return False
            if a.blocking != b.blocking:
                return False
            if len(a.keywords) != len(b.keywords):
                return False
            for keyA, keyB in zip(a.keywords, b.keywords):
                if keyA.value._t != keyB.value._t:
                    return False
                if keyA.value._v != keyB.value._v:
                    return False
            return True


        port_propEvent = usesport(name="propEvent",
                                            repid="IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0",
                                            type_="responses",)        

        ######################################################################
        # PROPERTIES
        # 
        # DO NOT ADD NEW PROPERTIES HERE.  You can add properties in your derived class, in the PRF xml file
        # or by using the IDE.       
        simplePropStr = simple_property(id_="simplePropStr",
                                          type_="string",
                                          defvalue="Hello",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure","event")
                                          )       
        simplePropBoolean = simple_property(id_="simplePropBoolean",
                                          type_="boolean",
                                          defvalue=True,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropUlong = simple_property(id_="simplePropUlong",
                                          type_="ulong",
                                          defvalue=1,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropObjRef = simple_property(id_="simplePropObjRef",
                                          type_="objref",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropShort = simple_property(id_="simplePropShort",
                                          type_="short",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropFloat = simple_property(id_="simplePropFloat",
                                          type_="float",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropOctet = simple_property(id_="simplePropOctet",
                                          type_="octet",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropChar = simple_property(id_="simplePropChar",
                                          type_="char",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropUShort = simple_property(id_="simplePropUShort",
                                          type_="ushort",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropDouble = simple_property(id_="simplePropDouble",
                                          type_="double",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropLong = simple_property(id_="simplePropLong",
                                          type_="long",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropLongLong = simple_property(id_="simplePropLongLong",
                                          type_="longlong",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simplePropUlonglong = simple_property(id_="simplePropUlonglong",
                                          type_="ulonglong",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simpleEnum = simple_property(id_="simpleEnum",
                                          type_="string",
                                          defvalue="hello",
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )       
        simpleReadOnly = simple_property(id_="simpleReadOnly",
                                          type_="string",
                                          defvalue="READ ONLY VALUE",
                                          mode="readonly",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropStr = simpleseq_property(id_="seqPropStr",  
                                          type_="string",
                                          defvalue=("test","test1","test3"),
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropBoolean = simpleseq_property(id_="seqPropBoolean",  
                                          type_="boolean",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropULong = simpleseq_property(id_="seqPropULong",  
                                          type_="ulong",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropObjRef = simpleseq_property(id_="seqPropObjRef",  
                                          type_="objref",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropShort = simpleseq_property(id_="seqPropShort",  
                                          type_="short",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropFloat = simpleseq_property(id_="seqPropFloat",  
                                          type_="float",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropOctet = simpleseq_property(id_="seqPropOctet",  
                                          type_="octet",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropChar = simpleseq_property(id_="seqPropChar",  
                                          type_="char",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropUShort = simpleseq_property(id_="seqPropUShort",  
                                          type_="ushort",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropDouble = simpleseq_property(id_="seqPropDouble",  
                                          type_="double",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropLong = simpleseq_property(id_="seqPropLong",  
                                          type_="long",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropLongLong = simpleseq_property(id_="seqPropLongLong",  
                                          type_="longlong",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          ) 
        seqPropulonglong = simpleseq_property(id_="seqPropulonglong",  
                                          type_="ulonglong",
                                          defvalue=None,
                                          mode="readwrite",
                                          action="external",
                                          kinds=("configure",)
                                          )
        class StructProp(object):
            structSimpleStr = simple_property(id_="structSimpleStr",
                                          type_="string",
                                          )
            structSimpleBoolean = simple_property(id_="structSimpleBoolean",
                                          type_="boolean",
                                          )
            structSimpleulong = simple_property(id_="structSimpleulong",
                                          type_="ulong",
                                          )
            structSimpleObjRef = simple_property(id_="structSimpleObjRef",
                                          type_="objref",
                                          )
            structSimpleShort = simple_property(id_="structSimpleShort",
                                          type_="short",
                                          )
            structSimpleFloat = simple_property(id_="structSimpleFloat",
                                          type_="float",
                                          )
            structSimpleOctet = simple_property(id_="structSimpleOctet",
                                          type_="octet",
                                          )
            structSimpleChar = simple_property(id_="structSimpleChar",
                                          type_="char",
                                          )
            structSimpleushort = simple_property(id_="structSimpleushort",
                                          type_="ushort",
                                          )
            structSimpledouble = simple_property(id_="structSimpledouble",
                                          type_="double",
                                          )
            structSimplelong = simple_property(id_="structSimplelong",
                                          type_="long",
                                          )
            structSimplelonglong = simple_property(id_="structSimplelonglong",
                                          type_="longlong",
                                          )
            structSimpleulonglong = simple_property(id_="structSimpleulonglong",
                                          type_="ulonglong",
                                          )
            structSimpleEnum = simple_property(id_="structSimpleEnum",
                                          type_="double",
                                          defvalue=1.5,
                                          )
        
            def __init__(self):
                """Construct an initialized instance of this struct definition"""
                for attrname, classattr in type(self).__dict__.items():
                    if type(classattr) == simple_property:
                        classattr.initialize(self)

            def __str__(self):
                """Return a string representation of this structure"""
                d = {}
                d["structSimpleStr"] = self.structSimpleStr
                d["structSimpleBoolean"] = self.structSimpleBoolean
                d["structSimpleulong"] = self.structSimpleulong
                d["structSimpleObjRef"] = self.structSimpleObjRef
                d["structSimpleShort"] = self.structSimpleShort
                d["structSimpleFloat"] = self.structSimpleFloat
                d["structSimpleOctet"] = self.structSimpleOctet
                d["structSimpleChar"] = self.structSimpleChar
                d["structSimpleushort"] = self.structSimpleushort
                d["structSimpledouble"] = self.structSimpledouble
                d["structSimplelong"] = self.structSimplelong
                d["structSimplelonglong"] = self.structSimplelonglong
                d["structSimpleulonglong"] = self.structSimpleulonglong
                d["structSimpleEnum"] = self.structSimpleEnum
                return str(d)

            def getId(self):
                return "structProp"

            def isStruct(self):
                return True

            def getMembers(self):
                return [("structSimpleStr",self.structSimpleStr),("structSimpleBoolean",self.structSimpleBoolean),("structSimpleulong",self.structSimpleulong),("structSimpleObjRef",self.structSimpleObjRef),("structSimpleShort",self.structSimpleShort),("structSimpleFloat",self.structSimpleFloat),("structSimpleOctet",self.structSimpleOctet),("structSimpleChar",self.structSimpleChar),("structSimpleushort",self.structSimpleushort),("structSimpledouble",self.structSimpledouble),("structSimplelong",self.structSimplelong),("structSimplelonglong",self.structSimplelonglong),("structSimpleulonglong",self.structSimpleulonglong),("structSimpleEnum",self.structSimpleEnum)]

        
        structProp = struct_property(id_="structProp",
                                          structdef=StructProp,
                                          configurationkind=("configure",),
                                          mode="readwrite"
                                          )
        class StructSeq(object):
            structSeqSimple = simple_property(id_="structSeqSimple",
                                          type_="string",
                                          )
            structSeqSimpleBoolean = simple_property(id_="structSeqSimpleBoolean",
                                          type_="boolean",
                                          )
            structSeqSimpleUlong = simple_property(id_="structSeqSimpleUlong",
                                          type_="ulong",
                                          )
            structSeqSimpleObjecRef = simple_property(id_="structSeqSimpleObjecRef",
                                          type_="objref",
                                          )
            structSeqSimpleShort = simple_property(id_="structSeqSimpleShort",
                                          type_="short",
                                          )
            structSeqFloat = simple_property(id_="structSeqFloat",
                                          type_="float",
                                          )
            structSeqSimpleOctet = simple_property(id_="structSeqSimpleOctet",
                                          type_="octet",
                                          )
            structSeqSimpleChar = simple_property(id_="structSeqSimpleChar",
                                          type_="char",
                                          )
            structSeqSimpleushort = simple_property(id_="structSeqSimpleushort",
                                          type_="ushort",
                                          )
            structSeqSimpleDouble = simple_property(id_="structSeqSimpleDouble",
                                          type_="double",
                                          )
            structSeqSimpleLong = simple_property(id_="structSeqSimpleLong",
                                          type_="long",
                                          )
            structSeqSimpleLongLong = simple_property(id_="structSeqSimpleLongLong",
                                          type_="longlong",
                                          )
            structSeqSimpleULongLong = simple_property(id_="structSeqSimpleULongLong",
                                          type_="ulonglong",
                                          )
            structSeqSimpleEnum = simple_property(id_="structSeqSimpleEnum",
                                          type_="string",
                                          defvalue="3",
                                          )
        
            def __init__(self, structSeqSimple="", structSeqSimpleBoolean=False, structSeqSimpleUlong=0, structSeqSimpleObjecRef="", structSeqSimpleShort=0, structSeqFloat=0, structSeqSimpleOctet=0, structSeqSimpleChar="", structSeqSimpleushort=0, structSeqSimpleDouble=0, structSeqSimpleLong=0, structSeqSimpleLongLong=0, structSeqSimpleULongLong=0, structSeqSimpleEnum=""):
                self.structSeqSimple = structSeqSimple
                self.structSeqSimpleBoolean = structSeqSimpleBoolean
                self.structSeqSimpleUlong = structSeqSimpleUlong
                self.structSeqSimpleObjecRef = structSeqSimpleObjecRef
                self.structSeqSimpleShort = structSeqSimpleShort
                self.structSeqFloat = structSeqFloat
                self.structSeqSimpleOctet = structSeqSimpleOctet
                self.structSeqSimpleChar = structSeqSimpleChar
                self.structSeqSimpleushort = structSeqSimpleushort
                self.structSeqSimpleDouble = structSeqSimpleDouble
                self.structSeqSimpleLong = structSeqSimpleLong
                self.structSeqSimpleLongLong = structSeqSimpleLongLong
                self.structSeqSimpleULongLong = structSeqSimpleULongLong
                self.structSeqSimpleEnum = structSeqSimpleEnum

            def __str__(self):
                """Return a string representation of this structure"""
                d = {}
                d["structSeqSimple"] = self.structSeqSimple
                d["structSeqSimpleBoolean"] = self.structSeqSimpleBoolean
                d["structSeqSimpleUlong"] = self.structSeqSimpleUlong
                d["structSeqSimpleObjecRef"] = self.structSeqSimpleObjecRef
                d["structSeqSimpleShort"] = self.structSeqSimpleShort
                d["structSeqFloat"] = self.structSeqFloat
                d["structSeqSimpleOctet"] = self.structSeqSimpleOctet
                d["structSeqSimpleChar"] = self.structSeqSimpleChar
                d["structSeqSimpleushort"] = self.structSeqSimpleushort
                d["structSeqSimpleDouble"] = self.structSeqSimpleDouble
                d["structSeqSimpleLong"] = self.structSeqSimpleLong
                d["structSeqSimpleLongLong"] = self.structSeqSimpleLongLong
                d["structSeqSimpleULongLong"] = self.structSeqSimpleULongLong
                d["structSeqSimpleEnum"] = self.structSeqSimpleEnum
                return str(d)

            def getId(self):
                return "structSeqStruct"

            def isStruct(self):
                return True

            def getMembers(self):
                return [("structSeqSimple",self.structSeqSimple),("structSeqSimpleBoolean",self.structSeqSimpleBoolean),("structSeqSimpleUlong",self.structSeqSimpleUlong),("structSeqSimpleObjecRef",self.structSeqSimpleObjecRef),("structSeqSimpleShort",self.structSeqSimpleShort),("structSeqFloat",self.structSeqFloat),("structSeqSimpleOctet",self.structSeqSimpleOctet),("structSeqSimpleChar",self.structSeqSimpleChar),("structSeqSimpleushort",self.structSeqSimpleushort),("structSeqSimpleDouble",self.structSeqSimpleDouble),("structSeqSimpleLong",self.structSeqSimpleLong),("structSeqSimpleLongLong",self.structSeqSimpleLongLong),("structSeqSimpleULongLong",self.structSeqSimpleULongLong),("structSeqSimpleEnum",self.structSeqSimpleEnum)]

                
        structSeq = structseq_property(id_="structSeq",
                                          structdef=StructSeq,                          
                                          defvalue=[],
                                          configurationkind=("configure",),
                                          mode="readwrite"
                                          )

'''uses port(s)'''

