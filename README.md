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
dog | bird | cat
----|------|----
foo | foo  | foo
bar | bar  | bar
baz | baz  | baz
* 出参    
`{
   "result": 0,
   "msg": "测试通过!",
   "data": null
 }
`