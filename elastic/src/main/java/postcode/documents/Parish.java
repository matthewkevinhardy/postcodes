package postcode.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "parish")
public class Parish {

	@Id
	@Field(name = "PARNCP21CD", type = FieldType.Keyword)
	private String parncp21cd;

	@Field(name = "PARNCP21NM", type = FieldType.Text)
	private String parncp21nm;

	public String getParncp21cd() {
		return parncp21cd;
	}

	public void setParncp21cd(String parncp21cd) {
		this.parncp21cd = parncp21cd;
	}

	public String getParncp21nm() {
		return parncp21nm;
	}

	public void setParncp21nm(String parncp21nm) {
		this.parncp21nm = parncp21nm;
	}

}
