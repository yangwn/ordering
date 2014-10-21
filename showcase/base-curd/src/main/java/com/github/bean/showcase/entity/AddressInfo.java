package com.github.bean.showcase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

@Entity
@Table(name = "address_info")
@SqlResultSetMapping(name = "addressInfo", entities = @EntityResult(entityClass = AddressInfo.class))
@NamedNativeQueries({@NamedNativeQuery(name = "GET_ADDR_BY_ID", query = "select id, customer_id, addr_info, receiver, receiver_cell from address_info where addr_info = :addrinfo", resultSetMapping = "addressInfo")})
public class AddressInfo extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "addr_info")
	private String addrInfo;

	@Column(name = "receiver")
	private String receiver;

	@Column(name = "receiver_cell")
	private String receiverCell;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAddrInfo() {
		return addrInfo;
	}

	public void setAddrInfo(String addrInfo) {
		this.addrInfo = addrInfo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverCell() {
		return receiverCell;
	}

	public void setReceiverCell(String receiverCell) {
		this.receiverCell = receiverCell;
	}

}
