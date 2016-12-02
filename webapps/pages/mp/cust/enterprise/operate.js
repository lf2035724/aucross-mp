/**
 * 企业客户操作JS.
 * 
 * @author 潘瑞峥
 * @date 2012-12-26
 */
$( function() {

	/**
	 * form校验.
	 */
	var url = path + '/cust/enterprise/check.do';
	var urlEmail = path + '/cust/enterprise/checkEmail.do';
	$( '#frm' ).validate( {
		rules: {
			'vo.identityCardNumber': {
				required: true,
				remote: {
					url: url,
					type: 'post',
					dataType: 'json',
					data: {
						credentialsType: function() {
							return $( '#credentialsType' ).val();
						},
						identityCardNumber: function() {
							return $( '#identityCardNumber' ).val();
						},
						id: function() {
							return $( '#id' ).val();
						}
					}
				}
			},
			'vo.email': {
				required: true,
				remote: {
					url: urlEmail,
					type: 'post',
					dataType: 'json',
					data: {
						email: function() {
							return $( '#email' ).val();
						}
					}
				}
			}
		},
		messages: {
			'vo.identityCardNumber': {
				remote: '证件类型和证件号码的组合已经存在'
			},
			'vo.email': {
				remote: '已经被占用'
			}
		},
		submitHandler: function( form ) {
			form.submit();
		}
	} );

	/**
	 * change清空.
	 */
	$( '#credentialsType' ).change( function() {
		$( '#identityCardNumber' ).val( '' );
	} );

} );