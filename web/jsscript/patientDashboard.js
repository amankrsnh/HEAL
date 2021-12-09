$(getProfileForPid);

function getProfileForPid(){
    let xhr=$.post("GetPatientControllerServlet",processOnReadyResponse);
    xhr.fail(handleError);
}
function processOnReadyResponse(responseText)
{
    $(".profiles").empty().append(responseText);
    swal("Success","Your profile loaded successfully","success");
}

function handleError(xhr)
{
    swal("Error","Problem in Server Communication :"+xhr.statusText,"error");
}


function updatePatient()
{
    console.log("hello update Patient.");
    let ppassword=$("#password").val(),paddress=$("#address").val(),pblood_group=$("#bg").val(),pphone=$("#phone").val(),pdob=$("#dob").val(),pgender=$('#gender').find(":selected").text();;
    
    let data={
        password:ppassword,
        address:paddress,
        bg:pblood_group,
        phone:pphone,
        dob:pdob,
        gender:pgender
    };
    console.log("hello after update in same.");
    console.log(data);
    let xhr=$.post("UpdatePatientControllerSevlet",data,updateResponse);
    xhr.fail(handleError);
    
}
    
function updateResponse(responseText)
{
    let str=responseText.trim();
    console.log(str);
    if(str==="success")
    {
        swal("Success","Updated Succesfully","success");
    }
    else
        swal("Error", "Some problem Occured", "error");
}

$(".appoints").ready(function () {
  $('#didppointments').DataTable();
  $('.dataTables_length').addClass('bs-select');
});

function getAppointment()
{
    let xhr=$.post("GetAppointmentControllerServlet",appointmentResponse);
    xhr.fail(handleError);
}

function appointmentResponse(responseText){
    $(".appointment").empty().append(responseText);
    swal("Success","AppointmentDetails Loaded Successfully.","success");
}

function getDoctorsforpid()
{
    let xhr=$.post("ShowAllDoctorsServlet",doctorsResponse)
    xhr.fail(handleError);
}

function doctorsResponse(responseText)
{
    $(".addDoctors").empty().append(responseText);
    swal("Success","Doctors List Loaded Successfully.","success");
}