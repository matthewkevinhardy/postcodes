package postcode.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "parish")
public class Parish {
	@Id
	private String PARNCP21CD;

	private String PARNCP21NM;

	public String getPARNCP21CD() {
		return PARNCP21CD;
	}

	public void setPARNCP21CD(String pARNCP21CD) {
		PARNCP21CD = pARNCP21CD;
	}

	public String getPARNCP21NM() {
		return PARNCP21NM;
	}

	public void setPARNCP21NM(String pARNCP21NM) {
		PARNCP21NM = pARNCP21NM;
	}

}
