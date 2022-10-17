const leerReserva = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Reservation/all',
        type : 'GET',
        dataType : 'json',
        success : function(r) {
            $("tbody").empty();
            let tabla = `<tbody>`;
            let date = new Date();
            for (let i = 0; i < r.length; i++) {
                //date = r[i].startDate()
                const {idReservation, startDate, devolutionDate,status} = r[i];
                tabla += `<tr>
                            <th scope="row">${idReservation}</th>
                            <td>${startDate}</td>
                            <td>${devolutionDate}</td>
                            <td>${status}</td>
                            <td>${r[i].box.id} - ${r[i].box.name}</td>
                            <td>${r[i].client.idClient} - ${r[i].client.name}</td>
                            <td>
                                <button type="button" class="btn btn-info" onclick='llenarInputs(${JSON.stringify(r[i])})' data-bs-toggle="modal" data-bs-target="#modalReservaEdit" data-bs-whatever="@mdo"><i class="bi bi-pencil-square"></i></button>
                                <button type="button" class="btn btn-danger" id="btnBorrar" onclick="eliminarReserva(${r[i].idReservation})"><i class="bi bi-trash3-fill"></i></button>
                            </td>
                          <tr>`
            }
            $("#palcoIdSelect option").remove();
            $("#clientIdSelect option").remove();
            leerPalcos();
            leerClientes();
            tabla += '</tbody>'
            $("#reservaTable").append(tabla);
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}
document.addEventListener("DOMContentLoaded", () => {
    leerReserva()
});

const leerPalcos = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Box/all',
        type : 'GET',
        dataType : 'json',
        success : function(c) {
            c.forEach(e => {
                $('#palcoIdSelect').prepend(`<option id="optionPalcos" value=${e.id}>${e.id} - ${e.name}</option>`);
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
                $('#clientIdSelect').prepend(`<option id="optionClients" value=${e.idClient}>${e.idClient} - ${e.name}</option>`);
            });
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}


const limpiarCampos = ()=>{
    $("#startDate").val(null);
    $("#devolutionDate").val(null);
}
const llenarInputs = (info) =>{
    $("#idUpdate").val(info.idReservation)
    $("#idPalcoUpdate").val(info.box.id)
    $("#idClientUpdate").val(info.client.idClient)
    $("#startDateUpdate").val((info.startDate).slice(0,10));
    $("#devolutionDateUpdate").attr("min",(info.startDate).slice(0,10));
    $("#devolutionDateUpdate").val((info.devolutionDate).slice(0,10));
}

const dataReservaToEdit = ()=>{
    let data={
        idReservation: $("#idUpdate").val(),
        startDate: $("#startDateUpdate").val(),
        devolutionDate: $("#devolutionDateUpdate").val(),
        status: $("#IdSelect").val()
    };
    return JSON.stringify(data);
}

const dataReservaToSend = ()=>{
    let data={
        startDate: $("#startDate").val(),
        devolutionDate: $("#devolutionDate").val(),
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

$("#btnAgregarReserva").on("click", ()=>{
    guardar('http://150.136.154.173:8080/api/Reservation/save',dataReservaToSend, leerReserva);
    $("#btnAgregarReserva").attr("data-bs-dismiss","modal")
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

$("#btnActualizarReserva").on("click", ()=>{
    editar('http://150.136.154.173:8080/api/Reservation/update',dataReservaToEdit, leerReserva);
    $("#btnActualizarReserva").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha actualizado correctamente!',
        showConfirmButton: false,
        timer: 1500
    })
});

const eliminarReserva= (idReserva) =>{
    let data = {
        idMessage: idReserva
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
                url : `http://150.136.154.173:8080/api/Reservation/${idReserva}`,
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
                    leerReserva();
                }
            });
        }
    })
}

