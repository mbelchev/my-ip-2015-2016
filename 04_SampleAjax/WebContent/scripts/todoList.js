$(document).ready(function() {
	"use strict";
	
	var ENDPOINT = "http://localhost:3000/tasks";
	
	function taskEndpoint(taskId) {
		return ENDPOINT + "/" + taskId;
	}

	function showPanel(panelName) {
		var ALL_PANELS = ["emptyPanel", "readPanel", "updatePanel", "createPanel"];
		_.forEach(ALL_PANELS, function(nextValue) {
			$("#"+nextValue).hide();
		});
		$("#"+panelName).show();
	}

	function listTasks() {
		return $.ajax(ENDPOINT, {
			method: "GET",
			dataType: "json"
		});
	}
	function readTask(taskId) {
		return $.ajax(taskEndpoint(taskId), {
			method: "GET",
			dataType: "json"
		});
	}
	function showTaskView(task) {
		$("#readPanel .task-title").text(task.title);
		$("#readPanel .task-description").text(task.description);
		showPanel("readPanel");
	}
	function reloadTasks() {
		listTasks().then(function(response) {
			function addTaskToList(task) {
				var newItem = $("<li />");
				newItem.text(task.title);
				newItem.addClass("list-group-item");
				newItem.attr("data-task-id", task.id);
				$("#tasksList").append(newItem);
			}
			$("#tasksList").html("");
			_.forEach(response, addTaskToList);
		});
	}
	
	function addTask() {		
		 $.ajax(ENDPOINT, {
			method: "POST",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				title: $("#createPanel [name='title']").val(),
				description: $("#createPanel [name='description']").val()
			}),
			dataType: "json",
			success: location.reload()
		});
	}
	
	function deleteTask(id) {
		$.ajax(taskEndpoint(id), {
			method: "DELETE",
			error: location.reload()
		});
	}
	
	function updateTask(id) {
		$.ajax(taskEndpoint(id), {
			method: "PUT",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				title: $("#updatePanel [name='title']").val(),
				description: $("#updatePanel [name='description']").val()
			}),
			dataType: "json",
			success: location.reload()
		})		
	}
	
	function attachHandlers() {
		var taskId;
		
		$(document).on("click", "#tasksList [data-task-id]", function() {
			taskId = $(this).attr("data-task-id");
			readTask(taskId).then(showTaskView);
		});
	
		$(".task-action-cancel").click(function() {
			showPanel("emptyPanel");
		});
	
		$(document).on("click", "#addTaskButton", function() {
			showPanel("createPanel");
		});
		
		$(document).on("click", "#createPanel .task-action-ok", function() {
			addTask();
		});
		
		$(document).on("click", "#readPanel .task-action-remove", function() {
			deleteTask(taskId);
		});
		
		$(document).on("click", "#readPanel .task-action-ok", function() {
			showPanel("updatePanel");
		});
		
		$(document).on("click", "#updatePanel .task-action-ok", function() {
			updateTask(taskId);
		});

	}
	
	attachHandlers();
	reloadTasks();
	
});
