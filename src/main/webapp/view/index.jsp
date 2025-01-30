<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vignatrics World</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            height: 100vh;
            background-color: #f0f8ff;
            font-family: Arial, sans-serif;
        }
        #clock {
            font-size: 2em;
            margin-bottom: 10px;
            color: #4a90e2;
        }
        h1 {
            color: #4a90e2;
        }
    </style>
    <script>
        function updateTime() {
            const now = new Date();
            let hours = now.getHours();
            const minutes = now.getMinutes();
            const seconds = now.getSeconds();
            const ampm = hours >= 12 ? 'PM' : 'AM';

            hours = hours % 12;
            hours = hours ? hours : 12; // the hour '0' should be '12'

            const strTime = hours+':'+minutes+':'+seconds+' '+ampm;
            document.getElementById('clock').innerText = strTime;
        }
        setInterval(updateTime, 1000);
    </script>
</head>
<body onload="updateTime()">
    <div id="clock"></div>
    <h1>Welcome to Vignatrics World</h1>
</body>
</html>
