<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Boutique | Ecommerce bootstrap template</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- gLightbox gallery-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>


    <link rel="stylesheet" href="../../../boutique/vendor/glightbox/css/glightbox.min.css">
    <!-- Range slider-->
    <link rel="stylesheet" href="../../../boutique/vendor/nouislider/nouislider.min.css">
    <!-- Choices CSS-->
    <link rel="stylesheet" href="../../../boutique/vendor/choices.js/public/assets/styles/choices.min.css">
    <!-- Swiper slider-->
    <link rel="stylesheet" href="../../../boutique/vendor/swiper/swiper-bundle.min.css">
    <!-- Google fonts-->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Libre+Franklin:wght@300;400;700&amp;display=swap">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Martel+Sans:wght@300;400;800&amp;display=swap">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="../../../boutique/css/style.default.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="../../../boutique/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="../../../boutique/img/favicon.png">
</head>
<body>
<div class="page-holder">
    <!-- navbar-->
    <header class="header bg-white">
        <div class="container px-lg-3">
            <nav class="navbar navbar-expand-lg navbar-light py-3 px-lg-0"><a class="navbar-brand" href="index.html"><span class="fw-bold text-uppercase text-dark">Boutique</span></a>
                <button class="navbar-toggler navbar-toggler-end" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto">
                        <li class="nav-item">
                            <!-- Link--><a class="nav-link" href="index.html">Home</a>
                        </li>
                        <li class="nav-item">
                            <!-- Link--><a class="nav-link" href="../shop.html">Shop</a>
                        </li>
                        <li class="nav-item">
                            <!-- Link--><a class="nav-link" href="detail.html">Product detail</a>
                        </li>
                        <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Pages</a>
                            <div class="dropdown-menu mt-3 shadow-sm" aria-labelledby="pagesDropdown"><a class="dropdown-item border-0 transition-link" href="index.html">Homepage</a><a class="dropdown-item border-0 transition-link" href="../shop.html">Category</a><a class="dropdown-item border-0 transition-link" href="detail.html">Product detail</a><a class="dropdown-item border-0 transition-link" href="cart.html">Shopping cart</a><a class="dropdown-item border-0 transition-link" href="checkout.jsp">Checkout</a></div>
                        </li>
                    </ul>
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item"><a class="nav-link" href="cart.html"> <i class="fas fa-dolly-flatbed me-1 text-gray"></i>Cart<small class="text-gray fw-normal">(2)</small></a></li>
                        <li class="nav-item"><a class="nav-link" href="#!"> <i class="far fa-heart me-1"></i><small class="text-gray fw-normal"> (0)</small></a></li>
                        <li class="nav-item"><a class="nav-link" href="#!"> <i class="fas fa-user me-1 text-gray fw-normal"></i>Login</a></li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <!--  Modal -->
    <div class="modal fade" id="productView" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content overflow-hidden border-0">
                <button class="btn-close p-4 position-absolute top-0 end-0 z-index-20 shadow-0" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                <div class="modal-body p-0">
                    <div class="row align-items-stretch">
                        <div class="col-lg-6">
                            <div class="p-4 my-md-4">
                                <ul class="list-inline mb-2">
                                    <li class="list-inline-item m-0"><i class="fas fa-star small text-warning"></i></li>
                                    <li class="list-inline-item m-0 1"><i class="fas fa-star small text-warning"></i></li>
                                    <li class="list-inline-item m-0 2"><i class="fas fa-star small text-warning"></i></li>
                                    <li class="list-inline-item m-0 3"><i class="fas fa-star small text-warning"></i></li>
                                    <li class="list-inline-item m-0 4"><i class="fas fa-star small text-warning"></i></li>
                                </ul>
                                <h2 class="h4">Red digital smartwatch</h2>
                                <p class="text-muted">$250</p>
                                <p class="text-sm mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ut ullamcorper leo, eget euismod orci. Cum sociis natoque penatibus et magnis dis parturient montes nascetur ridiculus mus. Vestibulum ultricies aliquam convallis.</p>
                                <div class="row align-items-stretch mb-4 gx-0">
                                    <div class="col-sm-7">
                                        <div class="border d-flex align-items-center justify-content-between py-1 px-3"><span class="small text-uppercase text-gray mr-4 no-select">Quantity</span>
                                            <div class="quantity">
                                                <button class="dec-btn p-0"><i class="fas fa-caret-left"></i></button>
                                                <input class="form-control border-0 shadow-0 p-0" type="text" value="1">
                                                <button class="inc-btn p-0"><i class="fas fa-caret-right"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-5"><a class="btn btn-dark btn-sm w-100 h-100 d-flex align-items-center justify-content-center px-0" href="cart.html">Add to cart</a></div>
                                </div><a class="btn btn-link text-dark text-decoration-none p-0" href="#!"><i class="far fa-heart me-2"></i>Add to wish list</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <!-- HERO SECTION-->
        <section class="py-5 bg-light">
            <div class="container">
                <div class="row px-4 px-lg-5 py-lg-4 align-items-center">
                    <div class="col-lg-6">
                        <h1 class="h2 text-uppercase mb-0">Checkout</h1>
                    </div>
                    <div class="col-lg-6 text-lg-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-lg-end mb-0 px-0 bg-light">
                                <li class="breadcrumb-item"><a class="text-dark" href="index.html">Home</a></li>
                                <li class="breadcrumb-item"><a class="text-dark" href="cart.html">Cart</a></li>
                                <li class="breadcrumb-item active" aria-current="page">Checkout</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </section>
        <section class="py-5">
            <!-- BILLING ADDRESS-->
            <h2 class="h5 text-uppercase mb-4">Billing details</h2>

            <!-- Form để chọn ID hóa đơn -->
            <form action="/chi-tiet-hoa-don/${userId}" method="get">
                <select name="hoaDonId" class="form-control">
                    <c:forEach items="${hoaDon}" var="sp">
                        <option value="${sp.id}">IDHoaDon ${sp.id} | Tài Khoản : ${sp.userId.userName} | Địa Chỉ :${sp.diaChi} | Số điện thoại : ${sp.sdt}</option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-success">Search</button>
            </form>



            <table class="table table-sm table-hover">
                <thead>
                <tr>
                    <th scope="col">ID Chitiet</th>
                    <th scope="col">ID HoaDon</th>
                    <th scope="col">Số Lượng</th>
                    <th scope="col">Tên Sản phẩm</th>
                    <th scope="col">Giá sản phẩm</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Ngày Nhận Hàng</th>
                    <th scope="col">Trả hàng</th>
                    <th scope="col">Đã trả hàng</th>
                    <th scope="col">Tổng tiền</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${chiTietHoaDon}" var="hd">
                    <tr>
                        <td>${hd.id}</td>
                        <td>${hd.idHoaDon.id}</td>
                        <td>${hd.soLuong}</td>
                        <td>${hd.idChiTietSanPham.ten}</td>
                        <td>${hd.donGia}</td>
                        <td>${hd.donGia * hd.soLuong}</td>
                        <td>${hd.idHoaDon.ngayKhachNhan}</td>

                        <td>
                            <button class="return-button" data-received-date="${hd.idHoaDon.ngayKhachNhan}">
                                <a href="/chi-tiet-hoa-don/tra-hang/${hd.id}">Trả hàng</a>
                            </button>
                        </td>


                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="8"></td> <!-- Để cân bằng số cột với hàng trên -->
                    <td>
                        <c:set var="total" value="0" />
                        <c:forEach items="${chiTietHoaDon}" var="hd">
                            <c:set var="total" value="${total + (hd.donGia * hd.soLuong)}" />
                        </c:forEach>
                        ${total}
                    </td>
                </tr>
                </tbody>
            </table>
                </tbody>
            </table>
        </section>
    </div>


<%--    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">--%>
<%--        <div class="modal-dialog">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>

<%--&lt;%&ndash;                    <form:form method="POST" action="/dang-ki/add" modelAttribute="traHang">&ndash;%&gt;--%>


<%--&lt;%&ndash;                        <div class="form-sub-w3ls">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <form:input path="id" class="form-control" />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="icon-agile">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <i class="fa fa-user" aria-hidden="true"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <div class="form-sub-w3ls">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <form:input path="idChiTietSanPham.ten" class="form-control"  />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="icon-agile">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <i class="fa fa-envelope-o" aria-hidden="true"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </div>&ndash;%&gt;--%>

<%--&lt;%&ndash;                        <div class="form-sub-w3ls">&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <form:input path="soLuong" class="form-control"  />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="icon-agile">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <i class="fa fa-envelope-o" aria-hidden="true"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>

<%--&lt;%&ndash;                            <form:input path="donGia" class="form-control"  />&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div class="icon-agile">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <i class="fa fa-envelope-o" aria-hidden="true"></i>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </div>&ndash;%&gt;--%>

<%--&lt;%&ndash;                    </form:form>&ndash;%&gt;--%>




<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>--%>
<%--                    <button type="button" class="btn btn-primary">Save changes</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>




    <footer class="bg-dark text-white">
        <div class="container py-4">
            <div class="row py-5">
                <div class="col-md-4 mb-3 mb-md-0">
                    <h6 class="text-uppercase mb-3">Customer services</h6>
                    <ul class="list-unstyled mb-0">
                        <li><a class="footer-link" href="#!">Help &amp; Contact Us</a></li>
                        <li><a class="footer-link" href="#!">Returns &amp; Refunds</a></li>
                        <li><a class="footer-link" href="#!">Online Stores</a></li>
                        <li><a class="footer-link" href="#!">Terms &amp; Conditions</a></li>
                    </ul>
                </div>
                <div class="col-md-4 mb-3 mb-md-0">
                    <h6 class="text-uppercase mb-3">Company</h6>
                    <ul class="list-unstyled mb-0">
                        <li><a class="footer-link" href="#!">What We Do</a></li>
                        <li><a class="footer-link" href="#!">Available Services</a></li>
                        <li><a class="footer-link" href="#!">Latest Posts</a></li>
                        <li><a class="footer-link" href="#!">FAQs</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <h6 class="text-uppercase mb-3">Social media</h6>
                    <ul class="list-unstyled mb-0">
                        <li><a class="footer-link" href="#!">Twitter</a></li>
                        <li><a class="footer-link" href="#!">Instagram</a></li>
                        <li><a class="footer-link" href="#!">Tumblr</a></li>
                        <li><a class="footer-link" href="#!">Pinterest</a></li>
                    </ul>
                </div>
            </div>
            <div class="border-top pt-4" style="border-color: #1d1d1d !important">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start">
                        <p class="small text-muted mb-0">&copy; 2021 All rights reserved.</p>
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        <p class="small text-muted mb-0">Template designed by <a class="text-white reset-anchor" href="https://bootstrapious.com/p/boutique-bootstrap-e-commerce-template">Bootstrapious</a></p>
                        <!-- If you want to remove the backlink, please purchase the Attribution-Free License. See details in readme.txt or license.txt. Thanks!-->
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- JavaScript files-->
    <script src="../../../boutique/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../../../boutique/vendor/glightbox/js/glightbox.min.js"></script>
    <script src="../../../boutique/vendor/nouislider/nouislider.min.js"></script>
    <script src="../../../boutique/vendor/swiper/swiper-bundle.min.js"></script>
    <script src="../../../boutique/vendor/choices.js/public/assets/scripts/choices.min.js"></script>
    <script src="../../../boutique/js/front.js"></script>
    <script>
        // ------------------------------------------------------- //
        //   Inject SVG Sprite -
        //   see more here
        //   https://css-tricks.com/ajaxing-svg-sprite/
        // ------------------------------------------------------ //
        function injectSvgSprite(path) {

            var ajax = new XMLHttpRequest();
            ajax.open("GET", path, true);
            ajax.send();
            ajax.onload = function(e) {
                var div = document.createElement("div");
                div.className = 'd-none';
                div.innerHTML = ajax.responseText;
                document.body.insertBefore(div, document.body.childNodes[0]);
            }
        }
        // this is set to BootstrapTemple website as you cannot
        // inject local SVG sprite (using only 'icons/orion-svg-sprite.svg' path)
        // while using file:// protocol
        // pls don't forget to change to your domain :)
        injectSvgSprite('https://bootstraptemple.com/files/icons/orion-svg-sprite.svg');

    </script>
    <!-- FontAwesome CSS - loading as last, so it doesn't block rendering-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
</div>

<script>
    // Function to calculate the difference in days between two dates
    function calculateDaysDifference(date1, date2) {
        const oneDay = 24 * 60 * 60 * 1000; // One day in milliseconds
        const diffDays = Math.round(Math.abs((date1 - date2) / oneDay));
        return diffDays;
    }

    // Get all the "Trả Hàng" buttons
    const returnButtons = document.querySelectorAll('.return-button');

    // Current date
    const currentDate = new Date();

    // Iterate through each button and check if it should be disabled or hidden
    returnButtons.forEach(button => {
        const receivedDate = new Date(button.getAttribute('data-received-date'));
        const daysDifference = calculateDaysDifference(currentDate, receivedDate);

        // Hide the button if it's been more than 7 days
        if (daysDifference > 7) {
            button.style.display = 'none'; // Hide the button
        } else {
            button.style.display = 'block'; // Show the button
        }
    });


    var myModal = document.getElementById('myModal')
    var myInput = document.getElementById('myInput')

    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus()
    })

</script>


</body>
</html>