package com.revature.pojos;

import java.util.Objects;

public class Request {
	private Integer reqId;
	public Integer userId;
	private String comment;
	private String reason;
	private double amount;
	private String approved;

	private String date;

	public Request() {
	}

	public Request(Integer userId, String comment, String reason, double amount, String approved) {
		this.reqId = reqId;
		this.userId = userId;
		this.comment = comment;
		this.reason = reason;
		this.amount = amount;
		this.approved = approved;
		this.date = "";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getReqId() {
		return reqId;
	}

	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String  getApproved() {
		return approved;
	}

	public void setApproved(String  approved) {
		this.approved = approved;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Request req = (Request) o;
		return Objects.equals(reqId, req.reqId) && Objects.equals(userId, req.userId) && Objects.equals(comment, req.comment)&& Objects.equals(reason, req.reason) && Objects.equals(amount, req.amount) && Objects.equals(approved, req.approved);
	}

	@Override
	public int hashCode() {
		return Objects.hash(reqId,userId,comment,reason,amount,approved);
	}

    @Override
    public String toString() {
        return "User{" +
                "requestId="+reqId+
                "userId=" + userId +
                ", comment='" + comment + '\'' +
                ", reason='"+ reason + '\''+
                ", amount=$" + amount +
                ", approved='" + approved + '\'' +
                '}';
    }



}