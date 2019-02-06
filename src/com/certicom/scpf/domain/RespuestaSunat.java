package com.certicom.scpf.domain;
import java.util.Date;

public class RespuestaSunat {
	private String ReferenceID;
	private Integer ResponseCode;
	private String Description;
	private String DocumentReference;
	private String ID;
	private Date IssueDate;
	private Date IssueTime;
	private Date ResponseDate;
	private Date ResponseTime;
	
	
	public String getReferenceID() {
		return ReferenceID;
	}
	public void setReferenceID(String referenceID) {
		ReferenceID = referenceID;
	}
	public Integer getResponseCode() {
		return ResponseCode;
	}
	public void setResponseCode(Integer responseCode) {
		ResponseCode = responseCode;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDocumentReference() {
		return DocumentReference;
	}
	public void setDocumentReference(String documentReference) {
		DocumentReference = documentReference;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public Date getIssueDate() {
		return IssueDate;
	}
	public void setIssueDate(Date issueDate) {
		IssueDate = issueDate;
	}
	public Date getIssueTime() {
		return IssueTime;
	}
	public void setIssueTime(Date issueTime) {
		IssueTime = issueTime;
	}
	public Date getResponseDate() {
		return ResponseDate;
	}
	public void setResponseDate(Date responseDate) {
		ResponseDate = responseDate;
	}
	public Date getResponseTime() {
		return ResponseTime;
	}
	public void setResponseTime(Date responseTime) {
		ResponseTime = responseTime;
	}
	
	
}
