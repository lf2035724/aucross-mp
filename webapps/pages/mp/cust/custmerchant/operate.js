/**
 * 商户操作JS.
 * 
 * @author 潘瑞峥
 * @date 2013-01-07
 */
var defaultEl = '<option value=""></option>';

$( function() {

	var optTypePrefix = 'vo.custMerchantBusinesses[';
	var optTypeSuffix = '].businessType';
	var projectOptTypePrefix = 'vo.merchantProjectOrders[';
	var projectOptTypeSuffix = '].projectOrderType';
	var merchantType;

	/** validate */
	$( '#frm' ).validate( {
		submitHandler: function( form ) {
			buildDate();
			form.submit();
		}
	} );

	// init
	init();

	function init() {
		merchantType = $( '#merchantType' ).val();
		$( 'div.' + merchantType ).show();

		// var agencyUrl = path + '/mp/cust/agency/find.do';
		// var el = defaultEl;
		// $.getJSON( agencyUrl, function( json ) {
		// $( '#agency' ).empty();
		// $.each( json.beans, function() {
		// el += '<option value="' + this.identity + '">' + this.name + '</option>';
		// } );
		// $( '#agency' ).append( el );
		// } );
	}

	/**
	 * 商户类型change.
	 */
	$( '#merchantType' ).change( function() {
		merchantType = $( this ).val();
		$( 'div.sib' ).hide();
		$( 'div.' + merchantType ).show();
	} );

	/**
	 * 构建数据.
	 */
	function buildDate() {
		// 重置name属性, 防止重复.
		$( 'div.sib input:checkbox' ).attr( 'name', 'optType' );
		
		// 重新设置name属性, 提交后台.
		$( 'div.sib:visible input:checkbox:checked' ).each( function( index ) {
			$( this ).attr( 'name', optTypePrefix + index + optTypeSuffix );
		} );
		
		// 重新设置name属性, 提交后台.
		$( 'div.sic input:checkbox:checked' ).each( function( index ) {
			$( this ).attr( 'name', projectOptTypePrefix + index + projectOptTypeSuffix );
		} );
	}

} );

function popupCustomer( url, params ) {
	if( url == null || url == '' )
		url = $( '#url' ).val();
	var $popup = $( '#customer_popup' );
	$( $popup ).dialog( {
		resizable: true,
		modal: true,
		height: 500,
		width: 700,
		close: function( event, ui ) {
			$( this ).dialog( 'destroy' ).html( '' );
		},
		buttons: {
			'关闭': function() {
				$( this ).dialog( 'destroy' ).html( '' );
			}
		}
	} );

	$.post( url, params, function( data ) {
		$( $popup ).html( data );
	} );
}

/**
 * 选择客户回调
 */
function callbackSelectCustomer( param ) {
	$( '#customer_popup' ).dialog( 'destroy' ).html( '' );

	$( '#custId' ).val( '' );
	$( '#custContent' ).text( '' );

	var custId = param.id;
	var content = param.name + '（' + custId + '）';
	$( '#custId' ).val( custId );
	$( '#custContent' ).text( content );

	var cardUrl = path + '/cust/bankcard/find.do?custId=' + custId;
	var el = defaultEl;
	$( '#bankcard' ).empty();
	$.getJSON( cardUrl, function( json ) {
		$.each( json.beans, function( i, e ) {
			el += '<option value="' + e.identity + '">' + e.cardName + '（' + e.cardNo + '）' + '</option>';
		} );
		$( '#bankcard' ).append( el );
	} );
}

/**
 * 机构弹出.
 */
function popupAgency( url, params ) {
	if( url == null || url == '' )
		url = $( '#agencyUrl' ).val();
	var $popup = $( '#agency_popup' );
	$( $popup ).dialog( {
		resizable: true,
		modal: true,
		height: 550,
		width: 900,
		close: function( event, ui ) {
			$( this ).dialog( 'destroy' ).html( '' );
		},
		buttons: {
			'关闭': function() {
				$( this ).dialog( 'destroy' ).html( '' );
			}
		}
	} );

	$.post( url, params, function( data ) {
		$( $popup ).html( data );
	} );
}

/**
 * 选择机构回调.
 */
function callbackAgency( param ) {
	$( '#agency_popup' ).dialog( 'destroy' ).html( '' );

	$( '#agencyId' ).val( '' );
	$( '#agencyContent' ).text( '' );

	$( '#agencyId' ).val( param.dataId );
	var content = param.name + '（' + param.code + '）';
	$( '#agencyContent' ).text( content );
}