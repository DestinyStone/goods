package com.coffee.entity.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装修改商品信息表单
 * 
 * @author K
 */
public class ModifyItemFormBean {
	private int itemId;
	private String name;
	private String type;
	private int stock;
	private double price;
	private String description;
	private String author;
	private String press;
	private String pressTime;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPressTime() {
		return pressTime;
	}

	public void setPressTime(String pressTime) {
		this.pressTime = pressTime;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStock() {
		return stock;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 存储校验不通过时给用户的错误提示信息
	 */
	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	/*
	 * 已在前端进行验证，这里暂不写验证,仅写确认密码验证
	 */
	/**
	 * 验证是否符合要求
	 */
	public boolean validate() {
		return true;
	}

	@Override
	public String toString() {
		return "ModifyItemFormBean [itemId=" + itemId + ",name=" + name + ",type=" + type + ",stock=" + stock
				+ ",price=" + price + ",description=" + description + "]";
	}
}
