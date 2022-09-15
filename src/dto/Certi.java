package dto;

public class Certi {

	private int id;
	private int sort;
	private String certification;
	
	// 社員番号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// ソート
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	
	// 資格
	public String getCertification() {
		return certification;
	}
	public void setCertification(String certification) {
		this.certification = certification;
	}
}
