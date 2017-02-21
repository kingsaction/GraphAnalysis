function modal_mysql_login(){
	//首先获取到表单元素的所有值
	var driverName = $("#driverName option:selected").text();   //获取到select选中的值
	var dataBaseType = $("#dataBaseType").val();
	var ipAddress = $("#ipAddress").val();
	var portNumber = $("#portNumber").val();
	var connectionName = $("#connectionName").val();
	var userName = $("#userName").val();
	var password = $("#password").val();
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