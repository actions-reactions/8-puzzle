<?php

function generateRandomString($length = 9) {
    $characters = '012345678';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randI = rand(0, $charactersLength - 1);
        if(strpos($randomString, $characters[$randI]) === false){
            $randomString .= $characters[$randI];
        }
        else{
            $i--;
        }
    }
    return $randomString;
}
$random_numbers = array();

for($y=0; $y < 100;$y++){
    $tmp = generateRandomString();
    if(!in_array($tmp, $random_numbers)){
        $output = "";
        $x = 0;
        for($i = 0; $i < 3; $i++){
            for($j = 0; $j < 3; $j++){
                $output .= " " . $tmp[$x++] . " ";
            }
            $output .= "\n";
        }
        $random_numbers[] = $output;
        file_put_contents("puzzle3x3-$y.txt", $output);
    }
}








