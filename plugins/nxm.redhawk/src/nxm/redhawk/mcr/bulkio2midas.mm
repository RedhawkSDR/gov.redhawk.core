STARTMACRO t:corbaargs t:fftargs t:thinargs s:output
    PIPE INIT
        corbareceiver/MSGID=MAIN/TLL=200/PS=^corbaargs.PIPESIZE/POLL=0.1 FILE=_CORBA_OUT HOST="^corbaargs.HOST" PORT=^corbaargs.PORT &
            FRAMESIZE=^corbaargs.FRAMESIZE OVERRIDE_SRI_SUBSIZE=^corbaargs.OVERRIDE_SRI_SUBSIZE &
            RESOURCE=^corbaargs.RESOURCE PORT_NAME=^corbaargs.PORT_NAME IDL="^corbaargs.IDL"
        if fftargs isNULL then
            set fftout _CORBA_OUT
        else
            set fftout _FFT_OUT
            if fftargs.switches NREXISTS then
              set fftargs.switches ""
            elseif fftargs.switches.startsWith("/") isFalse then
              warn "ignoring invalid FFT switches: ^{fftargs.switches}"
              set fftargs.switches ""
            endif
            FFT/NEXP=^{fftargs.numAvg}^{fftargs.switches} _CORBA_OUT ^fftout NFFT=^fftargs.fftsize WIN=^fftargs.window OVER=^fftargs.over NAVG=2
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
    if msg.name eqs "CHANGE_SETTINGS" then
      ! info "DEBUG: Got CHANGE_SETTINGS msg, data = ^msg.data"
      foreach cmdID in msg.data
        set settingsTbl msg.data.^{cmdID}
        if reg.^{cmdID} rexists then
          foreach property in settingsTbl
            set value settingsTbl.^{property}
            set reg.^{cmdID}.^{property} value
          endfor
        else
          warn "Unable to change settings for non-existent command=^{cmdID} value=^{settingsTbl}"
        endif
      endfor

    elseif msg.name eqs "CHANGE_CORBARECEIVER_SETTINGS" then
      ! TODO: remove this if block after verifying that the above block works
      ! info "DEBUG: Got CHANGE_CORBARECEIVER_SETTINGS msg, data = ^msg.data"
      foreach key in msg.data
        set value msg.data.^{key}
        set reg.corbareceiver.^{key} value
      endfor
      
    else
      ! forward messages (e.g. from CORBARECEIVER) to registered message handler for this macro (requires NeXtMidas 3.3.1+)
      MESSAGE SEND THIS.MSGID msg
    endif
RETURN
