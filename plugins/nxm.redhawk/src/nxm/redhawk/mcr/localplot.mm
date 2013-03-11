STARTMACRO t:plotargs
	PIPE ON
		plot/^plotargs.switches/MSGID=MAIN/exit=NONE ^{plotargs.sources} setup=plotargs.args
	PIPE OFF
ENDMACRO 