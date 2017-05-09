<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Java开发人工智能扫一扫人脸识别系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	     *{margin: 0; padding: 0;}
	     body{background:#e6e6e6; font-size: 12px; font-family:"微软雅黑"; color;}
	     /*h1 S*/
	     h1{line-height:80px; text-align: center; font-weight: 300; color:#000000;}
	     /*h1 E*/
	     
	     .banner{width:100%;height: 230px; background:url("") top center;}
	     
	     .upload{width:180px; height: 36px; display:block; margin: 30px auto; text-align:center;line-height:36px;text-decoration:none; font-size:12px;
	            radius:20px; border:1px solid #00b4ff; color: #00b4ff;}
	     .upload:hover{background: #00b4ff;color: #ffffff;}
	     
	     .photo{width:800px; height:460px;margin: 0 auto;}
	     .photo .p_box{width:505px; height:460px;background:#ffcc99;float:left; position:relative;}
	     .photo .p_value{width:290px;height:460px;background: #ffffff;float:right;}
	     .photo .p_value h2{font-size:24px; font-weight: 500;text-align: center;line-height:120px;}
	     .photo .p_value .p_info{font-size: 16px;padding-left:35px;line-height:50px;}
	
	     @-webkit-keyframes renlian{
	       from {height:5px;}
	       10% {height:5px;}
	       40% {height:50px;}
	       50% {height:150px;}
	       60% {height:250px;}
	       80% {height:460px;}
	       60% {height:250px;}
	       50% {height:150px;}
	       40% {height:100px;}
	       10% {height:50px;}
	       to {height:0px;}
	     }
	     
	     .scale{animation:renlian 1.5s infinite ease; -webkit-animation:renlian 1.5s infinite ease;}
	     .bs {position:absolute; background: green; width: 505px; height: 460px; top: 0px; left: 0px;font-size: 36px;text-align: center;line-height:400px;color: #fff;opacity:.3;}
	     #file, #filename{display:none;}
	</style>
  </head>
  
  <body>
       <h1>Java开发人工智能反黄识别系统</h1>
       
       <div class="banner"></div>
       
       <form action="upload.jsp" method="post" enctype="multipart/form-data" id="arryForm">
           <a href="javascript:;" class="upload" id="upload_btn" >上传照片</a><!-- onclick="openBrows();" -->
           <input type="file" id="file" name="file" onchange="saveFile();" />
           <input type="text" id="filename" />   
       </form>

       <div class="photo">
          <div class="p_box">
             <img alt="arry老师" src="${empty fileName ? 'img/1.jpg' : fileName }" id="path" width="505" >
             <div class="bs scale"></div>
          </div>
          <div class="p_value">
             <h2>人脸识别扫描结果：</h2>
             <p class="p_info" id="p_message">
                  <!--  年龄：31岁（误差范围：12岁）<br />
                   性别：Male（正确率：99.9999%）<br />
                   种族：White（正确率：78.0801%）<br />
                   正在小：2.49521% -->
             </p>
          </div>
       </div>
       
       <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
       <script type="text/javascript">
       //页面加载完就执行
          $(function(){
        	  //点击上传按钮
        	  $("#upload_btn").click(function(){
        		  var ie=navigator.appName =="Microsoft Internet Explorer" ? true:false;
        		  if(ie){
        			  document.getElementById("file").click();
        			  document.getElementById("filename").value=document.getElementById("filename").value;
        		  } else{
        			  var a=document.createEvent("MouseEvents");
        			  a.initEvent("click",true,true);
        			  document.getElementById("file").dispatchEvent(a);
        		  }  
        	  });
        	  //执行
        	  faceDo();
        	  
          });
         //上传图像 2：13：00
    	 function saveFile(){
    		document.getElementById("arryForm").submit();
    	 }
         //人脸识别
         function faceDo(){
        	 //alert("执行");
        	 var msg=$("#path").attr("src");
        	 $.ajax({
        		 type:"post",
        		 url:"faceData.jsp",
        		 data:{"path":msg},
        		 success:function(data){
        			/*  $("#p_message").prepend(data);
        			 $(".bs").removeClass().empty(); */
        			 alert("进入");
        			 var result=$.parseJSON(data);
        			 console.log(data);
        			 var num=[];
        			 var name=[];
        			 var array=result.result;
        			 array.forEach(function(value,index,array){
        				 name.push(value.class_name);
        				 num.push(value.probability);
        			 },array);
        			 var index=num.indexOf(Math.max.apply(null,num));
        			 var obj=$("<div class='text'>"+name[index]+"</div>");
        			 $(".p_box").append(obj);
        		 }
        	 });
         }
       
       </script>



  </body>
</html>