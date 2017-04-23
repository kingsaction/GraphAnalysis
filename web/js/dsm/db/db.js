function modal_mysql_login(){
	//首先获取到表单元素的所有值
	var driverName = $("#mysqlDriverName option:selected").text();   //获取到select选中的值
	var dataBaseType = $("#mysqlDataBaseType").val();
	var ipAddress = $("#mysqlIpAddress").val();
	var portNumber = $("#mysqlPortNumber").val();
	var connectionName = $("#mysqlConnectionName").val();
	var dataBaseName = $("#mysqlDataBaseName").val();
	var userName = $("#mysqlUserName").val();
	var password = $("#mysqlPassword").val();
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		url: "/graphanalysis/dsm/db/connection?t=" + (new Date()).getTime(),   //提交数据，测试是否能够成功的连接数据源,url路径采用绝对路径
		type:"POST",
		data: {
			//成功的连接数据源之后要能获取到数据源的信息
			"driverName": driverName,
			"dataBaseType": dataBaseType,
			"ipAddress": ipAddress,
			"portNumber": portNumber,
			"connectionName": connectionName,
			"dataBaseName": dataBaseName,
			"userName": userName,
			"password": password,
		},
		success: function(data) {
				//alert("返回值为:"+data);   //判断是否成功的接收到返回值，当成功的连接数据源接收到的数据为:"数据库连接成功"
				//连接数据源失败时，返回值为"数据库连接失败"
			if("数据库连接成功" == data){
				//跳转
				$('#modal-db-json').closeModal({
                    out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                });
				var form = $("<form method='post'></form>");
				/*参考文章: http://stackoverflow.com/questions/42053775/getting-error-form-submission-canceled-because-the-form-is-not-connected*/
				$(document.body).append(form);    //动态构建的表单，必须要使用这个语句，将form加到body中才行
				
		        form.attr({"action":"/graphanalysis/dsm/db/dbPage"});
		        var arr = new Array();
		        arr["driverName"] = driverName;
		        arr["dataBaseType"] = dataBaseType;
		        arr["ipAddress"] = ipAddress;
		        arr["portNumber"] = portNumber;
		        arr["connectionName"] = connectionName;
		        arr["userName"] = userName;
		        arr["password"]= password;
		        for (var key in arr)
		        {
		            var input = $("<input type='hidden'>");
		            input.attr({"name":key});
		            input.val(arr[key]);
		            form.append(input);
		        }
		        form.submit();
				//window.location.href = "/graphanalysis/dsm/db/dbPage";  //成功上传文件之后跳转到指定的路径中	
		}else if("用户名和密码无效" == data){
				swal("Oops...", "用户名和密码无效", "error");
			}else if("与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库" == data){
				swal("Oops...","与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库","error");
			}else{
				swal("Oops...","数据库连接失败","error");
			} //if语句结束    
			
			}
	})
}

function modal_postgresql_login(){
	//首先获取到表单元素的所有值
	var driverName = $("#postgresqlDriverName option:selected").text();   //获取到select选中的值
	var dataBaseType = $("#postgresqlDataBaseType").val();
	var ipAddress = $("#postgresqlIpAddress").val();
	var portNumber = $("#postgresqlPortNumber").val();
	var connectionName = $("#postgresqlConnectionName").val();
	var dataBaseName = $("#postgresqlDataBaseName").val();
	var userName = $("#postgresqlUserName").val();
	var password = $("#postgresqlPassword").val();
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		url: "/graphanalysis/dsm/db/connection?t=" + (new Date()).getTime(),   //提交数据，测试是否能够成功的连接数据源,url路径采用绝对路径
		type:"POST",
		data: {
			//成功的连接数据源之后要能获取到数据源的信息
			"driverName": driverName,
			"dataBaseType": dataBaseType,
			"ipAddress": ipAddress,
			"portNumber": portNumber,
			"connectionName": connectionName,
			"dataBaseName": dataBaseName,
			"userName": userName,
			"password": password,
		},
		success: function(data) {
				//alert("返回值为:"+data);   //判断是否成功的接收到返回值，当成功的连接数据源接收到的数据为:"数据库连接成功"
				//连接数据源失败时，返回值为"数据库连接失败"
			if("数据库连接成功" == data){
				//跳转
				$('#modal-db-json').closeModal({
                    out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                });
				var form = $("<form method='post'></form>");
				/*参考文章: http://stackoverflow.com/questions/42053775/getting-error-form-submission-canceled-because-the-form-is-not-connected*/
				$(document.body).append(form);    //动态构建的表单，必须要使用这个语句，将form加到body中才行
				
		        form.attr({"action":"/graphanalysis/dsm/db/dbPage"});
		        var arr = new Array();
		        arr["driverName"] = driverName;
		        arr["dataBaseType"] = dataBaseType;
		        arr["ipAddress"] = ipAddress;
		        arr["portNumber"] = portNumber;
		        arr["connectionName"] = connectionName;
		        arr["dataBaseName"] = dataBaseName;
		        arr["userName"] = userName;
		        arr["password"]= password;
		        for (var key in arr)
		        {
		            var input = $("<input type='hidden'>");
		            input.attr({"name":key});
		            input.val(arr[key]);
		            form.append(input);
		        }
		        form.submit();
				//window.location.href = "/graphanalysis/dsm/db/dbPage";  //成功上传文件之后跳转到指定的路径中	
		}else if("数据库连接成功" == data){
			swal("Good job!", "数据库连接成功", "success");
		}else if("您指定的PostgreSQL数据库的用户名不存在，请重新输入数据库用户名" == data){
			swal("Oops...", "您指定的PostgreSQL数据库的用户名不存在，请重新输入数据库用户名", "error");
		}else if("在连接PostgreSQL数据库时没有指定相应的用户名，请您检查数据库并输入正确的用户名" == data){
			swal("Oops...","在连接PostgreSQL数据库时没有指定相应的用户名，请您检查数据库并输入正确的用户名","error");
		}else if ("您指定的数据库不存在，请重新输入" == data){
			swal("Oops...","您指定的数据库不存在，请重新输入","error");
		} else{
			swal("Oops...","数据库连接失败","error");
		}//if语句结束      
			
      }
	})
}

function modal_greenplum_login(){
	//首先获取到表单元素的所有值
	var driverName = $("#greenplumDriverName option:selected").text();   //获取到select选中的值
	var dataBaseType = $("#greenplumDataBaseType").val();
	var ipAddress = $("#greenplumIpAddress").val();
	var portNumber = $("#greenplumPortNumber").val();
	var connectionName = $("#greenplumConnectionName").val();
	var dataBaseName = $("#greenplumDataBaseName").val();
	var userName = $("#greenplumUserName").val();
	var password = $("#greenplumPassword").val();
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		url: "/graphanalysis/dsm/db/connection?t=" + (new Date()).getTime(),   //提交数据，测试是否能够成功的连接数据源,url路径采用绝对路径
		type:"POST",
		data: {
			//成功的连接数据源之后要能获取到数据源的信息
			"driverName": driverName,
			"dataBaseType": dataBaseType,
			"ipAddress": ipAddress,
			"portNumber": portNumber,
			"connectionName": connectionName,
			"dataBaseName": dataBaseName,
			"userName": userName,
			"password": password,
		},
		success: function(data) {
				//alert("返回值为:"+data);   //判断是否成功的接收到返回值，当成功的连接数据源接收到的数据为:"数据库连接成功"
				//连接数据源失败时，返回值为"数据库连接失败"
			if("数据库连接成功" == data){
				//跳转
				$('#modal-db-json').closeModal({
                    out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                });
				var form = $("<form method='post'></form>");
				/*参考文章: http://stackoverflow.com/questions/42053775/getting-error-form-submission-canceled-because-the-form-is-not-connected*/
				$(document.body).append(form);    //动态构建的表单，必须要使用这个语句，将form加到body中才行
				
		        form.attr({"action":"/graphanalysis/dsm/db/dbPage"});
		        var arr = new Array();
		        arr["driverName"] = driverName;
		        arr["dataBaseType"] = dataBaseType;
		        arr["ipAddress"] = ipAddress;
		        arr["portNumber"] = portNumber;
		        arr["connectionName"] = connectionName;
		        arr["dataBaseName"] = dataBaseName;
		        arr["userName"] = userName;
		        arr["password"]= password;
		        for (var key in arr)
		        {
		            var input = $("<input type='hidden'>");
		            input.attr({"name":key});
		            input.val(arr[key]);
		            form.append(input);
		        }
		        form.submit();
				//window.location.href = "/graphanalysis/dsm/db/dbPage";  //成功上传文件之后跳转到指定的路径中	
		}else if("数据库连接成功" == data){
			swal("Good job!", "数据库连接成功", "success");
		}else if("对于您指定的ip地址，在文件pg_hba.conf中没有对象的实体存在，SSL off" == data){
			swal("Oops...", "对于您指定的ip地址，在文件pg_hba.conf中没有对象的实体存在，SSL off", "error");
		}else if("在连接PostgreSQL数据库时没有指定相应的用户名，请您检查数据库并输入正确的用户名" == data){
			swal("Oops...","在连接PostgreSQL数据库时没有指定相应的用户名，请您检查数据库并输入正确的用户名","error");
		}else if ("您指定的数据库不存在，请重新输入" == data){
			swal("Oops...","您指定的数据库不存在，请重新输入","error");
		} else {
			swal("Oops...","数据库连接失败","error");
		}//if语句结束       
			
     }
	})
}

function modal_oracle_login(){
	//首先获取到表单元素的所有值
	var driverName = $("#oracleDriverName option:selected").text();   //获取到select选中的值
	var dataBaseType = $("#oracleDataBaseType").val();
	var ipAddress = $("#oracleIpAddress").val();
	var portNumber = $("#oraclePortNumber").val();
	var connectionName = $("#oracleConnectionName").val();
	var dataBaseName = $("#oracleDataBaseName").val();
	var userName = $("#oracleUserName").val();
	var password = $("#oraclePassword").val();
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		url: "/graphanalysis/dsm/db/connection?t=" + (new Date()).getTime(),   //提交数据，测试是否能够成功的连接数据源,url路径采用绝对路径
		type:"POST",
		data: {
			//成功的连接数据源之后要能获取到数据源的信息
			"driverName": driverName,
			"dataBaseType": dataBaseType,
			"ipAddress": ipAddress,
			"portNumber": portNumber,
			"connectionName": connectionName,
			"dataBaseName": dataBaseName,
			"userName": userName,
			"password": password,
		},
		success: function(data) {
				//alert("返回值为:"+data);   //判断是否成功的接收到返回值，当成功的连接数据源接收到的数据为:"数据库连接成功"
				//连接数据源失败时，返回值为"数据库连接失败"
			if("数据库连接成功" == data){
				//跳转
				$('#modal-db-json').closeModal({
                    out_duration: 1000,    /*关闭模态框,1000代表1000ms，在1s内关闭模态框*/
                });
				var form = $("<form method='post'></form>");
				/*参考文章: http://stackoverflow.com/questions/42053775/getting-error-form-submission-canceled-because-the-form-is-not-connected*/
				$(document.body).append(form);    //动态构建的表单，必须要使用这个语句，将form加到body中才行
				
		        form.attr({"action":"/graphanalysis/dsm/db/dbPage"});
		        var arr = new Array();
		        arr["driverName"] = driverName;
		        arr["dataBaseType"] = dataBaseType;
		        arr["ipAddress"] = ipAddress;
		        arr["portNumber"] = portNumber;
		        arr["connectionName"] = connectionName;
		        arr["userName"] = userName;
		        arr["password"]= password;
		        for (var key in arr)
		        {
		            var input = $("<input type='hidden'>");
		            input.attr({"name":key});
		            input.val(arr[key]);
		            form.append(input);
		        }
		        form.submit();
				//window.location.href = "/graphanalysis/dsm/db/dbPage";  //成功上传文件之后跳转到指定的路径中	
		}else if("用户名和密码无效" == data){
				swal("Oops...", "用户名和密码无效", "error");
			}else if("与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库" == data){
				swal("Oops...","与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库","error");
			}else{
				swal("Oops...","数据库连接失败","error");
			} //if语句结束    
			
			}
	})
}