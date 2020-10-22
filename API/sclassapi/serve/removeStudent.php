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
    

    $data = json_decode(file_get_contents("php://input"));
    $id = $data->id;
    $cid = $data->classid; 
    http_response_code(200);
    if(check($id ,$cid, $conn) == "Doesn't exist"){
        echo json_encode(array("status" => "Doesn't exist.!!"));
    }else{
        $query = "DELETE FROM `class_list` WHERE `class_list`.`sid` = $id AND `class_list`.`classid` = $cid";
        $stmt = $conn->prepare( $query );
        $stmt->execute();
        if(check($id ,$cid, $conn) == "Doesn't exist")
            echo json_encode(array("status"=> "Successfully deleted"));
        else 
            echo json_encode(array("status" => "Something wrong on server.!!"));

    }

    function check($id ,$cid, $conn ){
        $query = "select sid from class_list WHERE sid = $id and classid = $cid";
        $stmt = $conn->prepare( $query );
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);

        // get classe
        if($row['sid'] == $id){
            return "Exist";
        }else{
            return "Doesn't exist";
        }

    }

    
    
    
?>