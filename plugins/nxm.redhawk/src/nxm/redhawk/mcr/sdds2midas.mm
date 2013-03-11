STARTMACRO t:sddsargs t:fftargs t:thinargs s:output
	switch "PS" Pipesize get def=16m 
    PIPE INIT
        SOURCENIC/sddsargs.switches _sddsout
        if fftargs NisNULL then 
          fft _sddsout _thinIn nfft=fftargs.fftsize win=fftargs.window over=fftargs.over navg=fftargs.numAvg
          dispthin/ps=Pipesize/tl=1 _thinIn output thinargs.refreshrate
        else  
          dispthin/ps=Pipesize/tl=1 _sddsout output thinargs.refreshrate
        endif
        
        ! put output pipe into parent macro's PIPE table (aka RAM table)
        set/parent ram.^{output} ram.^{output}
	PIPE OFF
	
	remove/parent ram.^{output}
ENDMACRO