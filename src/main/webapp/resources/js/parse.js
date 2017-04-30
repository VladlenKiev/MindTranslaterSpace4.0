$().ready(function() {

	$('#parse-button').on("click", function(e) {
		myDropzone.processQueue();
	});

	$("#myLink").click(function(e){

		e.preventDefault();
		$.ajax({

			url:$(this).attr("href"),
		success: function(){
			alert("Value Added");  // or any other indication if you want to show
		}

		});

    });

	// "dropzoneForm" is the camel-case version of the form id "dropzone-form"
	Dropzone.options.dropzoneForm = {

		url : "upload",
		autoProcessQueue : false,
		uploadMultiple : true,
		maxFilesize : 256, // MB
		parallelUploads : 100,
		maxFiles : 100,
		addRemoveLinks : true,
		previewsContainer : ".dropzone-previews",

		// The setting up of the dropzone
		init : function() {

			var myDropzone = this;

			// first set autoProcessQueue = false
			$('#upload-button123').on("click", function(e) {

				myDropzone.processQueue();
			});

			// customizing the default progress bar
			this.on("uploadprogress", function(file, progress) {

				progress = parseFloat(progress).toFixed(0);

				var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
				progressBar.innerHTML = progress + "%";
			});

			// displaying the uploaded files information in a Bootstrap dialog
			this.on("successmultiple", function(files, serverResponse) {
				showInformationDialog(files, serverResponse);
			});
		}
	}

	function showInformationDialog(files, objectArray) {

		var responseContent = "";

		for (var i = 0; i < objectArray.length; i++) {

			var infoObject = objectArray[i];

			for ( var infoKey in infoObject) {
				if (infoObject.hasOwnProperty(infoKey)) {
					responseContent = responseContent + " " + infoKey + " -> " + infoObject[infoKey] + "<br>";
				}
			}
			responseContent = responseContent + "<hr>";
		}

		// from the library bootstrap-dialog.min.js
		BootstrapDialog.show({
			title : '<b>Server Response</b>',
			message : responseContent
		});
	}

});