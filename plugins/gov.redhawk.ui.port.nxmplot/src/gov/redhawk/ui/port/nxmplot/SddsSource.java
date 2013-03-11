package gov.redhawk.ui.port.nxmplot;

/**
 * @since 3.0
 */
public class SddsSource {

	public String mcastAddress;
	public int port;
	public int vlan;
	public String format;

	public SddsSource (String mcastAddress, final int port, final int vlan, final String format){
		this.mcastAddress = mcastAddress;
		this.port = port;
		this.vlan = vlan;
		this.format = format;
	}
}
