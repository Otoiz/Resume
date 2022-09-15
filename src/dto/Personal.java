package dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Personal {

	private int id;
	private String name;
	private String sex;
	private String birthday;

	// 社員番号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// 氏名
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// 生年月日
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		
		this.birthday = birthday;
	}
	public int getAge() {
		int age;
		Calendar cl = Calendar.getInstance();
		
		//日付をyyyyMMddの形で出力する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String strToday = sdf.format(cl.getTime());
		
		int tyear = Integer.parseInt(strToday.substring(0,4));
		int tday = Integer.parseInt(strToday.substring(4,8));

		//birthday
		int byear = Integer.parseInt(birthday.substring(0,4));
		
		int bday = Integer.parseInt(birthday.substring(5,7)+birthday.substring(8,10));

		age = tyear - byear;
		
		if(tday < bday) {
			age--;
		}
		
		return age;
	}
	// 性別
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
