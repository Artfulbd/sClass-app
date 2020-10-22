<?php
// required headers
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: GET");
    header("Access-Control-Allow-Credentials: true");
    header('Content-Type: application/json');
    
  
    
    // get database connection
    $link = mysqli_connect('localhost' , 'root' , '', 'sclass') or die("cannot connect");   

    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $cid = $data->classid; 

    // query to read single record
    $query = "SELECT sid FROM `class_list` WHERE classid=(SELECT classid FROM `class_list` WHERE sid=$id and classid = $cid)";
    $row = mysqli_fetch_all(mysqli_query($link, $query), MYSQLI_ASSOC);
    // get classe
   

 
    if($row != null){
      
        mysqli_close($link);
        // create array
        $product_arr = array(
            "status" => "OK",
            "mateList" => $row
        );
    
        http_response_code(200);
        echo json_encode($product_arr);
    }else{
        http_response_code(404);
        echo json_encode(array("status" => "Invalid user.!!"));
    }
?>