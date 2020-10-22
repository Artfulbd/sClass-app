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
    if(check($id ,$cid, $conn) == "Exist"){
        echo json_encode(array("status" => "Already inserted.!!"));
    }else{
        $query = "INSERT INTO `class_list`(`sid`, `classid`) VALUES ($id, $cid)";
        $stmt = $conn->prepare( $query );
        $stmt->execute();
        if(check($id ,$cid, $conn) == "Exist")
            echo json_encode(array("status"=> "Successfully inserted."));
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