package com.discorp.staff.xml;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Tu
 * Date: 3/7/13
 * Time: 2:36 PM
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "DynamicSearchResponse")
public class DynamicSearchResponse {
	@XmlElement(name = "customerInfoList", required = true)
	private List<CustomerInfo> customerInfoList;

	public List<CustomerInfo> getCustomerInfoList() {
		return customerInfoList;
	}

	public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "")
	@XmlRootElement(name = "CustomerInfo")
	public static class CustomerInfo{
		@XmlElement(required = true)
		private String firstName;
		@XmlElement(required = true)
		private String lastName;
		@XmlElement(required = true)
		private String city;
		@XmlElement(required = true)
		private String email;
		@XmlElement(required = true)
		private String phoneNumber;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
	}
}
