STARTMACRO t:corbaargs t:fftargs t:thinargs s:output
	switch MSGID_TMP msgHandlerID get def=null
    PIPE INIT
        corbareceiver/MSGID=MAIN/TLL=200/PS=corbaargs.PIPESIZE FILE=_corbaOut HOST="^corbaargs.HOST" PORT=corbaargs.PORT RESOURCE=corbaargs.RESORUCE PORT_NAME=^corbaargs.PORT_NAME IDL="^corbaargs.IDL" FORCE2000=^corbaargs.FORCE2000
        if thinargs isNULL then
        	if fftargs isNULL then 
	           ! put output pipe into parent macro's PIPE table (aka RAM table)
	        	set/parent ram.^{output} ram._corbaOut
	        else  
	           fft _corbaOut _thinIn nfft=fftargs.fftsize win=fftargs.window over=fftargs.over navg=fftargs.numAvg
	           ! put output pipe into parent macro's PIPE table (aka RAM table)
	        	set/parent ram.^{output} ram._thinIn
	        endif 
        else
	        if fftargs isNULL then 
	           dispthin/ps=thinargs.pipesize/tl=1 _corbaOut _dispout thinargs.refreshrate
	        else  
	           fft _corbaOut _thinIn nfft=fftargs.fftsize win=fftargs.window over=fftargs.over navg=fftargs.numAvg
	           dispthin/ps=thinargs.pipesize/tl=1 _thinIn _dispout thinargs.refreshrate
	        endif
	         
	        ! put output pipe into parent macro's PIPE table (aka RAM table)
	        set/parent ram.^{output} ram._dispout
        endif
        !plot _dispout
    PIPE OFF
    
    remove/parent ram.^{output}
ENDMACRO

PROCEDURE processMessage m:msg
	if msgHandlerID nisnull then
		!say "Forwarind msg: ^msg"
		message send PARENT.^{msgHandlerID} msg
	endif
RETURN
