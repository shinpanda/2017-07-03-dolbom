<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="result" class="result-container">
	<h3 class=hidden>검색 결과</h3>
	<div id="resultsetting" class="result-setting-container">

		<div id="resultinfo">
			<p id="resultaddress" class="resultaddress">검색지역 : 전국</p>
			<p id="resultcount" class="resultcount">20명의 베푸미가 검색되었습니다.</p>
		</div>
		<div id="resultsort" class="resultsort">
			<input id="time" type="radio" name="sort" checked="checked"
				value="time"> <label for="time"><span></span>시간순</label> <input
				id="grade" type="radio" name="sort" value="grade"> <label
				for="grade"><span></span>등급순</label>


		</div>




		<div class="bepumi-list-container">
			<div class="wrap">

					<c:forEach var = "i" begin = "0" end = "5"  >			
							<div class="floor">
			
								<c:forEach var = "i" begin = "0" end = "3"  >			
										<div class="column">
										    <!-- Post-->
										    <div class="post-module">
										      <!-- Thumbnail-->
										      <div class="thumbnail">
										        <div class="date">
										          <div class="day">27</div>
										          <div class="month">Mar</div>
										        </div><a href=""><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/photo-1429043794791-eb8f26f44081.jpeg"/></a>
										      </div>
										      <!-- Post Content-->
										      <div class="post-content">
													<c:set var="grade" value="회원" />
														<c:if test="${profile.grade == 1}">
															<c:set var="grade" value="베푸미" />
														</c:if>
														<c:if test="${profile.grade == 2}">
															<c:set var="grade" value="슈퍼베푸미" />
														</c:if>
										        <div class="category">${grade}</div>
										        <h1 class="title">"도움이 필요하시면 정성을 다해 돌보겠습니다."</h1>
										        
										 
																																		      
										        <h2 class="sub_title">${profile.name}   ${profile.address}</h2>
										        <p class="description">New York, the largest city in the U.S., is an architectural marvel with plenty of historic monuments, magnificent buildings and countless dazzling skyscrapers.</p>
										        <div class="post-meta"><span class="timestamp"><i class="fa fa-clock-">o</i> 6 mins ago</span><span class="comments"><i class="fa fa-comments"></i><a href="#"> 후기 39개</a></span></div>
										      </div>
										    </div>
										  </div>
								</c:forEach>		
															  
							</div>
					</c:forEach>			
			
				
			</div>

		</div>
	</div>
</div>



 <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/searching.js"></script>
