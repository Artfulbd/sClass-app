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
    
    
    // set ID property of record to read
    $data = json_decode(file_get_contents("php://input"));
    $fname = $data->fname;
    $lname = $data->lname;
    $pass = $data->pass;
    $speed = $data->speed;
    $qstn1 = $data->qstn1;
    $qstn2 = $data->qstn2;
    $id = $data->id;

  
    $num = givefname($id,$conn);
    if($num != null){
        http_response_code(200);
        echo json_encode(array("status" => "ID already exist"));
    }else{
            $query2 = "INSERT INTO `user`(`id`, `fname`, `lname`, `pass`, `speed`, `qstn1`, `qstn2`) 
            VALUES ($id, '$fname', '$lname', '$pass', $speed,'$qstn1','$qstn2')";
            $stmt = $conn->prepare($query2);
            $stmt->execute();
            $num = givefname($id, $conn);
            if($num == $fname){
                http_response_code(200);
                echo json_encode(array("status" => "ID Successfully created","fname" => "$num"));
            }
            else{
                http_response_code(200);
                echo json_encode(array("status" => "Problem on creating user"));
            }
    }

    function givefname($id, $conn)
    {
        $query = "SELECT fname  FROM user WHERE id = $id";
        $stmt = $conn->prepare( $query );
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
        return $row['fname'];
    }
?>