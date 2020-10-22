<?php
    // required headers
    header("Access-Control-Allow-Origin: *");
    header("Access-Control-Allow-Headers: access");
    header("Access-Control-Allow-Methods: GET");
    header("Access-Control-Allow-Credentials: true");
    header('Content-Type: application/json');

    // include database and object files
    include_once '../config/userdb.php';
    
    // get database connection
    $database = new userdb();
    $conn = $database->getConnection();
    
  
    
    // get database connection
    $link = mysqli_connect('localhost' , 'root' , '', 'sclass') or die("cannot connect");   

    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $cid = $data->classid;
    $flag = $data->isTeacher;
    
    if($flag == "true")
        $query = "SELECT classid FROM `classes` WHERE tid = $id and classid = $cid";
    else
        $query = "SELECT classid FROM `class_list` WHERE sid = $id and classid = $cid";
    $stmt = $conn->prepare( $query );
    $stmt->execute();
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    if($row['classid'] == $cid){
        $query = "SELECT filename, fileId FROM `class_files` WHERE classId = $cid";
        $row = mysqli_fetch_all(mysqli_query($link, $query), MYSQLI_ASSOC);
        mysqli_close($link);
    }
    http_response_code(200);
    if($row != null){
        $product_arr = array(
            "status" => "OK",
            "bookList" => $row
        );       
        echo json_encode($product_arr);
    }else{
        echo json_encode(array("status" => "Wrong information. Access not granted.!!"));
    }
?>