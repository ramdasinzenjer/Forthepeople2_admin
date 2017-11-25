package com.inzenjer.forthepeople.adapters;


public interface Constants {

    //Progress Message
    String LOGIN_MESSAGE="Logging in...";
    String REGISTER_MESSAGE="Register in...";


   String BASE_URL2="http://192.168.1.13/mla/";
   String BASE_URL="http://192.168.1.13/mla/admin/";

    String LOGIN_URL=BASE_URL+"login.php?";
    String REGISTRATION_URL=BASE_URL+"registration.php?";
    String  MLA_URL=BASE_URL+"mlalist.php?";
    String COMPLAINT_REG_URL=BASE_URL+"complaint_reg.php?";
    String COMPLAINT_REG_URL2=BASE_URL+"complaint_reg2.php?";
    String knowstatus=BASE_URL+"knowmystatus.php?";

    //Details


    String ID="id";
    String NAME="Name";
    String PASSWORD="Password";
    String EMAIL="Email";
    String LOGINSTATUS="LoginStatus";
    String SMSSERVICE="SmsService";
    String CALLSERVICE="CallService";
    String BOOKINGNUMBER="BookingNumber";
    

    //SharedPreference

    String PREFERENCE_PARENT="Parent_Pref";


    String complaints = BASE_URL+"/complaintlist.php";
    String change_status = BASE_URL+"status_update.php";
    String download = BASE_URL2+"uploads/";
    String suggetion = BASE_URL+"suggetion_list";
}
