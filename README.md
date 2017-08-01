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
 login/in
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
   "msg": "测试通过!",
   "data": {
             "access_token" : "9ca58a19-190e-42c4-8c30-658e6c0b2459",
             "token_type" : "bearer",
             "refresh_token" : "edf58afb-88ce-435d-8c27-177a85495738",
             "expires_in" : 43199,
             "scope" : "SCOPE_TRUST"
           }
 }
`