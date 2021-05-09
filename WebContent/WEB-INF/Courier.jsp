<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
    
    <%//Save---------------------------------
    if (request.getParameter("id") != null)
    {
     Courier courier = new Courier();
     String stsMsg = "";
    //Insert--------------------------
    if (request.getParameter("hidItemIDSave") == "")
     {
     stsMsg = courier.insertItem(request.getParameter("id"),
     request.getParameter("name"),
     request.getParameter("telno"),
     request.getParameter("company"),
     request.getParameter("vehical"),
     request.getParameter("email"));
     }
    else//Update----------------------
     {
     stsMsg = courier.updateItem(request.getParameter("hidItemIDSave"),
    		 request.getParameter("name"),
    	     request.getParameter("telno"),
    	     request.getParameter("company"),
    	     request.getParameter("vehical"),
    	     request.getParameter("email"));
     }
     session.setAttribute("statusMsg", stsMsg);
    }
    //Delete-----------------------------
    if (request.getParameter("hidItemIDDelete") != null)
    {
    	 Courier courier = new Courier();
     String stsMsg =
     courier.deleteItem(request.getParameter("hidItemIDDelete"));
     session.setAttribute("statusMsg", stsMsg);
    }
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Courier Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jQuery-3.2.1.min.js"></script>
<script src="Components/Courier.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Courier Details</h1>
<form id="formItem" name="formItem">
 Courier ID:
 <input id="id" name="id" type="text"
 class="form-control form-control-sm">
 <br> Name:
 <input id="name" name="name" type="text"
 class="form-control form-control-sm">
 <br> Contact Number:
 <input id="telno" name="telno" type="text"
 class="form-control form-control-sm">
 <br> Company:
 <input id="company" name="company" type="text"
 class="form-control form-control-sm">
 <br>
 <br> Vehical :
 <input id="vehical" name="vehical" type="text"
 class="form-control form-control-sm">
 <br>
 <br> Email:
 <input id="email" name="email" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave"
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Courier courier = new Courier();
 out.print(courier.readItems());
 %>
</div>
</div> </div> </div>
</body>
</html>