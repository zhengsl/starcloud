package com.star.cloud.domain.perf;

public class ContentQuality extends Performance {
	
	private final float psnr;
	
	private final float bitrate;
	
	private final float framerate;
	
	private final int resolutionH;
	
	private final int resolutionV;
	
	private final String vEncoder;
	
	private final String aEncoder;
	
	private final String packageFormat;
	
	public ContentQuality(float psnr, float bitrate, float framerate,
			int resolutionH, int resolutionV, String vEncoder, String aEncoder,
			String packageFormat) {
		super();
		this.psnr = psnr;
		this.bitrate = bitrate;
		this.framerate = framerate;
		this.resolutionH = resolutionH;
		this.resolutionV = resolutionV;
		this.vEncoder = vEncoder;
		this.aEncoder = aEncoder;
		this.packageFormat = packageFormat;
	}

	public float getPsnr() {
		return psnr;
	}

	public float getBitrate() {
		return bitrate;
	}

	public float getFramerate() {
		return framerate;
	}

	public int getResolutionH() {
		return resolutionH;
	}

	public int getResolutionV() {
		return resolutionV;
	}

	public String getvEncoder() {
		return vEncoder;
	}

	public String getaEncoder() {
		return aEncoder;
	}

	public String getPackageFormat() {
		return packageFormat;
	}

	@Override
	public boolean isSatisfied(Performance perf) {
		assert(perf != null && perf instanceof ContentQuality);
		
		ContentQuality cq = (ContentQuality) perf;
		return this.psnr <= cq.getPsnr();
	}
	
}
