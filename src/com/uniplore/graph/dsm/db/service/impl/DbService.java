package com.uniplore.graph.dsm.db.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;
import com.uniplore.graph.dsm.db.entity.DbPO;
import com.uniplore.graph.dsm.db.entity.DbVO;
import com.uniplore.graph.dsm.db.entity.EdgeDataVO;
import com.uniplore.graph.dsm.db.entity.EdgeVO;
import com.uniplore.graph.dsm.db.entity.NodeDataVO;
import com.uniplore.graph.dsm.db.entity.NodeVO;
import com.uniplore.graph.dsm.db.service.IDbService;

import lombok.experimental.var;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ietf.jgss.Oid;
import org.springframework.stereotype.Service;

@Service
public class DbService implements IDbService {

  @Override
  public String connectDataBase(DbPO dbPo) throws Exception {
    // 使用JDBC连接数据库
    String driverName = dbPo.getDriverName();// 首先应该得到其驱动，判断究竟是何种数据库‘
    String url = null;
    if (driverName != null && driverName.contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "?connectTimeout=3000&socketTimeout=3000"; // 设置连接超时的时间均是3s，如果3s未连接成功则直接终止连接
    }
    String user = dbPo.getUserName();
    String password = dbPo.getPassword();
    Connection connection;
    try {
      Class.forName(driverName);
      // 连接数据库
      connection = DriverManager.getConnection(url, user, password);
      if (dbPo.getIpAddress().length() != 0 && connection != null) {
        connection.close();//关闭流
        return "数据库连接成功";
      }
    } catch (Exception ex) {
      String message = ex.getMessage(); // 会打印出真实的数据库连接错误信息
      if (message.contains("Access denied")) {
        return "用户名和密码无效";
      } else if (message.contains("Communications link failure")) {
        return "与数据库通信时出错，不能连接到数据库服务器，请检查服务器是否正在运行以及您是否有权访问请求的数据库";
      } else {
        return "数据库连接失败";
      }
    }
    return "数据库连接失败";
  }

  @Override
  public List<String> showDataBase(DbPO dbPo) throws Exception {
    //建立一数组，用于存放此ip地址下所有的数据库
    List<String> dataBaseList = new ArrayList<String>();

    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());
    
    String url = null;
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "?connectTimeout=3000&socketTimeout=3000"; // 设置连接超时的时间均是3s，如果3s未连接成功则直接终止连接
    }
    // 连接数据库
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(),
        dbPo.getPassword());
    
    /* 功能： 连接上数据库之后，获取数据库中所有的数据库名
     * MySQL数据库必须采用getCatalogs()方法
     * 但是对于其他的数据库，采用的是getSchemas()方法
     * */
    ResultSet dbNames = connection.getMetaData().getCatalogs();   
    while (dbNames.next()) {
      String dbName = dbNames.getString("TABLE_CAT");
      //System.out.println("TABLE_CAT = " + dbName );  //判断是否正确的接收到表
      dataBaseList.add(dbName);
    }
    
    if (dataBaseList.size() != 0) {
      connection.close();
      return dataBaseList;
    }
    connection.close();
    return null;
   
  }
  
  
  @Override
  public List<String> showTable(DbPO dbPo,String dbName) throws Exception {
    List<String> tableList = new ArrayList<String>();
    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());

    String url = null;
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
        + "/" + dbName + "?connectTimeout=3000&socketTimeout=3000";
    }
    //System.out.println("url为:" + url);
    // 连接数据库
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
        dbPo.getPassword());
    
    ResultSet tables = connection.getMetaData().getTables(null, null, "%", null);
    while (tables.next()) {
      String table = tables.getString(3);
      //System.out.println(table);
      tableList.add(table);
    }
    //System.out.println(tableList.size());
    if (tableList.size() != 0 ) {
      connection.close();
      return tableList;
    }
    connection.close();
    return null;
  }

  @Override
  public List<String> showColumn(DbPO dbPo, String dbName, String tableName) throws Exception {
    List<String> columnList = new ArrayList<String>();
    //使用JDBC连接数据库
    Class.forName(dbPo.getDriverName());
    
    String url  = "";
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
          + "/" + dbName  + "?connectTimeout=3000&socketTimeout=3000";
    }
    //System.out.println("url为:" + url);
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
            dbPo.getPassword());
    ResultSet columns = connection.getMetaData().getColumns(null, null, tableName, null);
    while (columns.next()) {
      String column = columns.getString("COLUMN_NAME");
      //System.out.println(column);
      columnList.add(column);
    }
    
    if (columnList.size() != 0) {    //如果该数据库中有表则返回columnList，否则返回空
      connection.close();
      return columnList;
    }
    connection.close();
    return null;
  }

  
  /**
   * 功能:该算法是整个数据库作为数据源最重要的方法，在图中，每个小的图都是由两个点，一条边构成的，那么就需要在数据库中选出两列，在这两列上构造图，
   * 选出的这两列中的每一列都可能有很多值是重复的，对于重复的值，我们需要作为同一个点来对待，所以就需要一种数据结构来保存结果，并能快速的找到是够有
   * 重复的值存在，在此我选择了HashMap作为其数据结构.对于点的权重，我增加了一个简单的生成原则，当点和其它的点之间的边越多，其权重就越大，默认为1，多
   * 一条边的联系，权重就加1.当两个点中有某个值有空值时，权重为1，并且不再创建新的节点.
   */
  /*
  @Override
  public String dbDataFormatJson(DbPO dbPo, DbVO dbVo) throws Exception {
    //首先连接数据库
    Class.forName(dbPo.getDriverName());   //获取到数据库驱动，并连接数据库
    
    String url = "";
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
          + "/" + dbVo.getDbName()  + "?connectTimeout=3000&socketTimeout=3000";
    }
    System.out.println("拼接成的url地址为:" + url);
    
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
            dbPo.getPassword());
    
    //获取表中的两列
    String sourceNode = dbVo.getSourceNode();
    String targetNode = dbVo.getTargetNode();
    //获取表名
    String tableName = dbVo.getTableName();
    String sql = "select " + sourceNode + "," + targetNode + " from " + tableName;   //一定要注意其中的空格
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    ResultSet set = prepareStatement.executeQuery();
    
    //构造两个HashMap，分别用来放sourceNode和targetNode
    HashMap<String, Object> mapSourceNode = new HashMap<String, Object>();  //用来存放源点的name属性
    HashMap<String, Object> mapTargetNode = new HashMap<String, Object>();  //用来存放终点的name属性
    StringBuffer stringBuffer = new StringBuffer();
    int countNode = 0;  //点计数
    int countEdge = 0 ; //边计数
    while (set.next()) {  //经过该部分测试可以知道，当前返回的数据是一行行的返回的
      //***************************************节点一处理.*******************************************
      String node1 = set.getString(1);   //获取第一个点的值
      String node2 = set.getString(2);   //获取第二个点的值，同时获取是因为在构造node1点是需要知道node2点是否为空，如果为空，权值为0
      
      //sourceNode的属性值
      String nodeID1 = null;
      NodeDataVO data1 = null;
      String jsonString1 = null;
      
      if (node1 != null) {   //只有node1不为空，我们才有创建节点的必要性
        //判断node1的键知否已经被包含在mapSourceNode中，在说明该节点的出度不是1，此时应该做加一的操作
        if (mapSourceNode.containsKey(node1)) {
          //如果已经被包含，此时说明该点已经存在，count计数器不会发生任何的变化，也不需要将该数据再次加入到StringBuffer中
          //得到该key下的value值，也就是id值
          nodeID1 = (String)mapSourceNode.get(node1);  //根据其key获取value的值
          if (node2 != null) {    //如果此时node2的值不为空，也就是node1和node2之间存在一条边，此时需要更新node1的点的weight
            //下面的代码给重复的节点赋予新的权值
            //将上述的对象拆解为一个个的字符串，这些字符串中既含有点的数据，也含有边的数据
            String[] sb =  stringBuffer.toString().split(";");   //对stringBuffer中的数据进行合理的分割
            stringBuffer = new StringBuffer() ; //将stringBuffer的值全部清空，后面会重新构造
            for (int i = 0 ; i < sb.length ; i++) {  //遍历上述的数组
              if (!sb[i].contains("source")) {   //判断转成的字符串是否包含source字段，如果不包含，则说明是节点类型，而不是边类型
                NodeVO object = JSON.parseObject(sb[i], NodeVO.class);  //将字符串转换为NodeVO类型
                if (object.getData().getId().equals(nodeID1)) {
                  //再次判断得到的这个节点的id是否和我们在上面得到的nodeID1一样
                  //如果一样，得到该节点的权值，并在原来值的基础上加1
                  object.getData().setWeight(object.getData().getWeight() + 1);   //权值在原值的基础上加1
                  String jsonString = JSON.toJSONString(object);   //将该对象再次转换成JSON字符串
                  stringBuffer.append(jsonString + ";");
                } else {
                  //否则直接将原来的sb[i]放回到原来的stringBuffer中
                  stringBuffer.append(sb[i] + ";");
                }
              } else {
                //否则直接将原来的sb[i]放回到原来的stringBuffer中
                stringBuffer.append(sb[i] + ";");
              }
            }  //for
          } //node2 != null
        } else {   //if (node1 != null)配对的else字句
          //没有被包含，则首先计数要加1，并且根据其计数重新构造，并把该节点加入到hashmap中
          countNode++;
          //构造节点对象
          nodeID1 = "n" + countNode;   //拼接节点的编号
          if (node2 != null) {   //如果node2的值为空，那么我们在初始化node1节点的weight值应该设置为0
            data1 = new NodeDataVO(nodeID1, node1, 1);
          } else {
            data1 = new NodeDataVO(nodeID1, node1, 0);
          }
          NodeVO nodeVo1 = new NodeVO(data1, "nodes",false,false,true,false,false,true,"");  //创建节点
          jsonString1 = JSON.toJSONString(nodeVo1);    //构造出第一个节点
          mapSourceNode.put(node1, nodeID1);
          stringBuffer.append(jsonString1 + ";");   //将该数据追加到输出中
        }
      }
      //***************************************节点二处理.*******************************************
      
      
      //targetNode的属性值
      String nodeID2 = null;
      NodeDataVO data2 = null;
      String jsonString2 = null;
      
      if (node2 != null) {  //只有node2不为空，才有构造节点的必要性
        //判断node1的键知否已经被包含在mapSourceNode中
        if (mapTargetNode.containsKey(node2)) {
          //如果已经被包含，此时说明该点已经存在，count计数器不会发生任何的变化
          //得到该key下的value值，也就是id值
          nodeID2 = (String)mapTargetNode.get(node2);  //根据其key获取value的值
          if (node1 != null) {   //如果节点1的值为空，则美有必要去改变其权值，该if语句中所有内容都是在改变已经存在的节点的权值
            //下面的代码给重复的节点赋予新的权值
            //将上述的对象拆解为一个个的字符串，这些字符串中既含有点的数据，也含有边的数据
            String[] sb =  stringBuffer.toString().split(";");   
            stringBuffer = new StringBuffer() ; //将stringBuffer的值全部清空，后面会重新构造
            for (int i = 0 ; i < sb.length ; i++) { //遍历上述的数组
              if (!sb[i].contains("source")) {   //判断obj是否为节点类型
                NodeVO object = JSON.parseObject(sb[i], NodeVO.class);
                if (object.getData().getId().equals(nodeID2)) {
                  //再次判断得到的这个节点的id是否和我们在上面得到的nodeID1一样
                  //如果一样，得到该节点的权值，并在原来值的基础上加1
                  object.getData().setWeight(object.getData().getWeight() + 1);   //权值在原值的基础上加1
                  String jsonString = JSON.toJSONString(object);   //将该对象再次转换成JSON字符串
                  stringBuffer.append(jsonString + ";");
                } else {
                  //否则直接将原来的sb[i]放回到原来的stringBuffer中
                  //将sb[i]转换成JSON对象
                  Object objJson = JSON.parse(sb[i]);  //将字符串转换成JSON对象
                  stringBuffer.append(objJson.toString() + ";");   //将JSON对象字符串
                }
              } else {
                //否则直接将原来的sb[i]放回到原来的stringBuffer中
                //将sb[i]转换成JSON对象
                Object objJson = JSON.parse(sb[i]);  //将字符串转换成JSON对象
                stringBuffer.append(objJson.toString() + ";");   //将JSON对象字符串
              }
            }
          }
        } else {  //判断if (mapTargetNode.containsKey(node2))的else子句
          //没有被包含，则首先计数要加1，并且根据其计数重新构造，并把该节点加入到hashmap中
          countNode++;
          //构造节点对象
          nodeID2 = "n" + countNode;   //拼接节点的编号
          if ( node1 != null) {   //如果node1的值为空，说明该节点没有与之相连的节点，此时只能将weight设置为0
            data2 = new NodeDataVO(nodeID2, node2, 1);
          } else {
            data2 = new NodeDataVO(nodeID2, node2, 0);
          }
          
          NodeVO nodeVo2 = new NodeVO(data2, "nodes",false,false,true,false,false,true,"");
          jsonString2 = JSON.toJSONString(nodeVo2);    //构造出第一个节点
          mapTargetNode.put(node2, nodeID2);
          stringBuffer.append(jsonString2 + ";");  //将该数据追加到输出中
        }
      }
      //***************************************边处理.*******************************************
      if (node1 == null || node2 == null) {   //如果有一个点的没有值，即为空，则不会构造边，因为此时至多只有一个点
        continue;
      } else {   //两个节点都不为空，才有构造变的必要性
        countEdge++;
        //用上面的参数构造边
        //构造边编号
        String edgeID1 = "e" + countEdge;
        EdgeDataVO data3 = new EdgeDataVO(edgeID1, nodeID1, nodeID2, 1);
        EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
        String jsonString3 = JSON.toJSONString(edgeVo);
        stringBuffer.append(jsonString3 + ";");  //将该数据追加到输出中
      }
      
    }
    String jsonContent = stringBuffer.toString();
    String jsonContentOutput = jsonContent.replace(";", ",");
    //拼接成最后的结果
    //System.out.println("------拼接最好的结果------");
    String outString = "[" + jsonContentOutput + "]" ;
    connection.close();
    return outString;
  }  */  //注释整个函数，下面的代码改进了整个算法的性能
  
  /*该函数实现的功能和上面被注释的函数实现的功能完全一致，只是在操作字符串时，采用了新的替代方法，算法的效率有了明显的提升，之前算法测试20s左右，现在的算法10s*/
  @Override
  public String dbDataFormatJson(DbPO dbPo, DbVO dbVo) throws Exception {
    //首先连接数据库
    Class.forName(dbPo.getDriverName());   //获取到数据库驱动，并连接数据库
    
    String url = "";
    if (dbPo.getDriverName() != null && dbPo.getDriverName().contains("mysql")) {
      url = "jdbc:mysql://" + dbPo.getIpAddress() + ":" + dbPo.getPortNumber()
          + "/" + dbVo.getDbName()  + "?connectTimeout=3000&socketTimeout=3000";
    }
    System.out.println("拼接成的url地址为:" + url);
    
    Connection connection = DriverManager.getConnection(url, dbPo.getUserName(), 
            dbPo.getPassword());
    
    //获取表中的两列
    String sourceNode = dbVo.getSourceNode();
    String targetNode = dbVo.getTargetNode();
    //获取表名
    String tableName = dbVo.getTableName();
    String sql = "select " + sourceNode + "," + targetNode + " from " + tableName;   //一定要注意其中的空格
    PreparedStatement prepareStatement = connection.prepareStatement(sql);
    ResultSet set = prepareStatement.executeQuery();
    
    //构造两个HashMap，分别用来放sourceNode和targetNode
    HashMap<String, Object> mapSourceNode = new HashMap<String, Object>();  //用来存放源点的name属性
    HashMap<String, Object> mapTargetNode = new HashMap<String, Object>();  //用来存放终点的name属性
    StringBuffer stringBuffer = new StringBuffer();
    int countNode = 0;  //点计数
    int countEdge = 0 ; //边计数
    while (set.next()) {  //经过该部分测试可以知道，当前返回的数据是一行行的返回的
      //***************************************节点一处理.*******************************************
      String node1 = set.getString(1);   //获取第一个点的值
      String node2 = set.getString(2);   //获取第二个点的值，同时获取是因为在构造node1点是需要知道node2点是否为空，如果为空，权值为0
      
      //sourceNode的属性值
      String nodeID1 = null;
      NodeDataVO data1 = null;
      String jsonString1 = null;
      
      if (node1 != null) {   //只有node1不为空，我们才有创建节点的必要性
        //判断node1的键知否已经被包含在mapSourceNode中，在说明该节点的出度不是1，此时应该做加一的操作
        if (mapSourceNode.containsKey(node1)) {
          //如果已经被包含，此时说明该点已经存在，count计数器不会发生任何的变化，也不需要将该数据再次加入到StringBuffer中
          //得到该key下的value值，也就是id值
          nodeID1 = (String)mapSourceNode.get(node1);  //根据其key获取value的值
          if (node2 != null) {    //如果此时node2的值不为空，也就是node1和node2之间存在一条边，此时需要更新node1的点的weight
            //下面的代码给重复的节点赋予新的权值
            //将上述的对象拆解为一个个的字符串，这些字符串中既含有点的数据，也含有边的数据
            String[] sb =  stringBuffer.toString().split(";");   //对stringBuffer中的数据进行合理的分割
            stringBuffer = new StringBuffer() ; //将stringBuffer的值全部清空，后面会重新构造
            for (int i = 0 ; i < sb.length ; i++) {  //遍历上述的数组
              if (!sb[i].contains("source")) {   //判断转成的字符串是否包含source字段，如果不包含，则说明是节点类型，而不是边类型
                NodeVO object = JSON.parseObject(sb[i], NodeVO.class);  //将字符串转换为NodeVO类型
                if (object.getData().getId().equals(nodeID1)) {
                  //再次判断得到的这个节点的id是否和我们在上面得到的nodeID1一样
                  //如果一样，得到该节点的权值，并在原来值的基础上加1
                  object.getData().setWeight(object.getData().getWeight() + 1);   //权值在原值的基础上加1
                  String jsonString = JSON.toJSONString(object);   //将该对象再次转换成JSON字符串
                  sb[i] = jsonString;    //当节点的weight发生变化时，完成节点度的修改，并将该字符串重新写回
                  break;   //结束整个循环
                }
              } 
            }  //for
            //遍历数组重新加入到StringBuffer中
            for (String s : sb) {
              stringBuffer.append(s + ";");
            }
          } //node2 != null
        } else {   //if (node1 != null)配对的else字句
          //没有被包含，则首先计数要加1，并且根据其计数重新构造，并把该节点加入到hashmap中
          countNode++;
          //构造节点对象
          nodeID1 = "n" + countNode;   //拼接节点的编号
          if (node2 != null) {   //如果node2的值为空，那么我们在初始化node1节点的weight值应该设置为0
            data1 = new NodeDataVO(nodeID1, node1, 1);
          } else {
            data1 = new NodeDataVO(nodeID1, node1, 0);
          }
          NodeVO nodeVo1 = new NodeVO(data1, "nodes",false,false,true,false,false,true,"");  //创建节点
          jsonString1 = JSON.toJSONString(nodeVo1);    //构造出第一个节点
          mapSourceNode.put(node1, nodeID1);
          stringBuffer.append(jsonString1 + ";");   //将该数据追加到输出中
        }
      }
      //***************************************节点二处理.*******************************************
      
      
      //targetNode的属性值
      String nodeID2 = null;
      NodeDataVO data2 = null;
      String jsonString2 = null;
      
      if (node2 != null) {  //只有node2不为空，才有构造节点的必要性
        //判断node1的键知否已经被包含在mapSourceNode中
        if (mapTargetNode.containsKey(node2)) {
          //如果已经被包含，此时说明该点已经存在，count计数器不会发生任何的变化
          //得到该key下的value值，也就是id值
          nodeID2 = (String)mapTargetNode.get(node2);  //根据其key获取value的值
          if (node1 != null) {   //如果节点1的值为空，则美有必要去改变其权值，该if语句中所有内容都是在改变已经存在的节点的权值
            //下面的代码给重复的节点赋予新的权值
            //将上述的对象拆解为一个个的字符串，这些字符串中既含有点的数据，也含有边的数据
            String[] sb =  stringBuffer.toString().split(";");   
            stringBuffer = new StringBuffer() ; //将stringBuffer的值全部清空，后面会重新构造
            for (int i = 0 ; i < sb.length ; i++) { //遍历上述的数组
              if (!sb[i].contains("source")) {   //判断obj是否为节点类型
                NodeVO object = JSON.parseObject(sb[i], NodeVO.class);
                if (object.getData().getId().equals(nodeID2)) {
                  //再次判断得到的这个节点的id是否和我们在上面得到的nodeID1一样
                  //如果一样，得到该节点的权值，并在原来值的基础上加1
                  object.getData().setWeight(object.getData().getWeight() + 1);   //权值在原值的基础上加1
                  String jsonString = JSON.toJSONString(object);   //将该对象再次转换成JSON字符串
                  sb[i] = jsonString;    //当节点的weight发生变化时，完成节点度的修改，并将该字符串重新写回
                  break;   //结束整个循环
                }
              }  
            }  //for
            //遍历数组重新加入到StringBuffer中
            for (String s : sb) {
              stringBuffer.append(s + ";");
            }
          }
        } else {  //判断if (mapTargetNode.containsKey(node2))的else子句
          //没有被包含，则首先计数要加1，并且根据其计数重新构造，并把该节点加入到hashmap中
          countNode++;
          //构造节点对象
          nodeID2 = "n" + countNode;   //拼接节点的编号
          if ( node1 != null) {   //如果node1的值为空，说明该节点没有与之相连的节点，此时只能将weight设置为0
            data2 = new NodeDataVO(nodeID2, node2, 1);
          } else {
            data2 = new NodeDataVO(nodeID2, node2, 0);
          }
          
          NodeVO nodeVo2 = new NodeVO(data2, "nodes",false,false,true,false,false,true,"");
          jsonString2 = JSON.toJSONString(nodeVo2);    //构造出第一个节点
          mapTargetNode.put(node2, nodeID2);
          stringBuffer.append(jsonString2 + ";");  //将该数据追加到输出中
        }
      }
      //***************************************边处理.*******************************************
      if (node1 == null || node2 == null) {   //如果有一个点的没有值，即为空，则不会构造边，因为此时至多只有一个点
        continue;
      } else {   //两个节点都不为空，才有构造变的必要性
        countEdge++;
        //用上面的参数构造边
        //构造边编号
        String edgeID1 = "e" + countEdge;
        EdgeDataVO data3 = new EdgeDataVO(edgeID1, nodeID1, nodeID2, 1);
        EdgeVO edgeVo = new EdgeVO(data3, "edges",false,false,true,false,false,true,"");
        String jsonString3 = JSON.toJSONString(edgeVo);
        stringBuffer.append(jsonString3 + ";");  //将该数据追加到输出中
      }
      
    }
    String jsonContent = stringBuffer.toString();
    String jsonContentOutput = jsonContent.replace(";", ",");
    //拼接成最后的结果
    //System.out.println("------拼接最好的结果------");
    String outString = "[" + jsonContentOutput + "]" ;
    connection.close();
    return outString;
  }
  
}

