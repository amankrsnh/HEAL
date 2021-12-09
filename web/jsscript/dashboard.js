

function getProfileForDid(){
    let xhr=$.post("GetDoctorControllerServlet",processOnReadyResponse);
    xhr.fail(handleError);
}
function processOnReadyResponse(responseText)
{
    $(".pro").empty().append(responseText);
    swal("Success","Your profile loaded successfully","success");
}

function handleError(xhr)
{
    swal("Error","Problem in Server Communication :"+xhr.statusText,"error");
}


function updateDoctor()
{
    let dpassword=$("#password").val(),dspecialisation=$("#spe").val(),dhospital=$("#address").val(),dphone=$("#phone").val();
    let data={
        password:dpassword,
        specialisation:dspecialisation,
        hospital:dhospital,
        phone:dphone,
        profile:"true"
    };
    let xhr=$.post("UpdateDoctorControllerSevlet",data,updateResponse);
    xhr.fail(handleError);
    
}

function updateDoctorSchedule()
{
    let available=$("#available").is(":checked");
    let arr=Array.from(document.getElementById("weekdays").options).filter(option => option.selected).map(option => option.value);
    let weekdays = arr.reduce(myFunction),stime=$("#stime").val(),etime=$("#etime").val(),fees=parseInt($("#fees").val()),seats=parseInt($("#seats").val());
    let data={
        available:available,
        weekdays:weekdays,
        stime:stime,
        etime:etime,
        fees:fees,
        seats:seats,
        profile:"false"
    };
    console.log(data);
    let xhr=$.post("UpdateDoctorControllerSevlet",data,updateResponse);
    xhr.fail(handleError);
    
}

function myFunction(total, value, index, array) {
      return total + value;
    }
    
function updateResponse(responseText)
{
    let str=responseText.trim();
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

function getPatientsfordid()
{
    let xhr=$.post("ShowPatientsControllerServlet",patientsResponse)
    xhr.fail(handleError);
}

function patientsResponse(responseText)
{
    $(".patients").empty().append(responseText);
    swal("Success","Patient Details Loaded Successfully.","success");
}