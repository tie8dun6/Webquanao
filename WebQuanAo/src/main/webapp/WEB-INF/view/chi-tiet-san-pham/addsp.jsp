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
    <link href="../../../bootrap/css/styles.css" rel="stylesheet"/>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<style>
    .error-message {
        color: red;
        font-weight: bold;
    }
</style>
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
                    <a class="nav-link" href="index.html">
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
                            <a class="nav-link" href="../view1/layout-static.html">Static Navigation</a>
                            <a class="nav-link" href="../../view1/layout-sidenav-light.html">Light Sidenav</a>
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
                                Authentication
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne"
                                 data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="../../view1/login.html">Login</a>
                                    <a class="nav-link" href="../../view1/register.html">Register</a>
                                    <a class="nav-link" href="../../view1/password.html">Forgot Password</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse"
                               data-bs-target="#pagesCollapseError" aria-expanded="false"
                               aria-controls="pagesCollapseError">
                                Error
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne"
                                 data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="../../view1/401.html">401 Page</a>
                                    <a class="nav-link" href="../../view1/404.html">404 Page</a>
                                    <a class="nav-link" href="../../view1/500.html">500 Page</a>
                                </nav>
                            </div>
                        </nav>
                    </div>
                    <div class="sb-sidenav-menu-heading">Addons</div>
                    <a class="nav-link" href="../../view1/charts.html">
                        <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                        Charts
                    </a>
                    <a class="nav-link" href="/chi-tiet-san-pham">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        Chi tiết sản phẩm
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
                    <li class="breadcrumb-item"><a href="index.html">Thêm chi tiết</a></li>
                    <li class="breadcrumb-item active">Tables</li>
                </ol>


                <div class="container">
                    <form:form class="w3-container" action="/chi-tiet-san-pham/add" method="post"
                               enctype="multipart/form-data" modelAttribute="chiTietSPDTO"
                               onsubmit="return confirmAdd()">
                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Tên sản phẩm</label>
                                <form:input class="form-control" path="ten"></form:input>
                                <form:errors path="ten" cssClass="error-message"/>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Màu sắc</label>
                                <form:select path="idMauSac.id" class="form-control">
                                    <c:forEach items="${listMS}" var="sp">
                                        <form:option value="${sp.id}">${sp.ten}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Hãng sản phẩm</label>
                                <form:select path="idHang.id" class="form-control">
                                    <c:forEach items="${listHangSanXuat}" var="sp">
                                        <form:option value="${sp.id}">${sp.ten}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Kích cỡ</label>
                                <form:select path="idKichCo.id" class="form-control">
                                    <c:forEach items="${listKichCo}" var="sp">
                                        <form:option value="${sp.id}">${sp.ten}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Chất liệu</label>
                                <form:select path="idChatLieu.id" class="form-control">
                                    <c:forEach items="${listChatLieu}" var="sp">
                                        <form:option value="${sp.id}">${sp.ten}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Loại sản phẩm</label>
                                <form:select path="idLoai.id" class="form-control">
                                    <c:forEach items="${listLoai}" var="sp">
                                        <form:option value="${sp.id}">${sp.ten}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Số lượng tồn</label>
                                <form:input class="form-control" path="soLuongTon" min="1" type="number" ></form:input>
                                <form:errors path="soLuongTon" cssClass="error-message"/>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Giá</label>
                                <form:input class="form-control" path="giaBan"></form:input>
                                <form:errors path="giaBan" cssClass="error-message"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-6">
                                <label class="form-label">Ngày nhập</label>
                                <form:input path="ngayNhap" type="date" cssClass="form-control"/>
                                <form:errors path="ngayNhap" cssClass="error-message"/>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Ngày chỉnh sửa</label>
                                <form:input path="ngayChinhSua" type="date" cssClass="form-control"/>
                                <form:errors path="ngayChinhSua" cssClass="error-message"/>
                            </div>
                        </div>
                        <div class="row">
                            <label class="form-label">Chọn ảnh</label>
                            <form:input class="form-control" path="image" type="file"></form:input>
                            <form:errors path="image" cssClass="error-message"/>
                        </div>

                        <br>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form:form>
                </div>


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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="../../../bootrap/js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
        crossorigin="anonymous"></script>
<script src="../../../bootrap/js/datatables-simple-demo.js"></script>
</body>
</html>
