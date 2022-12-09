package ch07;

import java.time.LocalDate;

public class Player {
	private int backNo;
	private String pname;
	private Position position;
	private LocalDate birthDay;
	private int height;
	private int isDeleted;
	
	Player() {}
	
	Player(int backNo, String pname, String position, String birthDay, int height) {
		this.backNo = backNo;
		this.pname = pname;
		this.position = Position.valueOf(position);
		this.birthDay = LocalDate.parse(birthDay);
		this.height = height;
		this.isDeleted = isDeleted;
	}
	
	Player(int backNo, String pname, String position, String birthDay, int height, int isDeleted) {
		this.backNo = backNo;
		this.pname = pname;
		this.position = Position.valueOf(position);
		this.birthDay = LocalDate.parse(birthDay);
		this.height = height;
		this.isDeleted = isDeleted;
	}
	Player(int backNo, String pname, Position position, LocalDate birthDay, int height, int isDeleted) {
		this.backNo = backNo;
		this.pname = pname;
		this.position = position;
		this.birthDay = birthDay;
		this.height = height;
		this.isDeleted = isDeleted;
	}
	
	@Override
	public String toString() {
		return "Player [" + backNo + ", " + pname + ", " + position + ", " + birthDay
				+ ", " + height+ ", " + isDeleted + "]";
	}
	public int getBackNo() {
		return backNo;
	}
	public void setBackNo(int backNo) {
		this.backNo = backNo;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String name) {
		this.pname = pname;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
}