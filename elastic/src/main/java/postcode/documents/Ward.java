package postcode.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "ward")
public class Ward {
	@Id
	@Field(name = "WD22CD", type = FieldType.Keyword)
	private String wd22cd;

	@Field(name = "WD22NM", type = FieldType.Text)
	private String wd22nm;

	public String getWd22cd() {
		return wd22cd;
	}

	public void setWd22cd(String wd22cd) {
		this.wd22cd = wd22cd;
	}

	public String getWd22nm() {
		return wd22nm;
	}

	public void setWd22nm(String wd22nm) {
		this.wd22nm = wd22nm;
	}

}
