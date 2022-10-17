const leerAdmin = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Admin/all',
        type : 'GET',
        dataType : 'json',
        success : function(admin) {
            $("tbody").empty();
            let tabla = `<tbody>`;
            for (let i = 0; i < admin.length; i++) {
                tabla += `<tr>
                            <th scope="row">${admin[i].id}</th>
                            <td>${admin[i].name}</td>
                            <td>${admin[i].email}</td> 
                            <td>
                                <button type="button" class="btn btn-info" onclick='llenarInputs(${JSON.stringify(admin[i])})' data-bs-toggle="modal" data-bs-target="#modalAdminEdit" data-bs-whatever="@mdo"><i class="bi bi-pencil-square"></i></button>
                                <button type="button" class="btn btn-danger" id="btnBorrar" onclick="eliminarAdmin(${admin[i].id})"><i class="bi bi-trash3-fill"></i></button>
                            </td>
                          <tr>`
            }
            tabla += '</tbody>'
            $("#adminTable").append(tabla);
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}
window.addEventListener("load", leerAdmin);

const llenarInputs = (info) =>{
    $("#idUpdate").val(info.id)
    $("#nameUpdate").val(info.name)
    $("#emailUpdate").val(info.email)
    $("#passwordUpdate").val(info.password)
}
const dataAdminToEdit = ()=>{
    let data={
        id: $("#idUpdate").val(),
        name: $("#nameUpdate").val(),
        email: $("#emailUpdate").val(),
        password: $("#passwordUpdate").val(),
    };
    return JSON.stringify(data);
}

const dataAdminToSend = ()=>{
    let data={
        name: $("#name").val(),
        email: $("#email").val(),
        password: $("#password").val(),
    };
    return JSON.stringify(data);
}

const limpiarCampos = ()=>{
    $("#name").val(null);
    $("#email").val(null);
    $("#password").val(null);
}

const guardar = (url, datos, consulta)=> {
    $.ajax({    
        url : url,
        type : 'POST',
        data: datos(),
        contentType:'application/json',
        success : function() {
            limpiarCampos();
        },
        complete: function(){
            consulta();
        }
    });
}

$("#btnAgregarAdmin").on("click", ()=>{
    guardar('http://150.136.154.173:8080/api/Admin/save',dataAdminToSend, leerAdmin);
    $("#btnAgregarAdmin").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha creado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});

const editar = (url, datos, consulta)=>{
    $.ajax({    
        url : url,
        type : 'PUT',
        data: datos(),
        contentType:'application/json',
        success : function() {
        },
        error : function(xhr, status) {
       //     alert('ha sucedido un problema');
        },
        complete: function(){
            consulta();
        }
    });
}

$("#btnActualizarAdmin").on("click", ()=>{
    editar('http://150.136.154.173:8080/api/Admin/update',dataAdminToEdit, leerAdmin);
    $("#btnActualizarAdmin").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha actualizado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});

const eliminarAdmin = (idAdmin) =>{
    let data = {
        id: idAdmin
    }
    Swal.fire({
        title: '¿Estas seguro?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '¡Si, eliminar!'
      }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url : `http://150.136.154.173:8080/api/Admin/${idAdmin}`,
                type : 'DELETE',
                data: JSON.stringify(data),
                contentType:'application/json',
                success : function() {
                    Swal.fire(
                        '¡Eliminado!',
                        'Se ha eliminado correctamente.',
                        'success'
                      )
                },
                error : function(xhr, status) {
                },
                complete: function(){
                    leerAdmin();
                }
            });
        }
      })
}