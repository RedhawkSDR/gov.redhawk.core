STARTMACRO u:host["localhost"] l:port[2809] u:resource["DomainName1/OSSIE::dfServerWave_1/dfSender"] u:port_name["output_port"] l:xfer_size[100000]
    PIPE INIT
		panel/setup/controls=gc/grid={title=test,nx=1,ny=1}

        corbareceiver _wave "^host" ^port "^resource" "^port_name" "SF" ^xfer_size
!        dataplot _wave "^host" ^port "^resource" "^port_name" "SF" ^xfer_size
!        datafloatplot _wave "^host" ^port "^resource" "^port_name" ^xfer_size
!        corbareceiver _wave "^host" ^port "^resource" "^port_name" ^xfer_size
		plot/id=plotDisplay/nice/all/focus=false/rt/lpb=16 _wave
    PIPE OFF
ENDMACRO

