package gov.redhawk.ui.port.nxmplot;

/**
 * @since 3.0
 */
public class MulticastSource {

	public String payload;
	public String mcastAddress;
	public int vlan;
	public int port;
	public String format;
	public String mode;
	public Object frameSize;

	public MulticastSource(
			final String payload,
			final String mcastAddress,
			final int vlan,
			final int port,
			final String format,
			final String mode,
			final int frameSize) {
		this.payload = payload;
		this.mcastAddress = mcastAddress;
		this.vlan = vlan;
		this.port = port;
		this.format = format;
		this.mode = mode;
		this.frameSize = frameSize;
	}
}
