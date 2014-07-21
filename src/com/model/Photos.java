package com.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Photos {
	@SerializedName("height")
    private int height;

    @SerializedName("width")
    private int width;

    @SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("photo_reference")
    private String photo_reference;

	public List<String> getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(List<String> html_attributions) {
		this.html_attributions = html_attributions;
	}

	public String getPhoto_reference() {
		return photo_reference;
	}

	public void setPhoto_reference(String photo_reference) {
		this.photo_reference = photo_reference;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
