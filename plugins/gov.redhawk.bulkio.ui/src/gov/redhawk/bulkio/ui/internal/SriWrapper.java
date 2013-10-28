package gov.redhawk.bulkio.ui.internal;

import java.util.Date;

import org.eclipse.jdt.annotation.NonNull;

import BULKIO.StreamSRI;

public class SriWrapper {

	private StreamSRI sri;
	private Date date;
	private String precisionTime;
	private boolean EOS;

	public SriWrapper(@NonNull StreamSRI streamSRI, @NonNull Date date) {
		this.setSri(streamSRI);
		this.setDate(date);
	}

	public SriWrapper(SriWrapper value) {
		this.sri = value.getSri();
		this.date = value.getDate();
		this.precisionTime = value.getPrecisionTime();
		this.EOS = value.isEOS();
	}

	@NonNull
	public StreamSRI getSri() {
		return sri;
	}

	public void setSri(StreamSRI sri) {
		this.sri = sri;
	}

	@NonNull
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@NonNull
	public String getPrecisionTime() {
		return precisionTime;
	}

	public void setPrecisionTime(String precisionTime) {
		this.precisionTime = precisionTime;
	}

	public boolean isEOS() {
		return EOS;
	}

	public void setEOS(boolean EOS) {
		this.EOS = EOS;
	}
	
}
