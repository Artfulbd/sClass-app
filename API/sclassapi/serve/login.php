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
    $link = mysqli_connect('localhost' , 'root' , '', 'sclass') or die("cannot connect");
    $database = new userdb();
    $conn = $database->getConnection();
    

    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $pass = $data->pass; 

    // query to read single record
    $query = "SELECT fname, lname, speed, preState from user 
                WHERE id = '$id' and pass = '$pass'
             ";

    $stmt = $conn->prepare( $query );
    $stmt->execute();
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
    $fname = $row['fname'];
    $lname = $row['lname'];
    $speed = $row['speed'];
    $state = $row['preState'];

    // get classe
   

 
    if($fname!=null){
      if($state == "teacher"){
            $query = "SELECT classid, classname FROM classes WHERE tid = $id";
            $hold = mysqli_fetch_all(mysqli_query($link, $query), MYSQLI_ASSOC);
        }
        else{
            $query = "select c.classid, c.classname FROM classes c JOIN class_list l ON( c.classid = l.classid) and l.sid = $id GROUP BY c.classid ";
            $hold = mysqli_fetch_all(mysqli_query($link, $query), MYSQLI_ASSOC);
        }
        mysqli_close($link);
        // create array
        $product_arr = array(
            "status"=> "Ok",
            "fname" => $fname,
            "lname" => $lname,
            "speed" => $speed,
            "state" => $state,
            "classList" => $hold
        );
    
        http_response_code(200);
        echo json_encode($product_arr);
    }else{
        http_response_code(404);
        echo json_encode(array("status" => "Incorrect user id or password"));
    }
?>