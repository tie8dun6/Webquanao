<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đọc mã QR từ webcam</title>
</head>
<body>
<h1>Đọc mã QR từ webcam</h1>
<video id="video" width="320" height="240" autoplay></video>
<div>
    <button onclick="capture()">Chụp ảnh</button>
</div>
<div id="output"></div>

<script>
    const video = document.getElementById('video');
    const output = document.getElementById('output');

    // Khởi tạo navigator.getUserMedia() để truy cập webcam
    navigator.mediaDevices.getUserMedia({ video: true })
        .then(function (stream) {
            video.srcObject = stream;
            video.play();
        })
        .catch(function (error) {
            console.error('Lỗi truy cập camera:', error);
        });

    function capture() {
        // Tạo canvas để lưu trữ ảnh từ video
        const canvas = document.createElement('canvas');
        canvas.width = video.videoWidth;
        canvas.height = video.videoHeight;
        const ctx = canvas.getContext('2d');

        // Vẽ frame hiện tại từ video lên canvas
        ctx.drawImage(video, 0, 0, canvas.width, canvas.height);

        // Chuyển canvas thành file image và gửi lên server
        canvas.toBlob((blob) => {
            const formData = new FormData();
            formData.append('file', blob, 'qrcode.png');

            fetch('/decode', {
                method: 'POST',
                body: formData,
            })
                .then(response => response.json())
                .then(data => {
                    if (data.text) {
                        output.textContent = 'Kết quả đọc mã QR: ' + data.text;
                    } else {
                        output.textContent = 'Không tìm thấy mã QR hoặc lỗi đọc mã.';
                    }
                })
                .catch(error => {
                    output.textContent = 'Đã có lỗi xảy ra khi gửi yêu cầu lên server.';
                });
        }, 'image/png');
    }

</script>

</body>
</html>
