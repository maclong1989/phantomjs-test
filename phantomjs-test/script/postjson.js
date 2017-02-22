"use strict";

try{ 
	
	var system = require('system');
	var fs = require('fs');
	var page = require('webpage').create();
	
	phantom.onError = function(msg, trace) {
		  var msgStack = ['PHANTOM ERROR: ' + msg];
		  if (trace && trace.length) {
		    msgStack.push('TRACE:');
		    trace.forEach(function(t) {
		      msgStack.push(' -> ' + (t.file || t.sourceURL) + ': ' + t.line + (t.function ? ' (in function ' + t.function +')' : ''));
		    });
		  }
		  console.error(msgStack.join('\n'));
		  phantom.exit(1);
	};
	
	phantom.outputEncoding = "utf8";

	if (system.args.length === 1) {
	    console.log('Try to pass some args when invoking this script!');
	    phantom.exit(1);
	}
	 
	var json = JSON.parse(system.args[1].replace(/\^@@\^/g,' '));
	
	//代理设置
	if(json.proxy.use){
		phantom.setProxy(json.proxy.host, json.proxy.port, 'manual', '', '');;
	}	

	page.onError = function(msg, trace) {

		  var msgStack = ['ERROR: ' + msg];

		  if (trace && trace.length) {
		    msgStack.push('TRACE:');
		    trace.forEach(function(t) {
		      msgStack.push(' -> ' + t.file + ': ' + t.line + (t.function ? ' (in function "' + t.function +'")' : ''));
		    });
		  }

		  console.error(msgStack.join('\n'));
	};

	var server = json.url;
	var data = JSON.stringify(json.data);
	var headers = json.headers;
	page.settings.userAgent = json.userAgent||'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0';
	
	

	
	page.open(server, json.method, data, headers, function (status) {
			
//		    if (status !== 'success') {
	//		        console.log('Unable to post!');
	//		    } else {
	//		    	
	//		    	fs.write(json.fileName, page.content, 'w');
	//		    	
	//		    	console.log('fetch success!');
	//		    }
			
				console.log('open!');
				
			if (status === "success") {
					
				fs.write(json.fileName, page.content, 'w');
	
				phantom.exit();	
			
			}else{
				 console.log('Unable to post!');
				 phantom.exit();
			}
	});
	
}catch(e){
	 console.log("error:" + e.message);
	 phantom.exit(1);
}
