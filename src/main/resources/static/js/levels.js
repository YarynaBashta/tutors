var mainUrl = "http://localhost:8080";

getAllLevels();
setModalConfiguration();
setActionOnCreateBtn();
// setActionOnUpdateBtn();
// setActionOnUpdateButtons();


//start when load page PS reload page for triggered http request
function getAllLevels() {
    $.ajax({
        url: mainUrl + "/level",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setLevelsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateBtn();
            setActionOnUpdateButtons()
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setLevelsToTable(levels) {
    for (var i = 0; i < levels.length; i++) {
        setLevelToTable(levels[i]);
    }
}

function setLevelToTable(level) {
    var tableOfLevels = $("#levels");
    tableOfLevels.append('<tr>' +
        '<td>' + level.name + '</td>' +
        '<td><button class="button" value="' + level.id + '">Delete</button></td>' +
        '<td><button class="update-button" value="' + level.id + '">Update</button></td>' +
        '</tr>');
}

//delete process

function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/level?id=" + $(this).val(),
                type: "DELETE",
                success: function (data) {
                    location.reload();
                },
                error: function (error) {
                    alert(error.message);
                }
            });
        })
    })

}

function setActionOnCreateBtn() {
    $("#btnCreateLevel").click(function () {

        var levelName = $("#lname").val();

//            if (firstName != null && lastName != null && age != null) {
        if (!levelName) {
            alert('Level name can\'t be empty');
            return false;
        }

        var newLevel = {
            "name": levelName
        };

        $.ajax({
            url: mainUrl + "/level",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newLevel),
            success: function (data) {
                location.reload();
            },
            error: function (error) {
                alert(error.message);
            }
        });
//            } else {
//                alert("Всі поля повинні бути заповнені")
//            }
    });
}

function setActionOnUpdateButtons() {
    $(".update-button").each(function (index) {
        $(this).click(function () {

            $.ajax({
                url: mainUrl + "/level/one" ,
                type: "GET",
                data: {
                    id:$(this).val()
                },
                success: function (data) {
                    $('#lname').val(data.name);
                    $('#id').val(data.id);

                    $('#myModal').show();
                    $('#btnUpdateLevel').show();
                    $('#btnCreateLevel').hide();
                    $('#title').text('Update level');
                    $('#footer').text('Form for update level');
                },
                error: function (error) {
                    alert(error.message);
                }
            });
        })
    })

}
function setActionOnUpdateBtn() {
    $("#btnUpdateLevel").click(function () {

        var levelName = $("#lname").val();
        var id = $("#id").val();

        if (!levelName) {
            alert('First name can\'t be empty');
            return false;
        }


        var newLevel = {
            "name" : levelName,
            "id": id
        };

        $.ajax({
            url: mainUrl + "/level?id="+ id,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(newLevel),
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
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function () {
        modal.style.display = "block";
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
        $('#lname').val('');
        $('#id').val('');

        $('#btnUpdateLevel').hide();
        $('#btnCreateLevel').show();
        $('#title').text('Create level');
        $('#footer').text('Form for new level');
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}

