package postcode.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "lanames")
public class LaUaName {
	@Id
	@Field(name = "LAD23CD", type = FieldType.Keyword)
	private String LAD23CD;

	@Field(name = "LAD23NM", type = FieldType.Text)
	private String LAD23NM;

	@Field(name = "LAD23NMW", type = FieldType.Text)
	private String LAD23NMW;

	public String getLAD23CD() {
		return LAD23CD;
	}

	public void setLAD23CD(String lAD23CD) {
		LAD23CD = lAD23CD;
	}

	public String getLAD23NM() {
		return LAD23NM;
	}

	public void setLAD23NM(String lAD23NM) {
		LAD23NM = lAD23NM;
	}

	public String getLAD23NMW() {
		return LAD23NMW;
	}

	public void setLAD23NMW(String lAD23NMW) {
		LAD23NMW = lAD23NMW;
	}

}
