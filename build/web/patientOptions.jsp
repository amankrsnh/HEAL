<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- bootstrape css-->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!--Fontawesome  css-->
     <link rel="stylesheet" href="css/all.min.css">
     <!--Fontawesome  css-->
    <link rel="stylesheet" href="css/custom.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
    <link href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css" rel="stylesheet"/>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <style>
        .table-striped tbody tr:nth-child(odd) td,
        .table-striped tbody tr:nth-child(odd) th 
        {
            background-color: #DFF0D8;
        }
    </style>
    <title>Patient Dashboard</title>
</head>
<body>
    <!--- top navbar-->
    <nav class="navbar navbar-dark fixed-top bg-info flex-md-nowrap p-0 shadow">
        <a href="home.html" class="navbar-brand col-sm-3 col-md-2">Heal</a>
    </nav>
     <!--- end  navbar-->

     
     <div class="container-fluid mt-5">
         <div class="row">
             <nav class="col-sm-2 bg-light sidebar py-3"><!--side bar--->
                 <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item"><a class="nav-link" href="javascript:getProfileForPid()"><i class="fas fa-briefcase-medical mr-2"></i>Patient's Profile</a></li>
                        <li class="nav-item"><a class="nav-link" href="javascript:getAppointment()"><i class="fas fa-calender-check mr-2"></i>Appointments</a></li>
                        <li class="nav-item"><a class="nav-link" href="javascript:getDoctorsforpid()"><i class="fas fa-eye mr-2"></i>View Doctors</a></li>
                        <li class="nav-item"><a class="nav-link" href="#"><i class="fas fa-sign-out-alt mr-2"></i>Logout</a></li>
                   </ul>
                </div>
             </nav><!--end side bar--->

             <div class="profile col-sm-6 mt-5"><!--profile area--->
                 <form class="profiles mx-5" action="javascript:updatePatient()">
                 </form>
                 <div class="appointment col-sm-12 mt-5">
                     
                 </div>
                 <div class="doctors col-sm-12 mt5">
                    <div id="wrap">
                        <div class="container">
                            <table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
                                <thead>
                                    <tr>
                                            <th>Name</th>
                                            <th>Email</th>
                                            <th>Gender</th>
                                            <th>Hospital</th>
                                            <th>Specialization</th>
                                    </tr>
                                </thead>
                                <tbody class="addDoctors">
                                    
                                </tbody>
                            </table>
                        </div>
                    </div>
                 </div>
             </div><!--end profile--->
         </div>
     </div>

    <!--- end sidebar-->
    <script>
        $(".chosen-select").chosen({
            no_results_text: "Oops, nothing found!"
          });
    </script>

    <script src="jsscript/patientDashboard.js"></script>


       <!-- bootstrape jss-->
       <script src="js/jquery.min.js"></script>
       <script src="js/popper.min.js"></script>
       <script src="js/bootstrap.min.js"></script>
       <script src="js/all.min.js"></script>
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.9.4/jquery.dataTables.min.js"></script>
</body>
</html>