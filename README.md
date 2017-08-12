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
 oauth/token
* 方法    
 POST
* 入参    
   
参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
accountSet  | String | 否       |账套名|  abc
username   | String | 否       |用户名   |  CLL
password   | String | 否       |密码     |  123456

* 出参    
```json
{
   "result": 0,
   "msg": "登陆成功!",
   "data": {
             "access_token":"73c59e9d-ee16-46c0-9045-c099f93113a4",
             "token_type":"bearer",
             "refresh_token":"edf58afb-88ce-435d-8c27-177a85495738",
             "expires_in":42826,
             "scope":"SCOPE_TRUST"
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
acceptance/unit
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
acceptance/orders/{unitId}
* 方法
GET
* 入参
unitId 单位ID
* 出参
```
{
    "id":"3",
    "name":"CG-2017-02-24-0003"
}
```

3. 获取订单详情
* URL    
acceptance/detail/{orderId}
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
acceptance/allCompete
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
  "data": {
     "billNo":"CG-2017-02-24-0003",
     "priority":"一级",
     "tempArea":"ABC",
     "customer":"湛江市万邦药业有限公司",
     "status":"1"
     "documentStatus":"已上架",
     "sendRoad":"配送线路",
     "billRemark":"AAAAAAAAAAA",
     "replenishmentOrderCount":"2",
     "wholeQuantity":"10",
     "pxCount":"3",
     "packCount":"5"
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


#### 上架入库模块
1.获取上架单号
* URL    
shelve/orderNoList
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

2.获取获取货位
* URL    
shelve/allocationList/{orderNoId}
* 方法    
 GET
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
orderNoId | int     | 否       |订单号id |  5

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

3.获取商品
* URL    
shelve/goodsList/{orderNoId}/{allocationId}
* 方法    
 GET
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
orderNoId  | int     | 否       |订单号id |  5
allocationId | int     | 是     |货位id |  3(默认0)

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

4.获取商品详情列表
* URL    
shelve/goodsDetailList/{orderNoId}
* 方法    
 GET
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
orderNoId  | int     | 否       |订单号id |  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": [
      {
        "orderNoId": 11111(这个商品所以在的订单id),
        "allocationId": 11111(这个商品所以在的订单下的货位id),
        "goodsId": 11111(这个商品id),
        "goodsDetailId": 11111(这个商品详情id),
        "articleNo": "货号",
        "status": "状态",
        "goodsName": "商品名",
        "lotNo": "批号",
        "specification": "规格",
        "productionDate": "生产日期",
        "unit": "单位",
        "amount": "数量",
        "manufacturer": "厂家"
      },...
    ]
}
```

5.全部上架
* URL    
shelve/all
* 方法    
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
orderNoId  | int     | 否       |订单号id |  5
allocationId | int     | 是     |货位id |  2(默认0)
goodsId     | int     | 是     |商品id |  3(默认0)

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": null
}
```

6.单个上架
* URL    
shelve/single
* 方法    
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
goodsDetailId | int | 否       |商品详情id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": null
}
```

7.修改
* URL    
shelve/update
* 方法    
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
goodsDetailId | int | 否       |商品详情id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": null
}
```
#### 单品盘点
1. 获取
* URL
api/productsInventroy/getList/{product}/{location}/{page}/{limit}
* 方法
GET
* 入参
String product // 商品名
String location //货位
int page //当前页数
int limit //每页总数
* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": {
    "total":"总数",
    "list":[{
        "id":"ID",
        "goodsName":"商品名",
        "location":"货位",
        "lotNo":"批号",
        "amount":"数量",
        "validateDate":"有效期",
        "standard":"规格",
        "manufacturer":"厂家"
    },...]
  }
}
```
2. 商品明细
* URL
api/productsInventroy/details/{id}
* 方法
GET
* 入参
id 
*出参
```
{
    "result": 0,
      "msg": "请求成功!",
      "data": {
        {
        "id":"Id",
        "goodsName":"商品名",
        "number":"编号",
        "location":"货位",
        "lotNo":"批号",
        "amount":"数量",
        "unit":"单位",
        "validateDate":"有效期",
        "standard":"规格",
        "manufacturer":"厂家",
        "productDate":"生产日期"
        }
      }
}
```
#### 调拨单
1. 获取list
* URL
api/transfer/list/{}/{}



#### 门店初盘
1.完成
* URL    
shopFirst/complete
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
span      | String | 否       |盘点范围|  月度盘点
wAreaId      | int | 否       |库区id|  5
areaId      | int | 否       |区域id|  5
allocationId| int | 否       |货位id|  5
goodsId | int | 是       |商品id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

2.新增
* URL    
shopFirst/add
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
id       | int | 否       |这条数据的id|  5
batch      | [] | 否       |批次|  {"lotNo":"D33", "amount": 4, "vDate":"2019-7-5"}
batch.lotNo      | String | 否       |批号|  DF44444
batch.amount      | int | 否       |数量|  5
batch.vDate      | String | 否       |有效期至|  2019-7-5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

3.单个完成
* URL    
shopFirst/single
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
goodsDetailId | int | 否       |商品详情id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```


#### 仓库初盘
1.完成
* URL    
warehouseFirst/complete
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
span      | String | 否       |盘点范围|  月度盘点
wAreaId      | int | 否       |库区id|  5
areaId      | int | 否       |区域id|  5
allocationId| int | 否       |货位id|  5
goodsId | int | 是       |商品id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

2.新增
* URL    
warehouseFirst/add
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
id       | int | 否       |这条数据的id|  5
batch      | [] | 否       |批次|  {"lotNo":"D33", "amount": 4, "vDate":"2019-7-5"}
batch.lotNo      | String | 否       |批号|  DF44444
batch.amount      | int | 否       |数量|  5
batch.vDate      | String | 否       |有效期至|  2019-7-5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

3.单个完成
* URL    
warehouseFirst/single
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
goodsDetailId | int | 否       |商品详情id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```


#### 复盘
1.完成
* URL    
secondCount/complete
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
span      | String | 否       |盘点范围|  月度盘点
wAreaId      | int | 否       |库区id|  5
areaId      | int | 否       |区域id|  5
allocationId| int | 否       |货位id|  5
goodsId | int | 是       |商品id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

2.新增
* URL    
secondCount/add
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
id       | int | 否       |这条数据的id|  5
batch      | [] | 否       |批次|  {"lotNo":"D33", "amount": 4, "vDate":"2019-7-5"}
batch.lotNo      | String | 否       |批号|  DF44444
batch.amount      | int | 否       |数量|  5
batch.vDate      | String | 否       |有效期至|  2019-7-5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```

3.单个完成
* URL    
secondCount/single
* 方法
 POST
* 入参   

参数名      | 类型   | 是否可空 | 参数说明 | 样例
-----------|--------|---------|---------|-----
goodsDetailId | int | 否       |商品详情id|  5

* 出参
```
{
  "result": 0,
  "msg": "请求成功!",
  "data": ””
}
```


