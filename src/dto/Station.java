package dto;

public class Station {

	private int id;
	private int sort;
	private String station;

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
	
	// 最寄り駅
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station = station;
	}
}
