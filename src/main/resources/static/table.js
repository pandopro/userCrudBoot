$(document).ready(function () {
    refreshTable();

});

function rolesfortable(data) {
    var str = "";
    data.map(n => str += " " + n.role);
    return str;
}

function fullingTable(data) {
    data.map(n => rolesfortable(n.roles));
    $('#users').append(`
      ${data.map(n => `
        <tr>
        <td>${n.id}</td>
      <td>${n.name}</td>
      <td>${n.lastname}</td>
      <td>${n.age}</td>
      <td>${n.email}</td>
<td> ${rolesfortable(n.roles)}</td>
<!--     rol-->
      <td>  
       <button type="button"  name="userEditCallModal" onclick="buttonEdit(${n.id})"
                                value="${n.id}"
                                class="btn btn-info" data-toggle="modal" data-target="modalEdit">
                            Edit
                        </button>
      </td>
<td> <button class="btn btn-danger" type="submit" name="submit" onclick="buttonDelete(${n.id})">Delete</button>
                      
                    </td>
    </tr>`).join('')}
  
`)
};

function buttonDelete(user_id) {

    $.ajax("/rest/admin/get", {
            method: "post",
            data: {id: user_id},
            dataType: "json",
            success: function (user) {
                $("#modalEdit").modal('show');
                $("#editId").val(user.id);
                $("#editName").val(user.name).attr("disabled", "disabled");
                $("#editLastName").val(user.lastname).attr("disabled", "disabled");
                $("#editAge").val(user.age).attr("disabled", "disabled");
                $("#editPassword").attr("value", user.password).attr("disabled", "disabled");
                $("#editEmail").attr("value", user.email).attr("disabled", "disabled");
                $("#editRoles").attr("value", user.id).attr("disabled", "disabled");
                $("#modal-footer").empty();
                $("#modal-footer").append(
                    "                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">\n" +
                    "                    Close\n" +
                    "                </button>\n" +
                    "                <button type=\"button\" id=\"submitDelBTN\"value=\"\" class=\"btn btn-danger\" onclick=\"deleteUserRest()\" form=\"editModalUser\">REMOVE\n" +
                    "                </button>\n");
                $("#submitDelBTN").val(user_id);
            }
        }
    )
};

function deleteUserRest() {

    const user_id = $("#submitDelBTN").val();
    console.log("deleteUserRest " + user_id);
    $.ajax("/rest/admin/delete", {
        data: {id: user_id},
        method: "post",
        dataType: "json",
        success: function () {
            // allUsers = msg;
            refreshTable();
        }
    });
}

function buttonEdit(user_id) {
    $.ajax("/rest/admin/get", {
            method: "post",
            data: {id: user_id},
            dataType: "json",
            success: function (user) {
                $("#modalEdit").modal('show');
                $("#editId").val(user.id);
                $("#editName").val(user.name).attr("disabled", false);
                $("#editLastName").val(user.lastname).attr("disabled", false);
                $("#editAge").val(user.age).attr("disabled", false);
                $("#editPassword").attr("value", user.password).attr("disabled", false);
                $("#editEmail").attr("value", user.email).attr("disabled", false);
                $("#editRoles").attr("value", user.id).attr("disabled", false);
                $("#editModalUser").attr("value", user.id);
                $("#modal-footer").empty();
                $("#modal-footer").append(
                    "                <button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">\n" +
                    "                    Close\n" +
                    "                </button>\n" +
                    "                <button type=\"button\" value=\"\" class=\"btn btn-primary\" onclick=\"editUser()\" form=\"editModalUser\">Save changes\n" +
                    "                </button>\n");
            }
        }
    )
};

function editUser() {
    $.ajax("/rest/admin/edit", {
        method: "post",
        data: {
            id: $("#editId").val(),
            name: $("#editName").val(),
            lastname: $("#editLastName").val(),
            age: $("#editAge").val(),
            email: $("#editEmail").val(),
            password: $("#editPassword").val(),
            roles: $("#editRoles").val(),
        },
        dataType: "json",
        success: function (data) {
        }
    });
    refreshTable();
}


function addUser() {
    console.log(JSON.stringify({
        name: $("#addName").val(),
        lastname: $("#addLastName").val(),
        age: $("#addAge").val(),
        email: $("#addEmail").val(),
        password: $("#addPassword").val(),
    }));
    $.ajax("/rest/admin/add", {
        method: "post",
        data:
            {
                name: $("#addName").val(),
                lastname: $("#addLastName").val(),
                age: $("#addAge").val(),
                email: $("#addEmail").val(),
                password: $("#addPassword").val(),
                roles: $("#addRoles").val(),
            },
        dataType: "json",
        success: function (data) {

        }
    });

    $("#addName").val("");
    $("#addLastName").val("");
    $("#addAge").val("");
    $("#addEmail").val("");
    $("#addPassword").val("");
    $("#addRoles").val("");
    refreshTable();
}

function refreshTable() {
    $.ajax("/rest/admin/all", {
        dataType: "json",
        success: function (msg) {
            $("#users").empty();
            allUsers = msg;
            fullingTable(allUsers);
            $("#modalEdit").modal('hide');
        }
    });
};

