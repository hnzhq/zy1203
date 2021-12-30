package com.st.model;

import java.util.Date;

public class Goods {
	private Integer id;
	private String goodsId;// 商品编号
	private String name;// 商品名
	private String unit;// 单位
	private Integer inNum;// 进货数量
	private Integer outNum;// 出货数量
	private Float price;// 单价
	private String place;// 产地
	private String staff;// 销售员工
	private Date time;// 销售的时间
	private String isArrival;// 商品是否到货

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getInNum() {
		return inNum;
	}

	public void setInNum(Integer inNum) {
		this.inNum = inNum;
	}

	public Integer getOutNum() {
		return outNum;
	}

	public void setOutNum(Integer outNum) {
		this.outNum = outNum;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date date) {
		this.time = date;
	}

	public String getIsArrival() {
		return isArrival;
	}

	public void setIsArrival(String isArrival) {
		this.isArrival = isArrival;
	}

}
