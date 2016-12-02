package com.ylink.modules.utils.export.common.constants;

/**
 * 枚举
 * 
 * @author 潘瑞峥
 * @date 2012-9-10
 */
public class ExportEnum {
	/**
	 * 文件类型
	 */
	public enum FileType {
		txt( "text/plain" ),
		
		xml( "text/xml" ),

		bpt( "text/plain" ),
		
		cer( "application/x-x509-ca-cert" ),

		jks( "application/octet-stream" ),
		
		csv( "application/octet-stream" ),

		undefined( "application/octet-stream" );

		private String contentType;

		private FileType( String contentType ) {
			this.contentType = contentType;
		}

		public String getContentType() {
			return contentType;
		}

	}

}