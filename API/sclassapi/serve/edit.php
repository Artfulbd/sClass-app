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
    $givenpass = $data->currentPass;
    $newpass = $data->newPass;
    $gspd = $data->speed;
    $gq1 = $data->q1ans;
    $gq2 = $data->q2ans;
   

    http_response_code(404);
    if($id == null || $givenpass == null || $newpass == null || $gspd == null || $gq1 == null || $gq2 == nulL){
        echo json_encode(array("status" => "Something wrong on server."));
    }
    else{
        $query = "SELECT pass, qstn1, qstn2 from user where id=$id";
        $stmt = $conn->prepare($query);
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        
        if($givenpass == $row['pass']){
            if($gq1 == $row['qstn1'] && $gq1 == $row['qstn1']){
                if($gspd == null)$query = "UPDATE `user` SET `pass`= $newpass WHERE id = $id";
                else $query = "UPDATE `user` SET `pass`= $newpass, `speed`= $gspd WHERE id = $id";
                $stmt = $conn->prepare($query);
                $stmt->execute();
                
                echo json_encode(array("status" => "Id successfully updated."));
            }else{
                echo json_encode(array("status" => "Incorrect answer.!!"));
            }
        }else{
            echo json_encode(array("status" => "Wrong password.!!"));
        }
    }
?>