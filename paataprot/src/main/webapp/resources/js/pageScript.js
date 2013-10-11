$(document).ready(function($) {
				$('#srollPannel').perfectScrollbar({
					wheelSpeed : 40,
					wheelPropagation : false
				});
				var ids = document.getElementById('notifyForm:notiScroll');
				$(ids).perfectScrollbar({
					wheelSpeed : 20,
					wheelPropagation : false
				});
			});

			function addElement() {
				document.getElementById('clickedText').style.display = 'inline';
				/* 				document.getElementById('tabView:postForm:hide1').style.display = 'inline';
				 document.getElementById('clickedText').innerHTML = '<input type="text" placeholder="send to..." id="sendToText" name="sendToText" style="width:684px"></input><br></br><input type="submit" value="send" class="btn btn-large btn-primary" style="width:60px;height:35px;padding:1px 1px;margin-left:640px"/>'; */
			}
			function hidePostText() {
				document.getElementById('clickedText').style.display = 'none';
				document.getElementById('clickedAlertText').style.display = 'none';
			}
			function addAlertText() {
				document.getElementById('clickedAlertText').style.display = 'inline';
				/* 	document.getElementById('clickedAlertText').innerHTML = '<input type="text" placeholder="send to..." id="alertToText" name="alertToText" style="width:682px"></input><br></br><input type="submit" value="send" class="btn btn-large btn-primary" style="width:70px;height:35px;padding:1px 1px;margin-left:626px;border-radius: 1pc"/>'; */
			}
			function clearText() {
				document.getElementById('tabView:postForm:postText').value = '';
				document.getElementById('tabView:postForm:autoComp_input').value = '';
			}
			function clearTextAlert() {
				document.getElementById('tabView:alertForm:alertText').value = '';
				document
						.getElementById('tabView:alertForm:autoCompAlert_input').value = '';
			}
			
			function reloadPage()
			  {
			  location.reload();
			  }
			function showReplyText(index){
				
				document.getElementById('tabViewNewsFeed:postfor:'+index+':replyPanelHide').style.display = 'inline';
			}
			function hideReplyText(index){
				document.getElementById('tabViewNewsFeed:postfor:'+index+':replyPanelHide').style.display = 'none';
			}
           function showGroupReplyText(index){
				
				document.getElementById('tabViewGroupNewsFeed:postForGroup:'+index+':replyGroupPanelHide').style.display = 'inline';
			}
			function hideGroupReplyText(index){
				document.getElementById('tabViewGroupNewsFeed:postForGroup:'+index+':replyGroupPanelHide').style.display = 'none';
			}