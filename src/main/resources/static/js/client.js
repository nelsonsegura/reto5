const leerClientes = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Client/all',
        type : 'GET',
        dataType : 'json',
        success : function(client) {
            $("tbody").empty();
            let tabla = `<tbody>`;
            for (let i = 0; i < client.length; i++) {
                tabla += `<tr>
                            <th scope="row">${client[i].idClient}</th>
                            <td>${client[i].name}</td>
                            <td>${client[i].email}</td> 
                            <td>${client[i].password}</td> 
                            <td>${client[i].age}</td> 
                            <td>
                                <button type="button" class="btn btn-info" onclick='llenarInputs(${JSON.stringify(client[i])})' data-bs-toggle="modal" data-bs-target="#modalClientEdit" data-bs-whatever="@mdo"><i class="bi bi-pencil-square"></i></button>
                                <button type="button" class="btn btn-danger" id="btnBorrar" onclick="eliminarCliente(${client[i].idClient})"><i class="bi bi-trash3-fill"></i></button>
                            </td>
                          <tr>`
            }
            tabla += '</tbody>'
            $("#clientTable").append(tabla);
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}
window.addEventListener("load", leerClientes);

const llenarInputs = (info) =>{
    $("#idUpdate").val(info.idClient)
    $("#nameUpdate").val(info.name)
    $("#emailUpdate").val(info.email)
    $("#passwordUpdate").val(info.password)
    $("#ageUpdate").val(info.age)
}

const dataClientToEdit = ()=>{
    let data={
        idClient: $("#idUpdate").val(),
        name: $("#nameUpdate").val(),
        email: $("#emailUpdate").val(),
        password: $("#passwordUpdate").val(),
        age: $("#ageUpdate").val()
    };
    return JSON.stringify(data);
}

const dataClientToSend = ()=>{
    let data={
        name: $("#name").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        age: $("#age").val()
    };
    return JSON.stringify(data);
}

const limpiarCampos = ()=>{
    $("#name").val(null);
    $("#email").val(null);
    $("#password").val(null);
    $("#age").val(null);
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
$("#btnAgregarClient").on("click", ()=>{
    guardar('http://150.136.154.173:8080/api/Client/save',dataClientToSend, leerClientes);
    $("#btnAgregarClient").attr("data-bs-dismiss","modal")
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

$("#btnActualizarClient").on("click", ()=>{
    editar('http://150.136.154.173:8080/api/Client/update',dataClientToEdit, leerClientes);
    $("#btnActualizarClient").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha actualizado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});

const eliminarCliente = (idClient) =>{
    let data = {
        id: idClient
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
                url : `http://150.136.154.173:8080/api/Client/${idClient}`,
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
                    leerClientes();
                }
            });
        }
      })
}