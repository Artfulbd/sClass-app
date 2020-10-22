
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
    $classname = $data->id;
    $teacherid = $data->teacherid;
    $num = giveId($teacherid, $classname, $conn);

    if($num != null){
        http_response_code(404);
        echo json_encode(array("status" => "'$classname' already exiest.!"));
    }else{
            $query = "INSERT INTO `classes`(`tid`, `classname`) VALUES ($teacherid,'$classname')";
            $stmt = $conn->prepare($query);
            $stmt->execute();
            $classid = giveId($teacherid, $classname, $conn);
            if($classid != null){
                $product_arr = array(
                    "status"=> "Ok",
                    "classid" => $classid
                );
                http_response_code(200);
                echo json_encode($product_arr);
            }
            else{
                http_response_code(200);
                echo json_encode(array("status" => "Problem on creating user"));
            }


          
            
    }
    function giveId($teacherid, $classname, $conn)
    {
            $query = "SELECT classid FROM `classes` WHERE tid = $teacherid and classname = '$classname'";
            $stmt = $conn->prepare( $query );
            $stmt->execute();
            $row = $stmt->fetch(PDO::FETCH_ASSOC);
            return $row['classid'];
    }
?>