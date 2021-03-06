<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>报价</title>
    <link rel="stylesheet" href="../wzBac/lib/layui/css/layui.css">
    <style>
        .wzListH1{
            padding:10px 0 20px 0;
            font-size: 26px;
            color: #333;
        }
        .cityBox{
        position: fixed; pointer-events: auto;top: 19%;
    left: 30%;
    width: 500px;
    height: 400px;
         overflow-y:auto;
        z-index:1001;
       background:#fff;
       padding:15px;
       border-radius:8px;
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
        <div style="padding: 15px;" class="layui-form">
            <h1 class="wzListH1">热门城市</h1>
            <div class="layui-form-item">
            <button class="layui-btn addhotcity">添加热门城市</button>
                
                 <div class="layui-inline" >
	                    <label class="layui-form-label">城市</label>
	                    <div class="layui-input-inline" style="width: 120px;">
	                        <input type="text" id="cityname" autocomplete="off" class="layui-input" >
	                    </div>
	                </div>
	                <div class="layui-inline">
	                    <button class="layui-btn layui-btn-normal" onclick="search()">搜索</button>
	                </div>
            <table class="layui-hide" id="wzTable" lay-filter="testcity"></table>
            <script type="text/html" id="barDemo">
            <button class="layui-btn layui-btn-xs "  lay-event="del">删除</button>
            <button class="layui-btn layui-btn-xs "  lay-event="edit">修改</button>
	</script>
        </div>
    </div>
	<div class="layui-layer-shade cityOver"  style="z-index: 1000; background-color: rgb(0, 0, 0); opacity: 0.3;position: fixed; pointer-events: auto;top: 0;left: 0; width: 100%;height: 100%;display:none;"></div>
  
    <input type="hidden"  name="myid" id="hotid">
    <div class="cityBox layui-form" style="display:none;">
    <div class="layui-form-item">
      <h1 class="">添加/修改</h1>
    </div>
     	
 	 
    	<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">城市名称</label>
		      <div class="layui-input-inline">
		        <input type="tel" name="hotcityname" id="addcityname" lay-verify="required" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">是(1)否(0)热门</label>
		      <div class="layui-input-inline">
		        <input type="text" name="hotname" id="ishot" lay-verify="number" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">排序方式1-5</label>
		      <div class="layui-input-inline">
		        <input type="text" name="sortname" id="issort" lay-verify="number" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    
		 
  		</div>
  		 <div class="layui-form-item">
              <div class="layui-input-block">
             <button class="layui-btn subhotcity" lay-submit lay-filter="formDemo">立即提交</button>
             <button type="reset" class="layui-btn layui-btn-primary">重置</button>
             </div>
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
 

    //JavaScript代码区域
    var arr = [ //标题栏
                 {field: 'sid', title: '序号', width: 80, sort: true,templet:'#zizeng'}
                ,{field: 'hotcityname', title: '热门城市'}
                ,{field: 'citytype', title: '是否热门' ,toolbar: '#barshot'}
                ,{field: 'sort', title: '排序级别'}
                ,{field: 'createtime', title: '创建时间'}
                ,{field: 'temp', title: '操    作',toolbar: '#barDemo'}
            ];
    function search(){
    	
    	var cityname = $("#cityname").val();
         layui.use(['element','table','laydate'], function(){
            var table = layui.table;
            var element = layui.element;
            table.render({
                elem: '#wzTable'
                ,cellMinWidth: 80 
                ,url:'../manageWB/queryhotcity'//全局定义常规单元格的最小宽度，layui 2.2.1 新增
                ,cols: [arr]
                ,where:{city:cityname}
//              ,skin: 'line' //表格风格
                ,even: true
                ,page: true //是否显示分页
              //,limits: [5, 7, 10]
                ,limit: 10 //每页默认显示的数量
            });
            //监听行单击事件（单击事件为：rowDouble）
           
        }); 
    }
   
   
    layui.use(['element','table','laydate','layer'], function(){
    	var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var element = layui.element;
       
        table.render({
             elem: '#wzTable'
            ,cellMinWidth: 80 
             ,url:'../manageWB/queryhotcity'   //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [arr]
           // ,where:{name:company,stime:stime,etime:etime}
//            ,skin: 'line' //表格风格
            ,even: true
            ,page: true //是否显示分页
            //,limits: [5, 7, 10]
            ,limit: 10 //每页默认显示的数量
        });
        //监听行单击事件（单击事件为：rowDouble）
      	$('.addhotcity').click(function(){
      		$("#offerid").val("");
      		$('.cityBox').show();
      		$('.cityOver').show();
      		
      	});
      
      	 //监听行工具事件
        table.on('tool(testcity)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
          var data = obj.data //获得当前行数据
          ,layEvent = obj.event; //获得 lay-event 对应的值
          if(layEvent === 'detail'){
            layer.msg('查看操作');
          } else if(layEvent === 'del'){
            layer.confirm('真的删除行么', function(index){
              obj.del(); //删除对应行（tr）的DOM结构
              layer.close(index);
              //向服务端发送删除指令
              $.ajax({
            	     type:"post"
            	     ,url:"../manageWB/edithotcity"
                     ,dataType:"json"
                     ,data:{id:data.id,status:3}
                     ,success:function(data){
                    	 console.log(data);
                    	  if(data.errorCode != 0){
                    		  layer.msg(data.errorMessage);
                    	  }else{
                    		  layer.msg("删除成功");
                    	 form.render();//需要渲染一下
                    	  }
                    	 
                     }
              })	
              
            });
          } 
        });
      
        
        //监听提交
      /*   form.on('submit(formDemo)', function(data){
          layer.msg("成功");
          // console.log(data);"D:/ma/项目/xiangmu/项目/haosha/work22/gym/gym-data/src/main/java/com/hiko/gym/data/service/DataServiceImpl.java"
          return true;
        }); */
        
        //监听行单击事件（单击事件为：rowDouble）
      	$('.subhotcity').click(function(){
      		
      		var 	url = null;
      		var cityname = $("#addcityname").val();
      		var ishot = $("#ishot").val();
      		var issort = $("#issort").val();
      		var id = $("#hotid").val();
      		
      		
      		if(id == null || id == ""){
      			url = "../manageWB/addhotcity";
      		}else{
      			url = "../manageWB/edithotcity";
      		}
      		if(cityname == null ||ishot== null ){
      			layer.msg('参数不能为空');
      			return false;
      		}
      		
      		 $.ajax({
        	     type:"post"
        	     ,url:url
                 ,dataType:"json"
                 ,data:{id:id,hotcityname:cityname,sort:issort,citytype:ishot}
                 ,success:function(data){
                	 console.log(data);
                	  if(data.errorCode != 0){
                		  layer.msg(data.errorMessage);
                	  }else{
                		  layer.msg("成功");
                		  $('.cityBox').hide();
                    	  $('.cityOver').hide();
                //	 form.render();//需要渲染一下
                    	  window.location.reload();
                	  }
                	 
                 }
          })	
      	});

      	$('.cityOver').click(function(){
      		$('.cityBox').hide();
      		$('.cityOver').hide();
      		
      	});
      
      	
    });

</script>
	<script type="text/html" id="zizeng">
    {{d.LAY_TABLE_INDEX+1}}
	</script>
	<script type="text/html" id="barshot">
        {{#if (d.citytype == 1) { }}
         <span>热门</span>
        {{# }else if(d.citytype == 0){ }}
        <span>正常</span>
        {{# } }}
       
	</script>
	
</body>
</html>