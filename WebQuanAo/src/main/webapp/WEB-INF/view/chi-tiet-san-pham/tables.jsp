<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Tables - SB Admin</title>
    <%--        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />--%>
    <%--        <link href="../bootrap/css/styles.css" rel="stylesheet" />--%>
    <%--        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>--%>

    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../../../bootrap/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>


</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="index.html">Start Bootstrap</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..."
                   aria-describedby="btnNavbarSearch"/>
            <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
        </div>
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
               aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#!">Settings</a></li>
                <li><a class="dropdown-item" href="#!">Activity Log</a></li>
                <li>
                    <hr class="dropdown-divider"/>
                </li>
                <li><a class="dropdown-item" href="#!">Logout</a></li>
            </ul>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">Core</div>
                    <a class="nav-link" href="/thong-ke/doanh-thu">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Dashboard
                    </a>
                    <div class="sb-sidenav-menu-heading">Interface</div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts"
                       aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        Thuộc tính
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne"
                         data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/mau-sac">Màu Sắc</a>
                            <a class="nav-link" href="/loai-san-pham">Loại Sản Phẩm</a>
                            <a class="nav-link" href="/hang-san-xuat">Nhà Sản Xuất</a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages"
                       aria-expanded="false" aria-controls="collapsePages">
                        <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                        Pages
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="collapsePages" aria-labelledby="headingTwo"
                         data-bs-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                               data-bs-target="#pagesCollapseAuth" aria-expanded="false"
                               aria-controls="pagesCollapseAuth">
                                Hóa đơn
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne"
                                 data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/hoa-don">Duyệt Hóa Đơn</a>
                                    <a class="nav-link" href="">Trả Hàng</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="" data-bs-toggle="collapse"
                               data-bs-target="#pagesCollapseError" aria-expanded="false"
                               aria-controls="pagesCollapseError">
                                Khuyến Mãi
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne"
                                 data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/khuyen-mai">Thêm khuyến mãi</a>
                                    <a class="nav-link" href="/nhan-vien">Quản lí nhân viên</a>
<%--                                    <a class="nav-link" href="../../view1/500.html">500 Page</a>--%>
                                </nav>
                            </div>
                        </nav>
                    </div>
                    <div class="sb-sidenav-menu-heading">Addons</div>
                    <a class="nav-link" href="/thong-ke/bieu-do">
                        <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                        Biểu đồ
                    </a>
                    <a class="nav-link" href="/chi-tiet-san-pham">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        Chi tiết sản phẩm
                    </a>
                    <a class="nav-link" href="/thong-ke/theo-ngay">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        Thống kê
                    </a>
                </div>
            </div>
            <div class="sb-sidenav-footer">
                <div class="small">Logged in as:</div>
                Start Bootstrap
            </div>
        </nav>
    </div>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Chi tiết sản phẩm</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="index.html">Hiển thị</a></li>
                    <li class="breadcrumb-item active">Tables</li>
                </ol>

                <br>
                <%--                        tim kiem theo ten san pham và gia--%>
                <form>
                    <div>
                        <label class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" placeholder="Tìm kiếm theo tên" name="tenSanPham"
                               value="${param.tenSanPham}">
                    </div>
                    <div class="row">
                        <lable>Tìm kiếm theo giá bán</lable>
                        <div class="col-md-6">
                            <input type="number" class="form-control" placeholder="Từ" name="min" value="${param.min}">
                        </div>
                        <div class="col-md-6">
                            <input type="number" class="form-control" placeholder="Đến" name="max" value="${param.max}">
                        </div>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-success">Search</button>
                </form>

                <br>
                <%--loc loai san pham--%>
                <form>
                    <select name="idLoai" class="form-control">
                        <c:forEach items="${listLoai}" var="sp">
                            <option value="${sp.id}">${sp.ten}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-success">Search</button>

                </form>

                <br>
                <br>
                <%--                        <form method="POST" action="/chi-tiet-san-pham/upload" enctype="multipart/form-data">--%>
                <%--                            <input type="file" name="file" accept=".xlsx">--%>
                <%--                            <input type="submit" value="Upload">--%>
                <%--                        </form>--%>
                <form method="POST" action="/chi-tiet-san-pham/upload" enctype="multipart/form-data" class="my-4"
                      onsubmit="return confirmAdd()">
                    <div class="input-group">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="file" name="file" accept=".xlsx">
                            <label class="custom-file-label" for="file"></label>
                        </div>
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">Import Excel</button>
                        </div>
                    </div>
                </form>
                <%----%>
                <a class="btn btn-success" href="/chi-tiet-san-pham/formAdd">Add Product</a>
                <br>
                <br>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">
                            ${successMessage}
                    </div>
                </c:if>

                <table class="table  table-sm table-hover ">
                    <thead>
                    <tr>
                        <th scope="col">ẢNH</th>
                        <th scope="col">TÊN SẢN PHẨM</th>
                        <th scope="col">MÀU SẮC</th>
                        <th scope="col">HÃNG</th>
                        <th scope="col">KÍCH CỠ</th>
                        <th scope="col">CHẤT LIỆU</th>
                        <th scope="col">LOẠI</th>
                        <th scope="col">SỐ LƯỢNG</th>
                        <th scope="col">GIÁ</th>
                        <th scope="col">NGÀY NHẬP</th>
                        <th scope="col">NGÀY CHỈNH SỬA</th>
                        <th scope="col">TRẠNG THÁI</th>
                        <th scope="col">CHỨC NĂNG</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listChiTietSanPham.getContent()}" var="ctsp">
                        <tr>
                            <td>
                                <img src="getimage/${ctsp.image}" width="100" height="90">
                            </td>
                            <td>${ctsp.ten}</td>
                            <td>${ctsp.idMauSac.ten}</td>
                            <td>${ctsp.idHang.ten}</td>
                            <td>${ctsp.idKichCo.ten}</td>
                            <td>${ctsp.idChatLieu.ten}</td>
                            <td>${ ctsp.idLoai.ten}</td>
                            <td>${ ctsp.soLuongTon }</td>
                            <td>${ ctsp.giaBan }</td>
                            <td>${ ctsp.ngayNhap }</td>
                            <td>${ ctsp.ngayChinhSua }</td>
                            <td>${ ctsp.idTrangThai.id == 1 ? "Hoạt động" : "Ngừng hoạt động" }</td>
                            <td>
                                    <%--                                        <a href="/chi-tiet-san-pham/detail/${ctsp.id}" class="btn btn-sm btn-primary">Detail</a>--%>
                                <a href="/chi-tiet-san-pham/view-update/${ctsp.id}"
                                   class="btn btn-sm btn-warning">Edit</a>
                                <a href="/chi-tiet-san-pham/remove/${ctsp.id}" onclick="return confirmRemove()"
                                   class="btn btn-sm btn-danger">Tắt</a>
                                <a href="/chi-tiet-san-pham/hoatdong/${ctsp.id}" onclick="return confirmRemove1()"
                                   class="btn btn-sm btn-danger">Bật</a>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>


                <center>
                    <div>
                        <c:if test="${listChiTietSanPham.getNumber() gt 0}">
                            <a href="?tenSanPham=${param.tenSanPham}&page=${listChiTietSanPham.getNumber()}">
                                Previous
                            </a>
                        </c:if>

                        <span>${listChiTietSanPham.getNumber() + 1} / ${listChiTietSanPham.getTotalPages()}</span>

                        <c:if test="${listChiTietSanPham.getNumber() lt listChiTietSanPham.getTotalPages() - 1}">
                            <a href="?tenSanPham=${param.tenSanPham}&page=${listChiTietSanPham.getNumber() + 2}">
                                Next
                            </a>
                        </c:if>
                    </div>
                </center>


            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Your Website 2023</div>
                    <div>
                        <a href="#">Privacy Policy</a>
                        &middot;
                        <a href="#">Terms &amp; Conditions</a>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>
<script>
    function confirmRemove() {
        return confirm("Bạn có chắc chắn muốn tắt trạng thái ");
    }

    function confirmRemove1() {
        return confirm("Bạn có chắc chắn muốn bật trạng thái ");
    }

    function confirmAdd() {
        return confirm("Bạn có chắc chắn muốn thêm excel.");
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="../../../bootrap/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../../../bootrap/js/datatables-simple-demo.js"></script>
<script src="../../../bootrap/js/datatables-simple-demo.js"></script>


</body>
</html>
