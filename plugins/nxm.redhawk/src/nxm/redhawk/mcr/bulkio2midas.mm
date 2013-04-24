STARTMACRO t:corbaargs t:fftargs t:thinargs s:output
    PIPE INIT
        corbareceiver/MSGID=MAIN/TLL=200/PS=^corbaargs.PIPESIZE/POLL=0.25 FILE=_CORBA_OUT HOST="^corbaargs.HOST" PORT=^corbaargs.PORT &
            RESOURCE=^corbaargs.RESOURCE PORT_NAME=^corbaargs.PORT_NAME IDL="^corbaargs.IDL" FORCE2000=^corbaargs.FORCE2000
        if fftargs isNULL then
            set fftout _CORBA_OUT
        else
            set fftout _FFT_OUT
            FFT/NEXP=^fftargs.numAvg _CORBA_OUT ^fftout NFFT=^fftargs.fftsize WIN=^fftargs.window OVER=^fftargs.over NAVG=2
        endif
        if thinargs isNULL then
            set thinout ^fftout
        else
            set thinout _DISPTHIN_OUT
            dispthin/PS=^{thinargs.pipesize}/TL=1 ^fftout ^thinout ^thinargs.refreshrate
        endif

        ! put output pipe into parent macro's PIPE/RAM results table so that PLOT can see it
        SET/PARENT RAM.^{output} RAM.^{thinout}
    PIPE OFF

    ! cleanup entry that we added in parent macro's PIPE/RAM results table
    REMOVE/PARENT RAM.^{output}
ENDMACRO

PROCEDURE processMessage m:msg
    ! forward messages (e.g. from corbareceiver) to registered message handler for this macro (requires NeXtMidas 3.3.1+)
    MESSAGE SEND THIS.MSGID msg
RETURN
