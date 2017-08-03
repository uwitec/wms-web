# App接口文档

#### Base URL    
* http://host:port/wms/api/

#### 登陆模块    
1.服务器连接测试    
* URL      
log/test    
* 方法    
GET
* 入参   
无
* 出参    
```
{
   "result": 0,
   "msg": "测试通过!",
   "data": null
 }
```

2.登陆
* URL      
 log/in
* 方法    
 POST
* 入参    
   
参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
accountSet  | String | 否       |账套名|  abc
username   | String | 否       |用户名   |  CLL
password   | String | 否       |密码     |  123456

* 出参    
```
{
   "result": 0,
   "msg": "登陆成功!",
   "data": {
             "access_token" : "9ca58a19-190e-42c4-8c30-658e6c0b2459",
             "refresh_token" : "edf58afb-88ce-435d-8c27-177a85495738",
           }
 }
```

3.注销    
* URL      
log/out
* 方法    
 POST
* 入参   
无
* 出参    
```
{
   "result": 0,
   "msg": "退出成功!",
   "data": null
 }
```

4.获取账套
* URL      
log/accountSets
* 方法    
 GET
* 入参   
无
* 出参    
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": [
    {
      "id": 5,
      "name": "abc"
    },...
  ]
}
```

5.刷新token
* URL      
log/refreshToken
* 方法    
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
refresh_token| String | 否       |refresh_token|  888e79a3-a27f-4e6d-abcf-32d7bbc35a84    
* 出参    
```
{
  "result": 0,
  "msg":"请求成功!",
  "data":"888e79a3-a27f-4e6d-abcf-32d7bbc35a84"
}
```

#### 首页模块    
1.获取首页信息    
* URL      
 home/info
 * 方法    
  POST
* 入参    
   
参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
warehouseId | String | 是       |仓库id|  11

* 出参    
```
{
   "result": 0,
   "msg": "请求成功!",
   "data": {
             "acceptBillCount":1,
             "putawayBillCount":3,
             "reviewBillCount":4,
             "tranferBillCount":5,
           }
 }
```

2.获取仓库
* URL      
home/warehouseList
* 方法    
 GET
* 入参   
无
* 出参    
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": [
    {
      "id": 5,
      "name": "abc"
    },...
  ]
}
```

#### 验收模块    
1. 获取单位
* URL    
acceptence/unit
* 方法
GET
* 入参
无
* 出参
```
{
    "result": 0,
      "msg": "请求成功!",
      "data": [
        {
          "id": 5,
          "name": "AA公司"
        },...
      ]
}
```

2. 获取订单
* URL    
acceptence/order/{unitId}
* 方法
GET
* 入参
unitId 单位ID
* 出参
```j
{
    "id":"3",
    "name":"CG-2017-02-24-0003"
}
```

3. 获取订单详情
* URL    
acceptence/orderDetailds/{orderId}
* 方法
GET
* 入参
orderId 订单ID
* 出参
```
{
  "orderId":"3",
  "status":"10",
  "buyer":"3",
  "buyerId":"陈莉莉",
  "acceptanceOrderList":[{
    "goodsName":"阿莫西林",
    "lotNo":"批号",
    "specification":"规格",
    "validityDate":"有效期",
    "retialPrice":"零售价",
    "amount":"数量",
    "manufacturer":"AAA公司"
  }],
  "onOrderList":[{}]
}
```
4. 一键完成
* URL     
acceptence/allCompete
* 入参
```
{
  "id":"订单id",
  "buyId":"采购员Id"
}
```
* 出参
无




#### 复核模块
1.出库复核
* URL    
recheck/exWarehouseReview/{billNo}
* 方法
 GET
* 入参   
billNo 销售单号
* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": [
    {
     "billNo":"CG-2017-02-24-0003",
     "priority":"一级",
     "tempArea":"ABC",
     "documentStatus":"已上架",
     "sendRoad":"配送线路",
     "billRemark":"AAAAAAAAAAA",
     "replenishmentOrderCount":"2",
     "wholeQuantity":"10",
     "pxCount":"3",
     "packCount":"5"
    },...
  ]
}
```
2.复核完成
* URL    
recheck/completed
* 方法
 POST
* 入参   
```
{
    "billNo":"CG-2017-02-24-0003",
    "reviewerId":"22",
    "diffRemark":"无差异"
}
```
* 出参
无

