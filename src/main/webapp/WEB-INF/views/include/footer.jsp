<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Footer -->
    <footer>
      <div class="container" id = "login">
        <div class="row">
          <div class="col-md-4">
            <span class="copyright">Copyright &copy; Your Website 2017</span>
          </div>
          <div class="col-md-4">
            <ul class="list-inline social-buttons">
              <li class="list-inline-item">
                <a href="#">
                  <i class="fa fa-twitter"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="${pageContext.request.contextPath }/auth/facebook">
                  <i class="fa fa-facebook"></i>
                </a>
              </li>
              <li class="list-inline-item">
                <a href="${pageContext.request.contextPath }/user/login">
                  <i class="fa fa-linkedin"></i>
                </a>
              </li>
            </ul>
          </div>
          <div class="col-md-4">
            <ul class="list-inline quicklinks">
              <li class="list-inline-item">
                <a href="#">Privacy Policy</a>
              </li>
              <li class="list-inline-item">
                <a href="#">Terms of Use</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
    <!-- Bootstrap core JavaScript -->
    <script src= "<c:url value = '/resources/vendor/jquery/jquery.min.js' />"></script>
    <script src= "<c:url value = '/resources/vendor/popper/popper.min.js' />"></script>
    <script src= "<c:url value = '/resources/vendor/bootstrap/js/bootstrap.min.js' />"></script>

    <!-- Plugin JavaScript -->
	<script src= "<c:url value = '/resources/vendor/jquery-easing/jquery.easing.min.js' />"></script>
    <!-- Contact form JavaScript -->
   	<script src= "<c:url value = '/resources/js/jqBootstrapValidation.js' />"></script>
    <script src= "<c:url value = '/resources/js/contact_me.js' />"></script>

    <!-- Custom scripts for this template -->
	<script src= "<c:url value = '/resources/js/agency.min.js' />"></script>
  </body>
</html>