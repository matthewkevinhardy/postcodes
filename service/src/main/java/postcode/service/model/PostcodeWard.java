package postcode.service.model;

import postcode.documents.Postcode;
import postcode.documents.Ward;

public class PostcodeWard {
	private Postcode postcode;
	private Ward ward;

	public PostcodeWard(Postcode postcode, Ward ward) {
		super();
		this.postcode = postcode;
		this.ward = ward;
	}

	public Postcode getPostcode() {
		return postcode;
	}

	public void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

}
