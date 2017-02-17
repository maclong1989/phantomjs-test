"use strict";

try{ 
	
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

	var system = require('system');
	
	if (system.args.length === 1) {
	    console.log('Try to pass some args when invoking this script!');
	    phantom.exit(1);
	}
	 
	var json = JSON.parse(system.args[1].replace(/\^@@\^/g,' '));
	
	//代理设置
	if(json.proxy.use){
		phantom.setProxy(json.proxy.host, json.proxy.port, 'manual', '', '');;
	}	
	
	var page = require('webpage').create();
	

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
	
	page.open(server, json.method, data, headers, function (status) {
		
		try{
			
		    if (status !== 'success') {
		        console.log('Unable to post!');
		    } else {
		    	console.log('fetch ok!');
		        console.log(page.content);
		    }
		    
		    phantom.exit();
	    
		}catch(e){
			 console.log("error:" + e.message);
			 phantom.exit(1);
		}
		
		
		
	});
	

}catch(e){
	 console.log("error:" + e.message);
	 phantom.exit(1);
}
