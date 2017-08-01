# App接口文档

#### Base URL    
* http://host:port/wms/api/

#### 登陆模块    
1.服务器连接测试    
* URL      
test/connection
* 入参   
无
* 出参    
`{
   "result": 0,
   "msg": "测试通过!",
   "data": null
 }
`

2.登陆
* URL      
 log/in
* 入参    
   
参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
grant_type | String | 否       | *      |  password
client_id  | String | 否       | *      |  wms
username   | String | 否       |用户名   |  CLL
password   | String | 否       |密码     |  123456

* 出参    
`{
   "result": 0,
   "msg": "登陆成功!",
   "data": {
             "access_token" : "9ca58a19-190e-42c4-8c30-658e6c0b2459",
             "token_type" : "bearer",
             "refresh_token" : "edf58afb-88ce-435d-8c27-177a85495738",
             "expires_in" : 43199,
             "scope" : "SCOPE_TRUST"
           }
 }
`

3.注销    
* URL      
log/out
* 入参   
无
* 出参    
`{
   "result": 0,
   "msg": "测试通过!",
   "data": null
 }
`

#### 首页模块    
1.获取首页信息    
* URL      
 home/info
* 入参    
   
参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
warehouseId | String | 否       |仓库id|  11

* 出参    
`{
   "result": 0,
   "msg": "请求成功!",
   "data": {
             "acceptBillCount" : "1",
             "putawayBillCount" : "3",
             "reviewBillCount" : "4",
             "tranferBillCount" : "5",
           }
 }
`