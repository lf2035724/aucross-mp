package com.ylink.ylpay.project.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.crypto.Cipher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import com.ylink.ylpay.project.constants.CertificateConstant;
import com.ylink.ylpay.project.constants.bean.CertificateBean;

/**
 * 数字证书工具.
 * 
 * @author 潘瑞峥
 * @date 2013-4-19
 */
public class CertificateUtils {

	/**
	 * 根据CertificateBean生成证书对象.
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Certificate generateCertificate( CertificateBean bean ) throws Exception {

		Validate.notNull( bean, "参数不能为空." );

		X509V3CertificateGenerator v3CertGen = new X509V3CertificateGenerator();

		// 签名算法.
		v3CertGen.setSignatureAlgorithm( CertificateConstant.SIGNATURE_ALGORITHM );
		// 序列号.
		v3CertGen.setSerialNumber( new BigInteger( bean.getSerialNumber().toString() ) );
		// 公钥.
		v3CertGen.setPublicKey( bean.getPublicKey() );
		// 颁发者.
		v3CertGen.setIssuerDN( new X509Name( CertificateConstant.ISSUER_DN ) );
		// 使用者.
		v3CertGen.setSubjectDN( new X509Name( bean.getSubjectDN() ) );
		// 开始日期.
		v3CertGen.setNotBefore( bean.getValidityTimeBegin() );
		// 结束日期.
		v3CertGen.setNotAfter( DateUtils.addYears( bean.getValidityTimeBegin(), bean.getValidityYear() ) );

		// 参数使用私钥.
		Certificate certificate = v3CertGen.generate( bean.getPrivateKey() );

		return certificate;
	}

	/**
	 * 根据证书生成KeyStore.
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	private static KeyStore newKeyStore( CertificateBean bean ) throws Exception {
		Certificate certificate = generateCertificate( bean );

		KeyStore keyStore = KeyStore.getInstance( CertificateConstant.JKS_TYPE );
		// 加载.
		keyStore.load( null, CertificateConstant.KEY_STORE_PASSWORD.toCharArray() );

		// 设置密钥.
		keyStore.setKeyEntry( CertificateConstant.KEY_ALIAS, bean.getPrivateKey(), CertificateConstant.KEY_PASSWORD.toCharArray(),
				new Certificate[] { certificate } );

		return keyStore;
	}

	/**
	 * 加载JKS返回KeyStore.
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	private static KeyStore loadKeyStore( InputStream inputStream ) throws Exception {
		KeyStore keyStore = KeyStore.getInstance( CertificateConstant.JKS_TYPE );
		// 加载.
		keyStore.load( inputStream, CertificateConstant.KEY_STORE_PASSWORD.toCharArray() );

		return keyStore;
	}

	/**
	 * 解析流返回证书.
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	private static Certificate parserCertificate( InputStream inputStream ) throws Exception {
		KeyStore keyStore = KeyStore.getInstance( CertificateConstant.JKS_TYPE );
		// 加载.
		keyStore.load( inputStream, CertificateConstant.KEY_STORE_PASSWORD.toCharArray() );

		// 通过别名获取证书.
		Certificate certificate = keyStore.getCertificate( CertificateConstant.KEY_ALIAS );

		return certificate;
	}

	/**
	 * 生成私钥字节数组.
	 * 
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static byte[] toPrivateKeyBytes( CertificateBean bean ) throws Exception {
		ByteArrayOutputStream outputStream = null;
		byte[] privateKeyBytes = null;
		try {
			outputStream = new ByteArrayOutputStream();

			KeyStore keyStore = newKeyStore( bean );
			// 保存到字节流.
			keyStore.store( outputStream, CertificateConstant.KEY_STORE_PASSWORD.toCharArray() );

			privateKeyBytes = outputStream.toByteArray();

			return privateKeyBytes;
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( outputStream );
		}
	}

	/**
	 * 根据私钥字节数组生成公钥字节数组.
	 * 
	 * @param privateKeyBytes
	 * @return
	 * @throws Exception
	 */
	public static byte[] toPublicKeyBytes( byte[] privateKeyBytes ) throws Exception {
		InputStream inputStream = null;
		byte[] publicKeyBytes = null;
		try {
			// 打开输入流.
			inputStream = new ByteArrayInputStream( privateKeyBytes );

			Certificate certificate = parserCertificate( inputStream );

			publicKeyBytes = certificate.getEncoded();

			return publicKeyBytes;
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 生成jks文件.
	 * 
	 * @param bean
	 * @param jksPath
	 * @throws Exception
	 */
	private static void toJks( CertificateBean bean, String jksPath ) throws Exception {
		OutputStream outputStream = null;
		try {
			// 打开输出流.
			outputStream = FileUtils.openOutputStream( new File( jksPath ) );

			KeyStore keyStore = newKeyStore( bean );

			// 保存到输出流.
			keyStore.store( outputStream, CertificateConstant.KEY_STORE_PASSWORD.toCharArray() );
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( outputStream );
		}
	}

	/**
	 * 根据jks文件生成cer文件.
	 * 
	 * @param jksPath
	 * @param cerPath
	 * @throws Exception
	 */
	private static void toCer( String jksPath, String cerPath ) throws Exception {
		InputStream inputStream = null;
		try {
			// 打开输入流.
			inputStream = FileUtils.openInputStream( new File( jksPath ) );

			Certificate certificate = parserCertificate( inputStream );

			FileUtils.writeByteArrayToFile( new File( cerPath ), certificate.getEncoded() );
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 根据数字证书对象生成jks和cer.
	 * 
	 * @param bean
	 * @param jksPath
	 * @param cerPath
	 * @throws Exception
	 */
	public static void toJksAndCer( CertificateBean bean, String jksPath, String cerPath ) throws Exception {
		toJks( bean, jksPath );
		toCer( jksPath, cerPath );
		System.out.println( "finish..." );
	}

	/**
	 * 获取文件的私钥.
	 * 
	 * @param jksPath
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey privateKeyFromJks( String jksPath ) throws Exception {
		InputStream inputStream = null;
		try {
			// 打开输入流.
			inputStream = FileUtils.openInputStream( new File( jksPath ) );

			KeyStore keyStore = loadKeyStore( inputStream );

			PrivateKey privateKey = ( PrivateKey ) keyStore.getKey( CertificateConstant.KEY_ALIAS,
					CertificateConstant.KEY_PASSWORD.toCharArray() );

			return privateKey;
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 获取文件的私钥.
	 * 
	 * @param privateKeyBytes
	 * @return
	 * @throws Exception
	 */
	private static PrivateKey privateKeyFromJks( byte[] privateKeyBytes ) throws Exception {
		InputStream inputStream = null;
		try {
			// 打开字节流.
			inputStream = new ByteArrayInputStream( privateKeyBytes );

			KeyStore keyStore = loadKeyStore( inputStream );

			PrivateKey privateKey = ( PrivateKey ) keyStore.getKey( CertificateConstant.KEY_ALIAS,
					CertificateConstant.KEY_PASSWORD.toCharArray() );

			return privateKey;
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 获取文件的公钥.
	 * 
	 * @param cerPath
	 * @return
	 * @throws Exception
	 */
	private static PublicKey publicKeyFromCer( String cerPath ) throws Exception {
		InputStream inputStream = null;
		try {
			// 打开输入流.
			inputStream = FileUtils.openInputStream( new File( cerPath ) );

			CertificateFactory certificateFactory = CertificateFactory.getInstance( CertificateConstant.CERTIFICATE_TYPE,
					CertificateConstant.PROVIDER );
			Certificate certificate = certificateFactory.generateCertificate( inputStream );

			return certificate.getPublicKey();
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 获取文件的公钥.
	 * 
	 * @param publicKeyBytes
	 * @return
	 * @throws Exception
	 */
	private static PublicKey publicKeyFromCer( byte[] publicKeyBytes ) throws Exception {
		InputStream inputStream = null;
		try {
			// 打开字节流.
			inputStream = new ByteArrayInputStream( publicKeyBytes );

			CertificateFactory certificateFactory = CertificateFactory.getInstance( CertificateConstant.CERTIFICATE_TYPE,
					CertificateConstant.PROVIDER );
			Certificate certificate = certificateFactory.generateCertificate( inputStream );

			return certificate.getPublicKey();
		} catch ( Exception e ) {
			throw e;
		} finally {
			// 静默关闭.
			IOUtils.closeQuietly( inputStream );
		}
	}

	/**
	 * 根据私钥加密.
	 * 
	 * @param data
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	private static byte[] decryptByPrivateKey( byte[] data, PrivateKey privateKey ) throws Exception {
		Cipher cipher = Cipher.getInstance( CertificateConstant.CERTIFICATE_ALGORITHM );
		cipher.init( Cipher.DECRYPT_MODE, privateKey );
		return cipher.doFinal( data );
	}

	/**
	 * 根据公钥解密.
	 * 
	 * @param data
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	private static byte[] encryptByPublicKey( byte[] data, PublicKey publicKey ) throws Exception {
		Cipher cipher = Cipher.getInstance( CertificateConstant.CERTIFICATE_ALGORITHM );
		cipher.init( Cipher.ENCRYPT_MODE, publicKey );
		return cipher.doFinal( data );
	}

	/**
	 * 证书.
	 * 
	 * @param jksPath
	 * @param cerPath
	 * @param content
	 * @throws Exception
	 */
	public static void verify( String jksPath, String cerPath, String content ) throws Exception {
		PrivateKey privateKey = privateKeyFromJks( jksPath );
		PublicKey publicKey = publicKeyFromCer( cerPath );

		verify( privateKey, publicKey, content );
	}

	/**
	 * 字节数组.
	 * 
	 * @param privateKeyBytes
	 * @param publicKeyBytes
	 * @param content
	 * @throws Exception
	 */
	public static void verify( byte[] privateKeyBytes, byte[] publicKeyBytes, String content ) throws Exception {
		Validate.notNull( privateKeyBytes, "私钥不能为空." );
		Validate.notNull( publicKeyBytes, "公钥不能为空." );

		PrivateKey privateKey = privateKeyFromJks( privateKeyBytes );
		PublicKey publicKey = publicKeyFromCer( publicKeyBytes );

		verify( privateKey, publicKey, content );
	}

	/**
	 * 根据私钥、公钥对一段内容进行加签验签、加密解密校验.
	 * 
	 * @param privateKey
	 * @param publicKey
	 * @param content
	 */
	private static void verify( PrivateKey privateKey, PublicKey publicKey, String content ) throws Exception {
		byte[] data = content.getBytes();

		/* 加签验签. */
		Signature signature = Signature.getInstance( CertificateConstant.SIGNATURE_ALGORITHM );
		signature.initSign( privateKey );
		signature.update( data );
		byte[] sign = signature.sign();
		System.out.println( "加签后内容: " + new String( sign ) );

		signature.initVerify( publicKey );
		signature.update( data );
		boolean result = signature.verify( sign );

		Validate.isTrue( result, "加签验签验证失败." );
		// Validate.isTrue( result, "加签验签验证失败." );

		/* 加密解密. */
		byte[] encodedData = encryptByPublicKey( data, publicKey );
		System.out.println( "加密后内容: " + new String( encodedData ) );
		// System.out.println( Base64.encodeBase64String( encodedData ) );
		byte[] decodedData = decryptByPrivateKey( encodedData, privateKey );

		System.out.println( "解密后内容: " + new String( decodedData ) );

		Validate.isTrue( new String( data ).equals( new String( decodedData ) ), "加密解密验证失败." );
	}

	public static void main( String[] args ) throws Exception {
		String content = "中ab文cd0123?!X";
		System.out.println( content );
		// CertificateBean bean = new CertificateBean( "丁当001" );

		// String jksPath = "d:/key/hcfund_prod.jks";
		// String cerPath = "d:/key/hcfund_prod.cer";
		String jksPath = "d:/key/公私钥测试.jks";
		String cerPath = "d:/key/公私钥测试.cer";
		// toJksAndCer( bean, jksPath, cerPath );
		verify( jksPath, cerPath, content );

		// byte[] privateKeyBytes = toPrivateKeyBytes( bean );
		// byte[] publicKeyBytes = toPublicKeyBytes( privateKeyBytes );
		// verify( privateKeyBytes, publicKeyBytes, content );
	}

}