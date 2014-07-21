package com.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GooglePlacesModel {
	@SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("next_page_token")
    private String next_page_token;

    @SerializedName("results")
    private List<Results> results;

    @SerializedName("status")
    private String status;

	public List<String> getHtml_attributions() {
		return html_attributions;
	}

	public void setHtml_attributions(List<String> html_attributions) {
		this.html_attributions = html_attributions;
	}

	public String getNext_page_token() {
		return next_page_token;
	}

	public void setNext_page_token(String next_page_token) {
		this.next_page_token = next_page_token;
	}

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
