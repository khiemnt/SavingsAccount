package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tu
 * Date: 3/6/13
 * Time: 10:55 AM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "DynamicSearchRequest")
public class DynamicSearchRequest {


    @XmlElement(required = true)
    private Integer dealerId;

	@XmlElement(name = "requestList", required = true)
	private List<Request> requestList;

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

	public List<Request> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<Request> requestList) {
		this.requestList = requestList;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	@XmlRootElement(name = "Request")
	public static class Request {
		@XmlElement(required = true)
		private String attribute;
		@XmlElement(required = true)
		private String operator;
		@XmlElement(required = true)
		private String value;

		public String getAttribute() {
			return attribute;
		}

		public void setAttribute(String attribute) {
			this.attribute = attribute;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
