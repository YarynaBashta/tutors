var mainUrl = "http://localhost:8080";

getAllSubjects();
setModalConfiguration();
setActionOnCreateBtn();


function initLevels() {
    $.ajax({
        url: 'http://localhost:8080/level',
        type: 'get',
        success: function (levels) {
            var $subject = $('#subject-level');
            $subject.html('');

            for (let l of levels) {
                $subject.append(`<input class="subj" value="${l.id}" type="checkbox" id="subj-l-${l.id}">`);
                $subject.append(`<label for="subj-l-${l.id}">${l.name}</label>`);
            }
        },

    })
}


//start when load page PS reload page for triggered http request
function getAllSubjects() {
    $.ajax({
        url: mainUrl + "/subject",
        type: "GET",
        contentType: "application/json",
        success: function (dataResponse) {
            setSubjectsToTable(dataResponse);
            setActionOnDeleteButtons();
            setActionOnUpdateBtn();
            setActionOnUpdateButtons();
        },
        error: function (error) {
            alert(error.message);
        }
    });
}

function setSubjectsToTable(subjects) {
    for (var i = 0; i < subjects.length; i++) {
        setSubjectToTable(subjects[i]);
    }
}

function setSubjectToTable(subject) {

    var tableOfSubjects = $("#subjects");
    let content = '<tr>' +
        '<td>' + subject.name + '</td>' +
        '<td>';
    for (var i = 0; i < subject.levels.length; i++) {
        content += `${subject.levels[i].name} `;

    }
    content += '</td>' +
        '<td><button class="button" value="' + subject.id + '">Delete</button></td>' +
        '<td><button class="update-button" value="' + subject.id + '">Update</button></td>' +
        '</tr>'
    tableOfSubjects.append(content);
}

//delete process
function setActionOnDeleteButtons() {
    $(".button").each(function (index) {
        $(this).click(function () {
            $.ajax({
                url: mainUrl + "/subject?id=" + $(this).val(),
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
    $("#btnCreateSubject").click(function () {
        var subjectName = $("#sname").val();
        var subjectLevel  = [];
        $('.subj').each(function (i, e) {
            var $elem = $(e);
            if ($elem.get(0).checked) {
                //if ($elem.is(":checked")) {
                subjectLevel.push(+$elem.val())

            }
        })
        if (!subjectName) {
            alert('Subject name can\'t be empty');
            return false;
        }

        console.log(subjectLevel)

        var newSubject = {
            "name": subjectName,
            "levelsId": subjectLevel
        };

        $.ajax({
            url: mainUrl + "/subject",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(newSubject),
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
                url: mainUrl + "/subject/one" ,
                type: "GET",
                data: {
                    id:$(this).val()
                },
                success: function (data) {
                    $('#sname').val(data.name);
                    $('#id').val(data.id);
                    //       $('#slevels').val(data.levels);
               //     $('input:radio[name="salonId"]').first().attr('checked', true);


                    $('#myModal').show();
                    $('#btnUpdateSubject').show();
                    $('#btnCreateSubject').hide();
                    $('#title').text('Update subject');
                    $('#footer').text('Form for update subject');
                },
                error: function (error) {
                    alert(error.message);
                }
            });
        })
    })

}
function setActionOnUpdateBtn() {
    $("#btnUpdateSubject").click(function () {

        var subjectName = $("#sname").val();
        var id = $("#id").val();
        var subjectLevel  = [];
        $('.subj').each(function (i, e) {
            var $elem = $(e);
            if ($elem.get(0).checked) {
                //if ($elem.is(":checked")) {
                subjectLevel.push(+$elem.val())

            }
        })

        if (!subjectName) {
            alert('First name can\'t be empty');
            return false;
        }


        var newSubject = {
            "name" : subjectName,
            "id": id,
            "levelsId": subjectLevel
        };

        $.ajax({
            url: mainUrl + "/subject?id="+ id,
            type: "PUT",
            contentType: "application/json",
            data: JSON.stringify(newSubject),
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
        initLevels();
    };

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";

        $('#sname').val('');
        $('#id').val('');
        initLevels();

        // $('.subj').each(function (i, e) {
        //     var $elem = $(e);
        //     if ($elem.get(0).checked) {
        //         //if ($elem.is(":checked")) {
        //         subjectLevel.push(+$elem.val())
        //
        //     }
        // })

        $('#btnUpdateSubject').hide();
        $('#btnCreateSubject').show();
        $('#title').text('Create subject');
        $('#footer').text('Form for new subject');
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
}

