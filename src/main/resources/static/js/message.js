const leerMs = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Message/all',
        type : 'GET',
        dataType : 'json',
        success : function(ms) {
            $("tbody").empty();
            let tabla = `<tbody>`;
            for (let i = 0; i < ms.length; i++) {
                tabla += `<tr>
                            <th scope="row">${ms[i].idMessage}</th>
                            <td>${ms[i].messageText}</td>
                            <td>${ms[i].box.id} - ${ms[i].box.name}</td>
                            <td>${ms[i].client.idClient} - ${ms[i].client.name}</td>
                            <td>
                                <button type="button" class="btn btn-info" onclick='llenarInputs(${JSON.stringify(ms[i])})' data-bs-toggle="modal" data-bs-target="#modalMsEdit" data-bs-whatever="@mdo"><i class="bi bi-pencil-square"></i></button>
                                <button type="button" class="btn btn-danger" id="btnBorrar" onclick="eliminarMs(${ms[i].idMessage})"><i class="bi bi-trash3-fill"></i></button>
                            </td>
                          <tr>`
            }
            $("option").remove();
            leerPalcos();
            leerClientes();
            tabla += '</tbody>'
            $("#boxMs").append(tabla);
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}

window.addEventListener("load", leerMs);

const leerPalcos = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Box/all',
        type : 'GET',
        dataType : 'json',
        success : function(c) {
            c.forEach(e => {
                $('#palcoIdSelect').prepend(`<option value=${e.id}>${e.id} - ${e.name}</option>`);
            });
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}
const leerClientes = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Client/all',
        type : 'GET',
        dataType : 'json',
        success : function(c) {
            c.forEach(e => {
                $('#clientIdSelect').prepend(`<option value=${e.idClient}>${e.idClient} - ${e.name}</option>`);
            });
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}

const limpiarCampos = ()=>{
    $("#messageText").val(null);
}

const dataMsToSend = ()=>{
    let data={
        messageText: $("#messageText").val(),
        client: {
            "idClient":$("#clientIdSelect").val()
        },
        box: {
            "id":$("#palcoIdSelect").val()
        }
    };
    return JSON.stringify(data);
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
$("#btnAgregarMs").on("click", ()=>{
    guardar('http://150.136.154.173:8080/api/Message/save',dataMsToSend, leerMs);
    $("#btnAgregarMs").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha creado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});


const llenarInputs = (info) =>{
    $("#idUpdate").val(info.idMessage)
    $("#idPalcoUpdate").val(info.box.id)
    $("#idClientUpdate").val(info.client.idClient)
    $("#messageTextUpdate").val(info.messageText)
    $("#statusIdSelect").val(info.status)

}

const dataMsToEdit = ()=>{
    let data={
        idMessage: $("#idUpdate").val(),
        messageText: $("#messageTextUpdate").val()
    };
    return JSON.stringify(data);
}

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

$("#btnActualizarMs").on("click", ()=>{
    editar('http://150.136.154.173:8080/api/Message/update',dataMsToEdit, leerMs);
    $("#btnActualizarMs").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha actualizado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});

const eliminarMs= (idMs) =>{
    let data = {
        idMessage: idMs
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
                url : `http://150.136.154.173:8080/api/Message/${idMs}`,
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
                    leerMs();
                }
            });
        }
      })
}