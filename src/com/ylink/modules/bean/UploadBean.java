package com.ylink.modules.bean;

import java.io.File;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件上传Bean.
 * 
 * @author 潘瑞峥
 * @date 2012-11-1
 */
public class UploadBean {

	/** 上传的文件. */
	private File upload;

	/** 自定义标题. */
	private String caption;

	/** 文件名称. */
	private String fileName;

	/** 文件类型. */
	private String contentType;

	public File getUpload() {
		return upload;
	}

	public void setUpload( File upload ) {
		this.upload = upload;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption( String caption ) {
		this.caption = caption;
	}

	public String getUploadFileName() {
		return fileName;
	}

	public void setUploadFileName( String fileName ) {
		this.fileName = fileName;
	}

	public String getUploadContentType() {
		return contentType;
	}

	public void setUploadContentType( String contentType ) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString( this, ToStringStyle.MULTI_LINE_STYLE );
	}

}