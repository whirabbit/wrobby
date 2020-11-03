<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2020/10/27
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<% pageContext.setAttribute("ctp",request.getContextPath());%>
<html>
  <head>
    <title>$Title$</title>
      <script src="js/jquery-3.5.1.js"></script>
  </head>
  <body>
<form action="${ctp}/postQR" method="post" id="form">
    文本: <label>
    <input type="text" name="text" placeholder="必填项" id="text">
</label><br>
<%-- 纠错等级:<input name="errorCorrectionLevel" type="text">--%>
    纠错等级:
    <label>
        <select name="errorCorrectionLevel">
            <option value="L" >L</option>
            <option value="M">M</option>
            <option value="Q">Q</option>
            <option value="H" selected>H</option>
        </select>
    </label><br>
 边框长度: <label>
    <input max="4" min="0" type="number" name="margin" value="0">
</label><br>
 边长: <label>
    <input min="0" name="length" type="number" value="100">
</label><br>
    是否添加logo:
    <label>是
        <input type="radio" name="logo" value="true">
    </label>
    <label>否
        <input type="radio" checked name="logo" value="false">
    </label>
    <input id="submit" type="submit" value="Ajax提交">
    <input type="submit">
</form>
  <div id="result">结果
      <img src="" id="img">
  </div>
  <hr>
  <h1>GET方式获取</h1>
  <a  href="getQR?text=测试文本&length=500&logo=true" >get测试</a>
  <p>

      <br>
      <table border="1">
      <caption>地址:www.wrobby.top/${ctp}/getQR?</caption>
      <tr>
          <td>说明</td>
          <td>可选参数及默认值</td>
      </tr>
      <tr>
          <td>文本</td>
          <td>text="你忘了写内容了"</td>
      </tr>
      <tr>
          <td> 纠错等级</td>
          <td>errorCorrectionLevel="H"</td>
      </tr>
      <tr>
          <td>边框长度</td>
          <td>margin=0</td>
      </tr>
      <tr>
          <td>二维码边长</td>
          <td>length=100</td>
      </tr>
      <tr>
          <td>是否添加logo( 暂时只有默认的)</td>
          <td>logo=false</td>
      </tr>
  </table>


  </p>

  </body>
<script>
$(function (){
$("#submit").click(function (){
if (!$("#text").val()){
    alert("请填写必要项");
    return false;
}
    $.ajax({
        data:$("#form").serialize(),
        url:"/MyUtils/postQR",
        method:"POST",
success:function (data){
    $("#img").attr("src","data:image/jpg;base64,"+data)
},
        error: function () {
            alert("失败");
        }

    });
    return false;
});
} )
</script>
</html>
