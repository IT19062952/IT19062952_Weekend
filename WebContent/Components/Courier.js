$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{
 the handler, let’s clear the alerts first.
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;

// If valid------------------------
 $("#formItem").submit();

});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide(); 
6
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;

 }
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "CourierAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 }); 
// If valid-------------------------
 $("#formItem").submit();
});

$(document).on("click", ".btnUpdate", function(event)
{
 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
 $("#id").val($(this).closest("tr").find('td:eq(0)').text());
 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#telno").val($(this).closest("tr").find('td:eq(2)').text());
 $("#company").val($(this).closest("tr").find('td:eq(3)').text());
 $("#vehical").val($(this).closest("tr").find('td:eq(3)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
});


// CLIENT-MODEL================================================================
function validateItemForm()
{
// id
if ($("#id").val().trim() == "")
 {
 return "Insert Courier Id.";
 }
// NAME
if ($("#name").val().trim() == "")
 {
 return "Insert Item Name.";
 } 

// telno-------------------------------
if ($("#telno").val().trim() == "")
 {
 return "Insert Item Contact Number.";
 }
// company-------------------------------
if ($("#company").val().trim() == "")
 {
 return "Insert Item Company.";
 }
// vehical-------------------------------
if ($("#vehical").val().trim() == "")
 {
 return "Insert Type of Vehical.";
 }
// email-------------------------------
if ($("#email").val().trim() == "")
 {
 return "Insert Email.";
 }

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}


$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("itemid"));
 $("#id").val($(this).closest("tr").find('td:eq(0)').text());
 $("#name").val($(this).closest("tr").find('td:eq(1)').text());
 $("#telno").val($(this).closest("tr").find('td:eq(2)').text());
 $("#company").val($(this).closest("tr").find('td:eq(3)').text());
 $("#vehical").val($(this).closest("tr").find('td:eq(3)').text());
 $("#email").val($(this).closest("tr").find('td:eq(3)').text());
});

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}
