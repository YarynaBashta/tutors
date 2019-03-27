var mainUrl = "http://localhost:9000";


$(document).ready(function() {
    console.log("aaaa!");
    getAllWorkers();
    setModalConfiguration();
    setActionOnCreateBtn();
    setActionOnUpdateBtn();
    getAllServices();
    getAllSalons();
});

//start when load page PS reload page for triggered http request
function  getAllWorkers() {
    $.ajax({
        url: mainUrl + "/worker",
        type: "GET",
        contentType: "application/json",
        success: function (workerResponse) {

            setWorkersToTable(workerResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateButtons();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function  getAllServices() {
    $.ajax({
        url: mainUrl + "/service",
        type: "GET",
        contentType: "application/json",
        success: function (data) {

            setServicesToSelect(data);
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setServicesToSelect(services) {
    for (var i = 0; i < services.length; i++) {
        setServiceToSelect(services[i]);
    }
}

function setServiceToSelect(service) {
    var selectOfservices = $("#worker-service");
    selectOfservices.append('<option value="' + service.id + '">' +  service.name + ' </option>');
}

function  getAllSalons() {
    $.ajax({
        url: mainUrl + "/salon",
        type: "GET",
        contentType: "application/json",
        success: function (data) {
            setSalonsToRadio(data);
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setSalonsToRadio(salons) {
    for (var i = 0; i < salons.length; i++) {
        setSalonToRadio(salons[i]);
    }
}

function setSalonToRadio(salon) {
    var radioOfsalons = $("#worker-salon");
    radioOfsalons.append('<p><input name="salonId" type="radio" value="'+salon.id+'"> '+salon.name+'</p>');
}

function setWorkersToTable(workers) {
    for (var i = 0; i < workers.length; i++) {
        setWorkerToTable(workers[i]);
    }
}

function setWorkerToTable(worker) {
    var img  = '';
    if(worker.photo){
       img = '<img style="max-width:200px;" src="' +'/img/'+ worker.photo+'"> ';
    }
    var tableOfWorkers = $("#workers");
    tableOfWorkers.append('<tr>' +
        '<td>' + img +  ' </td>' +
        '<td>' + worker.name + '</td>' +
        '<td>' + worker.surname + '</td>' +
        '<td>' +worker.phone + '</td>' +
        '<td>' +worker.experience + '</td>' +
        '<td>' +worker.serviceName + '</td>' +
        '<td>' +worker.salonName + '</td>' +
        '<td><button class="delete-button" value="' + worker.id + '">Delete</button></td>' +
        '<td><button class="update-button" value="' + worker.id + '">Update</button></td>' +
        '</tr>');
}

//delete process
function setActionOnDeleteButtons() {
    $(".delete-button").each(function (index) {

        $(this).click(function () {

            if (confirm("Are you sure?")) {
                $.ajax({
                    url: mainUrl + "/worker" ,
                    type: "DELETE",
                    data: {
                        id:$(this).val()
                    },
                    success: function (data) {
                        location.reload();
                    },
                    error: function (error) {
                        alert(error.message);
                    }
                });
            }


        })
    })

}

function setActionOnCreateBtn() {
    $("#btnCreateWorker").click(function () {
        //
        var  photo = $("#photo2").val();
        var firstName = $("#fname").val();
        var lastName = $("#lname").val();
        var phone = $("#phone").val();
        var experience = $("#experience").val();
        var service = $("#worker-service").val();
        var salon = $('input[name=salonId]:checked').val();

        if (!firstName) {
            alert('First name can\'t be empty');
            return false;
        }
        if (!lastName) {
            alert('lastName  can\'t be empty');
            return false;
        }
        if (!phone) {
            alert('Phone  can\'t be empty');
            return false;
        }
        if (!experience) {
            alert('Experience  can\'t be empty');
            return false;
        }
        if (!service) {
            alert('Service  can\'t be empty');
            return false;
        }
        if (!salon) {
            alert('Salon  can\'t be empty');
            return false;
        }


        var newWorker = {
            "photo" : photo,
            "name": firstName,
            "surname": lastName,
            "phone": phone,
            "experience" : experience,
            "serviceId" : service,
            "salonId" : salon

        };

        $.ajax({
            url: mainUrl + "/worker",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newWorker),
            success: function (data) {
                location.reload();
            },
            error: function (error) {
                alert(error.message);
            }
        });
    });
}

function setActionOnUpdateButtons() {
    $(".update-button").each(function (index) {
        $(this).click(function () {

            $.ajax({
                url: mainUrl + "/worker/one" ,
                type: "GET",
                data: {
                    id:$(this).val()
                },
                success: function (data) {
                    $('#fname').val(data.name);
                    $('#id').val(data.id);
                    $('#lname').val(data.surname);
                    $('#phone').val(data.phone);
                    $('#experience').val(data.experience);
                    $('#worker-service').val(data.serviceId);
                    $('input:radio[name="salonId"]').filter('[value="'+data.salonId+'"]').attr('checked', true);

                  if (data.photo) {
                      $("#upload-image").html('<img style="max-width:200px;" src="' +'/img/'+ data.photo+'"> ');
                  }

                  console.log(data.photo);

                    $('#myModal').show();
                    $('#btnUpdateWorker').show();
                    $('#btnCreateWorker').hide();
                    $('#title').text('Update worker');
                    $('#footer').text('Form for update worker');
                },
                error: function (error) {
                    alert(error.message);
                }
            });
        })
    })

}

function setActionOnUpdateBtn() {
    $("#btnUpdateWorker").click(function () {

        var  photo = $("#photo2").val();
        var firstName = $("#fname").val();
        var id = $("#id").val();
        var lastName = $("#lname").val();
        var phone = $("#phone").val();
        var experience = $("#experience").val();
        var service = $("#worker-service").val();
        var salon = $('input[name=salonId]:checked').val();

        if (!firstName) {
            alert('First name can\'t be empty');
            return false;
        }
        if (!lastName) {
            alert('lastName  can\'t be empty');
            return false;
        }
        if (!phone) {
            alert('Phone  can\'t be empty');
            return false;
        }
        if (!experience) {
            alert('Experience  can\'t be empty');
            return false;
        }
        if (!service) {
            alert('Service  can\'t be empty');
            return false;
        }
        if (!salon) {
            alert('Salon  can\'t be empty');
            return false;
        }

        var newWorker = {
            "photo" : photo,
            "id": id,
            "name": firstName,
            "surname": lastName,
            "phone": phone,
            "experience": experience,
            "serviceId": service,
            "salonId": salon

        };

        $.ajax({
            url: mainUrl + "/worker?id="+ id,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(newWorker),
            success: function (data) {
                location.reload();
            },
            error: function (error) {
                alert(error.message);
            }
        });
    });
}

function setModalConfiguration() {
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("openModal");

    // Get the <span> element that closes the modal
    var span = document.getElementById("closeWorkers");

    // When the user clicks the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";

        $('#fname').val('');
        $('#id').val('');
        $('#lname').val('');
        $('#phone').val('');
        $('#experience').val('');
        $('#worker-service').val('');
        $('input:radio[name="salonId"]').first().attr('checked', true);

        $('#btnUpdateWorker').hide();
        $('#btnCreateWorker').show();
        $('#title').text('Create worker');
        $('#footer').text('Form for new worker');
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}

function encodeImageFileAsURL(element) {
    var file = element.files[0];
    var reader = new FileReader();
    reader.onloadend = function() {

        let request = {
            fileName: new Date().getTime(),
            data: reader.result
        };
        $.ajax({
            url: mainUrl + "/upload",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(request),
            success: function (data) {
                addImgToContainer(data);
                $('#photo2').val(data);
            },
            error: function (error) {
                alert(error.message);
            }
        });
    }
    reader.readAsDataURL(file);
}


function addImgToContainer(filename) {
        $('#upload-image').html('<img style="max-width:200px;" src="' +'/img/'+filename+'">');
    }








