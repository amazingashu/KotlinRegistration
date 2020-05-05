<?php
define('HOST', 'localhost');
define('USER', 'root');
define('PASS', '');
define('DB', 'dbname');


	
	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
//checking if the script received a post request or not 
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	if (function_exists('date_default_timezone_set')) {

    date_default_timezone_set('Asia/Kolkata');

}

$CreatedDate = date('d-m-Y h:i:s');

		//Getting post data 
		$Email= $_POST['Email'];
		$Password= $_POST['Password'];
		
	
		//checking if the received values are blank
		if($Email== '' || $Password== ''){
			//giving a message to fill all values if the values are blank
			echo 'please fill all values';
		}else{
			//If the values are not blank
			//Connecting to our database by calling dbConnect script 
				$checkuser = "Select * from `tblUsers` where `Email`='".$Email."' and `Password`='".md5($Password)."'";
				
				$result=mysqli_query($con,$checkuser);
				
				 $rowcount=mysqli_num_rows($result);
						
				if($rowcount==1)
				{
					while($row = $result->fetch_assoc()) 
					{
					$Name = preg_replace('/\s+/', '', $row["Name"]);
			        //echo $user_login."-".$row["ID"]."-".$row["user_email"]."-".$row["user_phone"];
					echo $Name."-".$row["UserId"];
    				}
				}
				else
				{
					echo "LoginFailed-0";
				}
			}
			//Closing the database connection 
			mysqli_close($con);
		
}else{
echo 'error';
}
?>
