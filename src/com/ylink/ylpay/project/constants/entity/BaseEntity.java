package com.ylink.ylpay.project.constants.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import com.google.code.lightssh.common.entity.Persistence;

@MappedSuperclass
public abstract class BaseEntity implements Persistence<String> {

	private static final long serialVersionUID = -251977881761280487L;

	@Id
	@GeneratedValue( generator = "system-uuid" )
	@GenericGenerator( name = "system-uuid", strategy = "uuid" )
	@Column( name = "ID" )
	protected String identity;

	@Override
	public String getIdentity() {
		return identity;
	}

	public void setIdentity( String identity ) {
		this.identity = identity;
	}

	@Override
	public boolean isInsert() {
		return false;
	}

	@Override
	public void postInsertFailure() {
	}

	@Override
	public void preInsert() {
	}

}