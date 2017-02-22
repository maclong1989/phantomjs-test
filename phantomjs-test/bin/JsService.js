 
 try{
 	
 	 console.log("js start running!");
 	
	 var server = require('webserver').create();
	 var service;
	
	 var config = {
	 	service : {
	 		host : '127.0.0.1',
	 		port : '8088'
		}
	 
	 
	 };
	 
	 var myService = {
		start : function(func){
			var addr = config.service.host + ':' + config.service.port;
			service = server.listen(addr, func);
				
		},
			
		stop : function(){
			
			service.stop();
			
		}
		 
			 
	 };
	 
	 console.log("myService start!");
	 
	 myService.start(function (request, response) {
	    console.log('Request received at ' + new Date());
	
	    response.statusCode = 200;
	    response.headers = {
	        'Cache': 'no-cache',
	        'Content-Type': 'text/plain;charset=utf-8'
	    };
	    response.write('<html><body>Hello!</body></html>');
	    response.close();
	});

}catch(e){
	 console.log("error:" + e.message);
	 phantom.exit(1);
}

