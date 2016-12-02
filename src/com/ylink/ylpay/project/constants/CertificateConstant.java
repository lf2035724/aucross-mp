package com.ylink.ylpay.project.constants;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.ylink.modules.utils.export.common.constants.ExportEnum.FileType;

/**
 * 数字证书常量.
 * 
 * @author 潘瑞峥
 * @date 2013-4-19
 */
public final class CertificateConstant {

	/** 签名算法. */
	public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	/** 证书算法. */
	public static final String CERTIFICATE_ALGORITHM = "RSA";

	/** 证书类型. */
	public static final String CERTIFICATE_TYPE = "X.509";

	/** 私钥jks. */
	public static final String JKS_TYPE = FileType.jks.name();

	/** 公钥cer. */
	public static final String CER_TYPE = FileType.cer.name();

	/** 生成的序列号位数. */
	public static final int SN_COUNT = 9;

	/** KeyStore密码. */
	public static final String KEY_STORE_PASSWORD = "111111";

	/** Key别名. */
	public static final String KEY_ALIAS = "zlrt";

	/** Key保护密码. */
	public static final String KEY_PASSWORD = "111111";

	/** 有限期限(年). */
	public static final int VALID_TERM_YEAR = 5;

	/** 国家代码. */
	public static final String COUNTRY_CODE = "CN";

	/** 颁发者信息. */
	public static final String ISSUER_DN = "C=" + COUNTRY_CODE + ",O=华创证券,OU=证联融通,CN=证钱支付";

	/** 算法供应者. */
	public static final BouncyCastleProvider PROVIDER = new BouncyCastleProvider();

	static {
		Security.addProvider( PROVIDER );
	}

	private CertificateConstant() {
	}

}