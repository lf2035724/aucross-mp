/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.disableNativeSpellChecker = false ;   
	config.scayt_autoStartup = false;   
	config.font_names = '宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;'+ config.font_names ;
config.removeDialogTabs = 'image:advanced;link:advanced';

config.filebrowserUploadUrl="uploadImg.do";
 config.toolbarLocation = 'top';
 config.width = 900;
   config.height = 200;
   config.toolbar = 'Full';
   config.toolbarStartupExpanded = true; 
};
