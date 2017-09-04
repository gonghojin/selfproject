<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "sf" uri = "http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<sf:form id="signUpForm" action="signUp" method="post" commandName="user" 
        onsubmit="return check();">
<sf:errors path="*" cssClass="error" />
<table>
<tr>
    <td style="width: 200px;">이름(Full Name)</td>
    <td style="width: 390px">
        <sf:input path="uname" /><br />
        <sf:errors path="uname" cssClass="error" />
    </td>
</tr>
<tr>
    <td>비밀번호(Password)</td>
    <td>
        <sf:password path="upw" /><br />
        <sf:errors path="upw" cssClass="error" />
    </td>
</tr>
<tr>
    <td colspan="2" style="text-align: center;font-weight: bold;">
        Email이 아이디로 쓰이므로 비밀번호는 Email계정 비밀번호와 같게 하지 마세요.
    </td>
</tr>
<tr>
    <td>비밀번호 확인(Confirm)</td>
    <td><input type="password" name="confirm" /></td>
</tr>
<tr>
    <td>Email</td>
    <td>
        <sf:input path="uid" /><br />
        <sf:errors path="uid" cssClass="error" />
    </td>
</tr>

</table>
<div style="text-align: center;padding-bottom: 15px;">
    <input type="submit" value="확인" />
</div>
</sf:form>
</table>
</body>
</html>