package postcode.service.model;

import postcode.documents.Parish;
import postcode.documents.Postcode;
import postcode.documents.Ward;

public class PostcodeDTO {
	private String postcode;
	private String wardName;
	private String parish;

	public PostcodeDTO(String postcode, String wardName, String parish) {
		this.postcode = postcode;
		this.wardName = wardName;
		this.parish = parish;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getWardName() {
		return wardName;
	}

	public String getParish() {
		return parish;
	}

	public static PostcodeDTO from(Postcode postcode, Ward ward, Parish parish) {
		return new PostcodeDTO(postcode.getPcd2(), ward.getWd22nm(), parish.getPARNCP21NM());
	}
}
