<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>花呗交易记录</title>
    <link rel="stylesheet" href="../wzBac/lib/layui/css/layui.css">
    <style>
        .wzListH1{
            padding:10px 0 20px 0;
            font-size: 26px;
            color: #333;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">

    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->

        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <h1 class="wzListH1">花呗交易记录</h1>
             <div class="layui-form-item">
                 <div class="layui-inline">
                    <label class="layui-form-label">店铺查找</label>
                    <div class="layui-input-inline" style="width: 120px;">
                        <input type="text" id="storeName" autocomplete="off" class="layui-input" >
                    </div>
                    <label class="layui-form-label">交易时间</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="st" placeholder="开始时间">
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="et" placeholder="结束时间">
                    </div>
                </div>
                 <div class="layui-inline">
                    <button class="layui-btn layui-btn-normal" onclick="search()">搜索</button>
                </div>
            </div>
            <table class="layui-hide" id="zcTable" lay-filter="zlcaozuo"></table>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © layui.com - 底部固定区域
    </div>
</div>
<script src="../wzBac/js/jquery-3.2.1.min.js"></script>
<script src="../wzBac/js/common.js"></script>
<script src="../wzBac/lib/layui/layui.js"></script>
<script>
   

    //JavaScript代码区域toolbar: '<div><a href="{{d.picUrl}}" target="_blank ">查看图片</a></div>'
    var arr = [ //标题栏
                 {field: 'id', title: '序号', width: 80, sort: true,templet:'#zizeng'}
                ,{field: 'storeName', title: '门店名称', Width: 40}
                ,{field: 'outTradeNo', title: '订单号',Width: 130}
                ,{field: 'receiptAmount', title: '交易金额', width: 120}
                ,{field: 'fqNum', title: '分期数', width: 120}
                ,{field: 'notifyTime', title: '申请时间', minWidth: 100, sort: true}
                ,{field: 'tradeStatus', title: '交易状态', Width: 50,toolbar:'#issucc'}
                ,{field: 'temp', title: '操    作',toolbar: '#barDemo'}
            ];
    function search(){
        var storeName = $("#storeName").val();
        var stime = $("#st").val();
        var etime = $("#et").val();
        layui.use(['element','table','laydate'], function(){
            var table = layui.table;
            var element = layui.element;
            var laydate = layui.laydate;
            
            laydate.render({
                elem: '#st'
            });
            laydate.render({
                elem: '#et'
            });
            table.render({
                elem: '#zcTable'
                ,cellMinWidth: 80 
                ,url:'../manageWB/zanClickLog'    //全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [arr]
                ,where:{storeName:storeName,stime:stime,etime:etime}
//                ,skin: 'line' //表格风格
                ,even: true
                ,page: true //是否显示分页
                ,limits: [10, 20, 30]
                ,limit: 10 //每页默认显示的数量
            });
            //监听行单击事件（单击事件为：rowDouble）
           
        });
    }
   
    	
    layui.use(['element','table','laydate'], function(){
        var table = layui.table;
        var element = layui.element;
        var laydate = layui.laydate;
        
        var fq0=$("#fq0").val();
        var fq6 = $("#fq6").val();
        var fq12 = $("#fq12").val();
        var fq24 = $("#fq24").val();
        
        
        laydate.render({
            elem: '#st'
        });
        laydate.render({
            elem: '#et'
        });
        table.render({
            elem: '#zcTable'
            ,cellMinWidth: 80 
            ,url:'../manageWB/zanClickLog'    //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [arr]
           // ,where:{name:company,stime:stime,etime:etime}
//            ,skin: 'line' //表格风格
            ,even: true
            ,page: true //是否显示分页
            //,limits: [5, 7, 10]
            ,limit: 10 //每页默认显示的数量
        });
        
        
        //监听行单击事件（单击事件为：rowDouble）
     	 //监听行工具事件
        table.on('tool(zlcaozuo)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
          var data = obj.data //获得当前行数据
          ,layEvent = obj.event; //获得 lay-event 对应的值
          if(layEvent === 'edit'){
        	  layer.msg("已发送");
          } else if(layEvent === 'send'){
        	 
              //向服务端发送删除指令
              $.ajax({
            	     type:"post"
            	     ,url:"../manageWB/sendWealth"
                     ,dataType:"json"
                     ,data:{StoreId:data.storeId,trade_no:data.tradeNo,fq0:fq0,fq6:fq6,fq12:fq12,fq24:fq24}
                     ,success:function(data){
                    	 console.log(data);
                    	  if(data.errorCode != 0){
                    		  layer.msg(data.errorMessage);
                    	  }else{
                    		 
              		          table.reload('zcTable', {
              		        	 page: {
              		               curr: data.page //重新从第 1 页开始
              		             }
              		          });
                    		  layer.msg("发送成功");
                    	 //form.render();//需要渲染一下
                    	//	  window.location.reload();
                    	  }
                    	 
                     }
              })	
              
    
          } 
        });
        
        
        
    });
   
</script>
    <script type="text/html" id="barDemo">
      
	</script>
<script type="text/html" id="issucc">
        {{#if (d.tradeStatus == "TRADE_SUCCESS") { }}
         <span>交易成功</span>
        {{# }else if(d.tradeStatus == "TRADE_FINISHED"){ }}
        <span>交易成功不退款</span>
        {{# }else if(d.tradeStatus == "SEND_WEALTH_SUCCESS"){ }}
        <span>已发送财富</span>
       {{# }else if(d.tradeStatus == "REFUND_SUCCESS"){ }}
        <span>已退款</span>
        {{# } }}
       
</script>
<script type="text/html" id="zizeng">
    {{d.LAY_TABLE_INDEX+1}}
</script>
</body>
</html>