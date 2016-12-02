$( function() {

	var url = $( '#frm' ).attr( 'action' );

	var workTypeName = '_type';

	var idName = '_id';

	/** 需要编辑的当前年. */
	var currentYear = $( '#currentYear' ).val();
	/** 需要编辑的系统. */
	var workSystem = $( '#workSystem' ).val();

	var date = new Date( currentYear, '0' );

	$( '#yearDatepicker' ).datepicker( {
		numberOfMonths: [ 3, 4 ],
		hideIfNoPrevNext: true,
		showButtonPanel: false,
		changeYear: false,
		changeMonth: false,
		defaultDate: date,
		minDate: date,
		maxDate: new Date( currentYear, '11', '31' )
	} );

	/** 删除当天、选中等的CSS. */
	$( 'a.ui-state-highlight' ).removeClass( 'ui-state-highlight' );
	$( 'a.ui-state-hover' ).removeClass( 'ui-state-hover' );
	$( 'a.ui-state-active' ).removeClass( 'ui-state-active' );

	/**
	 * 将后台数据放入Array.
	 */
	$( '#paramArray div' ).each( function() {
		$( '#paramArray' ).data( $( this ).attr( 'key' ), $( this ).attr( 'value' ) );
	} );

	/**
	 * 将非工作日变红.
	 */
	$( 'a.ui-state-default' ).each( function( index ) {
		var c = $( this ).parent().attr( 'onclick' ).toString();
		var arguments = c.substring( c.indexOf( '(' ) + 1, c.indexOf( ')' ) );
		var ar = arguments.split( ',' );

		var year = ar[ 2 ];
		var month = ( parseInt( ar[ 1 ] ) + 1 ).toString();
		var day = $( this ).text();

		var cDate = format( year, month, day );

		/* 非工作日. */
		if( 'NO' == $( '#paramArray' ).data( cDate ) ) {
			$( this ).css( {
				'background': 'none #FF870F'
			} ).attr( workTypeName, '0' );
		} else {
			$( this ).attr( workTypeName, '1' );
		}
		$( this ).parent().removeAttr( 'onclick' );
		$( this ).attr( idName, cDate ).removeAttr( 'href' ).css( 'cursor', 'pointer' );
	} );

	/**
	 * 格式化日期.
	 */
	function format( year, month, day ) {
		var time = year;
		if( 1 == month.length ) {
			time += '0' + month;
		} else {
			time += month;
		}
		if( 1 == day.length ) {
			time += '0' + day;
		} else {
			time += day;
		}
		return time;
	}

	/**
	 * 修改.
	 */
	$( 'a.ui-state-default' ).click( function() {
		var $dom = $( this );
		var $id = $dom.attr( idName );
		var $type = $dom.attr( workTypeName );
		/* 非工作日. */
		if( '0' == $type ) {
			if( confirm( '您确定要将该日期改为[工作日]？' ) ) {
				$.getJSON( url, 'vo.identity=' + $id + '&vo.workDayTypeName=1&vo.workSystemTypeName=' + workSystem, function() {
					$dom.attr( workTypeName, '1' ).removeAttr( 'style' ).add( 'ui-state-default' );
				} ).error( function() {
					alert( '修改失败！' );
				} );
			}
		}
		/* 工作日. */
		else {
			if( confirm( '您确定要将该日期改为[非工作日]？' ) ) {
				$.getJSON( url, 'vo.identity=' + $id + '&vo.workDayTypeName=0&vo.workSystemTypeName=' + workSystem, function() {
					$dom.attr( workTypeName, '0' ).css( {
						'background': 'none #FF870F'
					} );
				} ).error( function() {
					alert( '修改失败！' );
				} );
			}
		}
	} );

} );