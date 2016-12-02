/**
 * 企业客户复核JS.
 * 
 * @author 潘瑞峥
 * @date 2012-12-26
 */
$( function() {

	var passUrl = $( '#passUrl' ).val();
	var refuseUrl = $( '#refuseUrl' ).val();

	/** 通过按钮. */
	$( '#passBtn' ).click( function() {
		if( validate() ) {
			$.confirm( '您确认要通过选择的记录？', function( result ) {
				if( result ) {
					$( '#frm' ).attr( 'action', passUrl )[ 0 ].submit();
					// 加载中.
					$.loading();
				}
			} );
		}
	} );

	/** 拒绝按钮. */
	$( '#refuseBtn' ).click( function() {
		if( validate() ) {
			$.confirm( '您确认要拒绝选择的记录？', function( result ) {
				if( result ) {
					$( '#frm' ).attr( 'action', refuseUrl )[ 0 ].submit();
					// 加载中.
					$.loading();
				}
			} );
		}
	} );

	/** 校验选择. */
	function validate() {
		var $el = $( 'input:radio:checked' );
		if( 0 < $el.length ) {
			return true;
		} else {
			alert( '请选择一条记录进行操作！' );
			return false;
		}
	}

} );