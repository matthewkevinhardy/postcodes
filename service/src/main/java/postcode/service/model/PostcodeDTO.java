package postcode.service.model;

import postcode.documents.Postcode;
import postcode.documents.Ward;

public class PostcodeDTO {
	private String postcode;
	private String wardName;

	public PostcodeDTO(String postcode, String wardName) {
		this.postcode = postcode;
		this.wardName = wardName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public static PostcodeDTO from(Postcode postcode, Ward ward) {
		return new PostcodeDTO(postcode.getPcd2(), ward.getWd22nm());
	}
}
