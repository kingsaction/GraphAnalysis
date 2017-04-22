//加载弹出框
document.write("<script type='text/javascript' src='../utils/sweetalert/js/sweetalert.min.js'></script>");
document.write("<link rel='stylesheet' type='text/css' href='../utils/sweetalert/css/sweetalert.css'>"); 
//模态框启动
$(document).ready(function() {
	// the "href" attribute of .modal-trigger must specify the modal ID that
	// wants to be triggered
	$('.modal-trigger').leanModal({
		dismissible : true, // 点击模态框外部则关闭模态框	
	});
});

//获取到所有的参数信息，并将其传至后台测试能否连接数据源
function modal_mysql_test(){
	//首先获取到表单元素的所有值
	var driverName = $("#mysqlDriverName option:selected").text();   //获取到select选中的值
	//alert("驱动为:" + driverName)
	var dataBaseType = $("#mysqlDataBaseType").val();
	//alert("数据库类型为:" + dataBaseType)
	var ipAddress = $("#mysqlIpAddress").val();
	//alert("ip地址为:" + ipAddress)
	var portNumber = $("#mysqlPortNumber").val();
	//alert("端口号为:" + portNumber)
	var connectionName = $("#mysqlConnectionName").val();
	//alert("连接名为:" + connectionName)
	var dataBaseName = $("#mysqlDataBaseName").val();
	//alert("数据库名为:" + dataBaseName)
	var userName = $("#mysqlUserName").val();
	//alert("用户名为:" + userName)
	var password = $("#mysqlPassword").val();
	//alert("密码为:" + password)
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		async: false,  //设置ajax交互为同步方式，默认采用的是异步方式
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
				swal("Good job!", "数据库连接成功", "success");
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

function modal_postgresql_test(){
	//首先获取到表单元素的所有值
	var driverName = $("#postgresqlDriverName option:selected").text();   //获取到select选中的值
	//alert("驱动为:" + driverName)
	var dataBaseType = $("#postgresqlDataBaseType").val();
	//alert("数据库类型为:" + dataBaseType)
	var ipAddress = $("#postgresqlIpAddress").val();
	//alert("ip地址为:" + ipAddress)
	var portNumber = $("#postgresqlPortNumber").val();
	//alert("端口号为:" + portNumber)
	var connectionName = $("#postgresqlConnectionName").val();
	//alert("连接名为:" + connectionName)
	var dataBaseName = $("#postgresqlDataBaseName").val();
	//alert("数据库名为:" + dataBaseName)
	var userName = $("#postgresqlUserName").val();
	//alert("用户名为:" + userName)
	var password = $("#postgresqlPassword").val();
	//alert("密码为:" + password)
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		async: false,  //设置ajax交互为同步方式，默认采用的是异步方式
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
				swal("Good job!", "数据库连接成功", "success");
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

function modal_greenplum_test(){
	//首先获取到表单元素的所有值
	var driverName = $("#greenplumDriverName option:selected").text();   //获取到select选中的值
    //alert("驱动为:" + driverName)
	var dataBaseType = $("#greenplumDataBaseType").val();
	//alert("数据库类型为:" + dataBaseType)
	var ipAddress = $("#greenplumIpAddress").val();
	//alert("ip地址为:" + ipAddress)
	var portNumber = $("#greenplumPortNumber").val();
	//alert("端口号为:" + portNumber)
	var connectionName = $("#greenplumConnectionName").val();
	//alert("连接名为:" + connectionName)
	var dataBaseName = $("#greenplumDataBaseName").val();
	//alert("数据库名为:" + dataBaseName)
	var userName = $("#greenplumUserName").val();
	//alert("用户名为:" + userName)
	var password = $("#greenplumPassword").val();
	//alert("密码为:" + password)
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		async: false,  //设置ajax交互为同步方式，默认采用的是异步方式
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
				swal("Good job!", "数据库连接成功", "success");
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

function modal_oracle_test(){
	//首先获取到表单元素的所有值
	var driverName = $("#oracleDriverName option:selected").text();   //获取到select选中的值
    //alert("驱动为:" + driverName)
	var dataBaseType = $("#oracleDataBaseType").val();
	//alert("数据库类型为:" + dataBaseType)
	var ipAddress = $("#oracleIpAddress").val();
	//alert("ip地址为:" + ipAddress)
	var portNumber = $("#oraclePortNumber").val();
	//alert("端口号为:" + portNumber)
	var connectionName = $("#oracleConnectionName").val();
	//alert("连接名为:" + connectionName)
	var dataBaseName = $("#oracleDataBaseName").val();
	//alert("数据库名为:" + dataBaseName)
	var userName = $("#oracleUserName").val();
	//alert("用户名为:" + userName)
	var password = $("#oraclePassword").val();
	//alert("密码为:" + password)
	//输出上述的所有值，判断是是否正确的得到连接信息
	//alert("驱动名称为:"+driverName+"数据库类型为:"+dataBaseType+"ip地址为:"+ipAddress+"端口号为:"+portNumber+"连接名为"+connectionName+"用户名为"+userName+"密码为:"+password);
	$.ajax({
		async: false,  //设置ajax交互为同步方式，默认采用的是异步方式
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
				swal("Good job!", "数据库连接成功", "success");
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
function readonly_mysql(){
	$("#mysqlDataBaseType").removeClass("invalid");
	$("#mysqlDataBaseType").addClass("valid");
}

function readonly_postgresql(){
	$("#postgresqlDataBaseType").removeClass("invalid");
	$("#postgresqlDataBaseType").addClass("valid");
}

function readonly_greenplum(){
	$("#greenplumDataBaseType").removeClass("invalid");
	$("#greenplumDataBaseType").addClass("valid");
}

function readonly_oracle(){
	$("#greenplumDataBaseType").removeClass("invalid");
	$("#greenplumDataBaseType").addClass("valid");
}