package postcode.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "postcode")
public class Postcode {

	@Id
	private String pcd;

	@Field(type = FieldType.Keyword)
	private String pcd2;

	@Field(type = FieldType.Keyword)
	private String pcds;

	@Field(type = FieldType.Keyword)
	private String dointr;

	@Field(type = FieldType.Keyword)
	private String doterm;

	@Field(type = FieldType.Keyword)
	private String oscty;

	@Field(type = FieldType.Keyword)
	private String ced;

	@Field(type = FieldType.Keyword)
	private String oslaua;

	@Field(type = FieldType.Keyword)
	private String osward;

	@Field(type = FieldType.Keyword)
	private String parish;

	public String getPcd() {
		return pcd;
	}

	public void setPcd(String pcd) {
		this.pcd = pcd;
	}

	public String getPcd2() {
		return pcd2;
	}

	public void setPcd2(String pcd2) {
		this.pcd2 = pcd2;
	}

	public String getPcds() {
		return pcds;
	}

	public void setPcds(String pcds) {
		this.pcds = pcds;
	}

	public String getDointr() {
		return dointr;
	}

	public void setDointr(String dointr) {
		this.dointr = dointr;
	}

	public String getDoterm() {
		return doterm;
	}

	public void setDoterm(String doterm) {
		this.doterm = doterm;
	}

	public String getOscty() {
		return oscty;
	}

	public void setOscty(String oscty) {
		this.oscty = oscty;
	}

	public String getCed() {
		return ced;
	}

	public void setCed(String ced) {
		this.ced = ced;
	}

	public String getOslaua() {
		return oslaua;
	}

	public void setOslaua(String oslaua) {
		this.oslaua = oslaua;
	}

	public String getOsward() {
		return osward;
	}

	public void setOsward(String osward) {
		this.osward = osward;
	}

	public String getParish() {
		return parish;
	}

	public void setParish(String parish) {
		this.parish = parish;
	}

}
