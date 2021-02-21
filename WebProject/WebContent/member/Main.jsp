<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	 <%String myctx=request.getContextPath();%>
<c:import url="/top.jsp"/>
 <div class="row">
          <div class="col-md-6">
          
          </div>
        </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12"></div>
          <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
              <li data-target="#myCarousel" data-slide-to="1"></li>
              <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
              <div class="item active">
                <img src="https://seoul-p-studio.bunjang.net/product/81257310_1_1541657289_w640.jpg"
                alt="shoes">
              </div>
              <div class="item">
                <img src="http://yachuk.com/wp/wp-content/uploads/bfi_thumb/ney-1-37091uvezog5qq7137eqdc-373yoge0bkg6qr361xpfk0.jpg"
                alt="hood">
              </div>
              <div class="item">
                <img src="https://seoul-p-studio.bunjang.net/product/53688299_1_1457316221_w640.jpg"
                alt="uniform">
              </div>
            </div>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
          </div>
        </div>
       
      </div>
    </div>
    <c:import url="/foot.jsp"/>
  </body>

</html>