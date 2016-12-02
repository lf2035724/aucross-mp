<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>上传证书</title>
<script type="text/javascript">
$( function() {
	$( '#uploadFrm' ).validate();
	$( '#verifyFrm' ).validate();
} );
</script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>商户管理</li>
		<li>上传证书</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<table class="profile">
		<colgroup>
			<col class="element" width="15%" />
			<col />
		</colgroup>

		<tr>
			<th><label>当前系统联融通code</label></th>
			<td>${code }</td>
		</tr>
	</table>

	<s:form id="uploadFrm" action="upload" method="post" enctype="multipart/form-data">
		<table class="profile">
			<colgroup>
				<col class="element" width="15%" />
				<col />
			</colgroup>

			<tr>
				<th><label class="required">需要更新的商户code</label></th>
				<td><s:textfield name="vo.code" cssClass="required default-input" /></td>
			</tr>
			<tr>
				<th><label>私钥</label></th>
				<td><s:file cssClass="default-input" name="privateUploadBean.upload"></s:file></td>
			</tr>
			<tr>
				<th><label>公钥</label></th>
				<td><s:file cssClass="default-input" name="uploadBean.upload"></s:file></td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="上传" cssClass="action save"></s:submit>
		</p>

		<s:token />

	</s:form>

	<s:form id="verifyFrm" action="verify" method="post" enctype="multipart/form-data">
		<table class="profile">
			<colgroup>
				<col class="element" width="15%" />
				<col />
			</colgroup>
			
			<tr>
				<th><label class="required">需要校验的商户code</label></th>
				<td><s:textfield name="vo.code" cssClass="required default-input" /></td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="校验" cssClass="action save"></s:submit>
		</p>

		<s:token />

	</s:form>

</body>