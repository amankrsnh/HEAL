<%-- 
    Document   : home
    Created on : 21 Nov, 2021, 1:36:31 PM
    Author     : Aman Kumar Singh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob,heal.dto.Patients,heal.dto.Doctors,java.util.ArrayList,java.io.IOException,java.io.InputStream,java.io.ByteArrayOutputStream,java.util.Base64"  %>

<%
        String userid=(String)session.getAttribute("id");
        String usertype=(String)session.getAttribute("usertype");
        System.out.println(userid+" : "+usertype);
        if(userid==null)
        {
            session.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        String script=null,profile=null,view=null,div=null;
        Blob image=null;
        if(usertype.equalsIgnoreCase("doctor"))
        {
            usertype="Patients";
            Doctors d=(Doctors)session.getAttribute("image");
            image=d.getdImage();
            script="jsscript/dashboard.js";
            profile="javascript:getProfileForDid()";
            view="javascript:getPatientsfordid()";
            div="<div class='tables'>"+
                    "<div class='container'>"+
                    "<center><div class='profile col-sm-10 mt-5'>"+
                "<div class='pro col-sm-12 mt-5'>"+

                "</div>"+
                    "<div class='appointment col-sm-12 mt-5'>"+

                "</div>"+
                "<div class='patients col-sm-12 mt5'>"+

                "</div>"+
            "</div>"+
            "</center>"+
            "</div>"+
        "</div>";
        }
        else if(usertype.equalsIgnoreCase("patient"))
        {
            usertype="Doctors";
            Patients p=(Patients)session.getAttribute("image");
            image=p.getpImage();
            script="jsscript/patientDashboard.js";
            profile="javascript:getProfileForPid()";
            view="javascript:getDoctorsforpid()";
            div="<div class='tables'>"+
                    "<div class='container'>"+
                    "<center><div class='profile col-sm-10 mt-5'>"+
                 "<form class='profiles mx-5' action='javascript:updatePatient()'>"+
                 "</form>"+
                 "<div class='appointment col-sm-12 mt-5'>"+
                     
                 "</div>"+
                 "<div class='doctors col-sm-12 mt5'>"+
                    "<div id='wrap'>"+
                        "<div class='container'>"+
                            "<h1>Doctors List</h1><table cellpadding='0' cellspacing='0' border='0' class='datatable table table-striped table-bordered'>"+
                                "<thead>"+
                                    "<tr>"+
                                            "<th>Name</th>"+
                                            "<th>Email</th>"+
                                            "<th>Gender</th>"+
                                            "<th>Hospital</th>"+
                                            "<th>Specialization</th>"+
                                            "<th>Rating</th>"+
                                    "</tr>"+
                                "</thead>"+
                                "<tbody class='addDoctors'>"+
                                    
                                "</tbody>"+
                            "</table>"+
                        "</div>"+
                    "</div>"+
                 "</div>"+
             "</div>"+
                    "</center>"+
            "</div>"+
        "</div>";
        }
        System.out.println(script+" : "+profile+" : "+view);
        String img=null;
        if(image!=null)
        {
            InputStream inputStream = image.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);                  
            }
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            inputStream.close();
            outputStream.close();

            img="data:image/jpg;base64,"+base64Image;
        }
        else
        {
            img="images/744709.png";
        }
    %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Heal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">
    
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    
	<style>
		.logo-txt {
			background-color: #f3ec78;
			background-image: linear-gradient(180deg, #18d137, #2888c9);
			background-size: 100%;
			-webkit-background-clip: text;
			-moz-background-clip: text;
			-webkit-text-fill-color: transparent;
			-moz-text-fill-color: transparent;
			font-weight: bold;
		}
                .navbar{
                        margin-bottom: 0px;
                }
                
	</style>
        <script src="<%= script %>"></script>
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

  </head>
  <body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">
    
      <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light site-navbar-target" id="ftco-navbar" style="margin-bottom:0;" >
	    <div class="container">
	      <a class="navbar-brand" href="home.jsp"><h1 class="logo-txt">Heal</h1></a>
	      <button class="navbar-toggler js-fh5co-nav-toggle fh5co-nav-toggle" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav nav ml-auto">
	          <li class="nav-item h"><a href="#" class="nav-link"><span>Home</span></a></li>
                  <li class="nav-item p"><a href="<%= profile %>" class="nav-link"><span>Profile</span></a></li>
                  <li class="nav-item u"><a href="<%= view %>" class="nav-link"><span><%= usertype %></span></a></li>
	          <li class="nav-item cs"><a href="QuickCheck-master/index.html" class="nav-link"><span>Check Symptoms</span></a></li>
	          <li class="nav-item nh"><a href="#doctor-section" class="nav-link"><span>Nearby HealthCare</span></a></li>
	          <li class="nav-item ap"><a href="javascript:getAppointment()" class="nav-link"><span>Appointments</span></a></li>
	          <li class="nav-item cta mr-md-2"><a href="index.html" id="login-txt" class="nav-link">Logout</a></li>
                </ul>                  
	      </div>
              <ul style="list-style-type:none">
                  <li>
                    <span class="login100-form-avatar">
                        <img src="<%= img %>" alt="AVATAR" width="65" height="65">
                    </span>
                  </li>
                </ul>
	    </div>
	  </nav>
        <%= div %>
	<section class="every hero-wrap js-fullheight" style="background-image: url('images/bg_3.jpg');" data-section="home" data-stellar-background-ratio="0.5">
            <div class="every overlay"></div>
            <div class="every container">
                <div class="every row no-gutters slider-text js-fullheight align-items-center justify-content-start" data-scrollax-parent="true">
                    <div class="every col-md-6 pt-5 ftco-animate">
                        <div class="every mt-5">
                            <span class="subheading">Welcome to Heal</span>
                            <h1 class="mb-4">What concerns you about your health today?</h1>
                            <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove.</p>
                            <p><a href="QuickCheck-master/index.html" class="btn btn-primary py-3 px-4">Check your Symptoms</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

		<section class="every ftco-counter img ftco-section ftco-no-pt ftco-no-pb" id="about-section">
    	<div class="every container">
    		<div class="row d-flex">
    			<div class="col-md-6 col-lg-5 d-flex">
    				<div class="img d-flex align-self-stretch align-items-center" style="background-image:url(images/about.jpg);">
    				</div>
    			</div>
    			<div class="col-md-6 col-lg-7 pl-lg-5 py-md-5">
    				<div class="py-md-5">
	    				<div class="row justify-content-start pb-3">
			          <div class="col-md-12 heading-section ftco-animate p-4 p-lg-5">
			            <h2 class="mb-4">We Are <span>Heal</span> A Medical Clinic</h2>
			            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
                                    <p class="mkapt"><a href="javascript:getDoctors()" class="btn btn-primary py-3 px-4">Make an appointment</a></p>
			          </div>
			        </div>
		        </div>
	        </div>
        </div>
    	</div>
    </section>


		<section class="every ftco-section ftco-no-pt ftco-no-pb ftco-services-2 bg-light">
			<div class="every container">
        <div class="row d-flex">
	        <div class="col-md-12 py-5">
	        	<div class="py-lg-5">
		        	<div class="row justify-content-center pb-5">
			          <div class="col-md-12 heading-section ftco-animate">
			            <h2 class="mb-3">Our Services</h2>
			          </div>
			        </div>
			        <div class="row">
			        	<div class="col-md-6 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services d-flex">
			              <div class="icon justify-content-center align-items-center d-flex"><span class="flaticon-ambulance"></span></div>
			              <div class="media-body pl-md-4">
			                <h3 class="heading mb-3">Symptoms Checker</h3>
			                <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
			              </div>
			            </div>      
			          </div>
			          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services d-flex">
			              <div class="icon justify-content-center align-items-center d-flex"><span class="flaticon-doctor"></span></div>
			              <div class="media-body pl-md-4">
			                <h3 class="heading mb-3">Qualified Doctors</h3>
			                <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
			              </div>
			            </div>      
			          </div>
			          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services d-flex">
			              <div class="icon justify-content-center align-items-center d-flex"><span class="flaticon-stethoscope"></span></div>
			              <div class="media-body pl-md-4">
			                <h3 class="heading mb-3">Book Appoinment</h3>
			                <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
			              </div>
			            </div>      
			          </div>
			          <div class="col-md-6 d-flex align-self-stretch ftco-animate">
			            <div class="media block-6 services d-flex">
			              <div class="icon justify-content-center align-items-center d-flex"><span class="flaticon-24-hours"></span></div>
			              <div class="media-body pl-md-4">
			                <h3 class="heading mb-3">24 Hours Service</h3>
			                <p>A small river named Duden flows by their place and supplies it with the necessary regelialia.</p>
			              </div>
			            </div>      
			          </div>
			        </div>
			      </div>
		      </div>
		    </div>
			</div>
		</section>

    <section class="every ftco-intro img" style="background-image: url(images/bg_2.jpg);">
			<div class="every overlay"></div>
			<div class="every container">
				<div class="row justify-content-center">
					<div class="col-md-9 text-center">
						<h2>Your Health is Our Priority</h2>
						<p>We can manage your dream building A small river named Duden flows by their place</p>
						<p class="mb-0"><a href="#" class="btn btn-white px-4 py-3">Search Nearby Healthcare</a></p>
					</div>
				</div>
			</div>
		</section>

		<section class="every ftco-section ftco-no-pt ftco-no-pb" id="department-section">
    	<div class="every container-fluid px-0">
    		<div class="row no-gutters">
    			<div class="col-md-4 d-flex">
    				<div class="img img-dept align-self-stretch" style="background-image: url(images/dept-1.jpg);"></div>
    			</div>

    			<div class="col-md-8">
    				<div class="row no-gutters">
    					<div class="col-md-4">
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Neurology</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Surgical</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Dental</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    					</div>

    					<div class="col-md-4">
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Opthalmology</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Cardiology</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Traumatology</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    					</div>

    					<div class="col-md-4">
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Nuclear Magnetic</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">X-ray</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    						<div class="department-wrap p-4 ftco-animate">
    							<div class="text p-2 text-center">
    								<div class="icon">
    									<span class="flaticon-stethoscope"></span>
    								</div>
    								<h3><a href="#">Cardiology</a></h3>
    								<p>Far far away, behind the word mountains</p>
    							</div>
    						</div>
    					</div>
    				</div>
    			</div>
    		</div>
    	</div>
    </section>
	
    <section class="every ftco-section testimony-section img" style="background-image: url(images/bg_3.jpg);">
    	<div class="every overlay"></div>
      <div class="every container">
        <div class="row justify-content-center pb-3">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
          	<span class="subheading">Read testimonials</span>
            <h2 class="mb-4">Our Patient Says</h2>
          </div>
        </div>
        <div class="every row ftco-animate justify-content-center">
          <div class="col-md-12">
            <div class="carousel-testimony owl-carousel ftco-owl">
              <div class="item">
                <div class="testimony-wrap text-center py-4 pb-5">
                  <div class="user-img" style="background-image: url(images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text px-4">
                    <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <p class="name">Jeff Freshman</p>
                    <span class="position">Patients</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap text-center py-4 pb-5">
                  <div class="user-img" style="background-image: url(images/person_2.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text px-4">
                    <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <p class="name">Jeff Freshman</p>
                    <span class="position">Patients</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap text-center py-4 pb-5">
                  <div class="user-img" style="background-image: url(images/person_3.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text px-4">
                    <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <p class="name">Jeff Freshman</p>
                    <span class="position">Patients</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap text-center py-4 pb-5">
                  <div class="user-img" style="background-image: url(images/person_1.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text px-4">
                    <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <p class="name">Jeff Freshman</p>
                    <span class="position">Patients</span>
                  </div>
                </div>
              </div>
              <div class="item">
                <div class="testimony-wrap text-center py-4 pb-5">
                  <div class="user-img" style="background-image: url(images/person_3.jpg)">
                    <span class="quote d-flex align-items-center justify-content-center">
                      <i class="icon-quote-left"></i>
                    </span>
                  </div>
                  <div class="text px-4">
                    <p class="mb-4">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <p class="name">Jeff Freshman</p>
                    <span class="position">Patients</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="apt hero-wrap hero-wrap-2" style="background-image: url('images/bg_1.jpg');" data-stellar-background-ratio="0.5">
      <div class="overlay"></div>
      <div class="container">
        <div class="row no-gutters slider-text align-items-end justify-content-start">
          <div class="col-md-9 ftco-animate pb-4">
            <h1 class="mb-3 bread">Appointment</h1>
             <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="ion-ios-arrow-forward"></i></a></span> <span>Appointment <i class="ion-ios-arrow-forward"></i></span></p>
          </div>
        </div>
      </div>
    </section>

    

    <section class="ftco-section" id="doctor-section">
		<div class="container-fluid px-5">
			<div class="row justify-content-center mb-5 pb-2">
	  <div class="col-md-8 text-center heading-section ftco-animate">
		<h2 class="mb-4">Our Qualified Doctors</h2>
		<p>Separated they live in. A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country</p>
	  </div>
	</div>	
			<div class="doctorslist row">
							
			</div>
		</div>
	</section>

		

    <footer class="ftco-footer ftco-section img" style="background-image: url(images/footer-bg.jpg);">
    	<div class="overlay"></div>
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-md">
            <div class="ftco-footer-widget mb-2">
              <h1 class="ftco-heading-2 logo-txt">Heal</h1>
              <p>Far far away, behind the word mountains, far from the countries.</p>
              <ul class="ftco-footer-social list-unstyled mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-2 ml-md-4">
              <h2 class="ftco-heading-2">Departments</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Neurology</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Opthalmology</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Nuclear Magnetic</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Surgical</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Cardiology</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Dental</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-2 ml-md-4">
              <h2 class="ftco-heading-2">Links</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Home</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>About</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Symptoms Checker</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Nearby HealthCare</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Appoinment</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Contact</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-2">
              <h2 class="ftco-heading-2">Services</h2>
              <ul class="list-unstyled">
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Emergency Services</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Qualified Doctors</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>Outdoors Checkup</a></li>
                <li><a href="#"><span class="icon-long-arrow-right mr-2"></span>24 Hours Services</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-2">
            	<h2 class="ftco-heading-2">Have a Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope pr-4"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

  <script>
      $(function()
{
    $(".tables").hide();
    $(".apt,#doctor-section").hide();
    $(".mkapt").on('click',function(){
        $(".apt,#doctor-section").show();
        $(".tables").hide();
        $(".every").hide();
    });
    $(".p").on('click',function(){
        $(".tables").show();
        $(".every").hide();
        $(".pro").show();
        $("form.profiles").show();
        $(".appointment").hide();
        $(".patients").hide();
        $(".doctors").hide();
        $(".apt,#doctor-section").hide();
    });
    $(".u").on('click',function(){
        $(".tables").show();
        $(".every").hide();
        $(".appointment").hide();
        $("form.profiles").hide();
        $(".patients").show();
        $(".doctors").show();
        $(".pro").hide();
        $(".apt,#doctor-section").hide();
    });
    $(".ap").on('click',function(){
        $(".tables").show();
        $("form.profiles").hide();
        $(".every").hide();
        $(".appointment").show();
        $(".patients").hide();
        $(".doctors").hide();
        $(".pro").hide();
        $(".apt,#doctor-section").hide();
    });
    $(".h").on('click',function(){
        $(".tables").hide();
        $(".every").show();
        $(".appointment").hide();
        $(".patients").hide();
        $(".pro").hide();
        $(".apt,#doctor-section").hide();
    });
    $(".cs").on('click',function(){
        $(".tables").hide();
        $(".every").show();
        $(".appointment").hide();
        $(".patients").hide();
        $(".pro").hide();
        $(".apt,#doctor-section").hide();
    });
    $(".nh").on('click',function(){
        $(".tables").hide();
        $(".every").show();
        $(".appointment").hide();
        $(".patients").hide();
        $(".pro").hide();
        $(".apt,#doctor-section").hide();
    });
});
  </script>
  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  
  <script src="js/main.js"></script>
    <script src="jsscript/appointment.js"></script>
  </body>
</html>