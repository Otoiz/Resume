package dto;

public class Career {
	
	
	private int id;
	private String startdate;
	private String enddate;
	
	private String sysname;
	private String sysdtl;
	private String roles;
	private String tools;
	
	private String credate;
	private String update;
	
	// 社員番号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	// 開始日
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	
	// 終了日
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	// システム名
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	
	// システム詳細
	public String getSysdtl() {
		return sysdtl;
	}
	public void setSysdtl(String sysdtl) {
		this.sysdtl = sysdtl;
	}
	
	// 役割
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	// ツール
	public String getTools() {
		return tools;
	}
	public void setTools(String tools) {
		this.tools = tools;
	}
	
	// 登録日
	public String getCredate() {
		return credate;
	}
	public void setCredate(String credate) {
		this.credate = credate;
	}
	
	// 更新日
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
}
