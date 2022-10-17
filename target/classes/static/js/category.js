const leerCategorias = () => {
    $.ajax({
        url : 'http://150.136.154.173:8080/api/Category/all',
        type : 'GET',
        dataType : 'json',
        success : function(category) {
            $("tbody").empty();
            let tabla = `<tbody>`;
            for (let i = 0; i < category.length; i++) {
                tabla += `<tr>
                            <th scope="row">${category[i].id}</th>
                            <td>${category[i].name}</td>
                            <td>${category[i].description}</td> 
                            <td>
                                <button type="button" class="btn btn-info" onclick='llenarInputs(${JSON.stringify(category[i])})' data-bs-toggle="modal" data-bs-target="#modalCategoryEdit" data-bs-whatever="@mdo"><i class="bi bi-pencil-square"></i></button>
                                <button type="button" class="btn btn-danger" id="btnBorrar" onclick="eliminarCategoria(${category[i].id})"><i class="bi bi-trash3-fill"></i></button>
                            </td>
                          <tr>`
            }
            tabla += '</tbody>'
            $("#categoryTable").append(tabla);
        },
        error : function(xhr, status) {
            alert('Disculpe, existió un problema');
        },
    });
}

window.addEventListener("load", leerCategorias);

const llenarInputs = (info) =>{
    $("#idUpdate").val(info.id)
    $("#nameUpdate").val(info.name)
    $("#descriptionUpdate").val(info.description)
}

const dataCategoryToEdit = ()=>{
    let data={
        id: $("#idUpdate").val(),
        name: $("#nameUpdate").val(),
        description: $("#descriptionUpdate").val(),
    };
    return JSON.stringify(data);
}
const limpiarCampos = ()=>{
    $("#name").val(null);
    $("#description").val(null);
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
const dataCategoryToSend = ()=>{
    let data={
        name: $("#nameCategory").val(),
        description: $("#descriptionCategory").val(),
    };
    return JSON.stringify(data);
}

$("#btnAgregarCategory").on("click", ()=>{
    guardar('http://150.136.154.173:8080/api/Category/save',dataCategoryToSend, leerCategorias);
    $("#btnAgregarCategory").attr("data-bs-dismiss","modal")
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

$("#btnActualizarCategory").on("click", ()=>{
    editar('http://150.136.154.173:8080/api/Category/update',dataCategoryToEdit, leerCategorias);
    $("#btnActualizarCategory").attr("data-bs-dismiss","modal")
    Swal.fire({
        position: 'center',
        icon: 'success',
        title: '¡Se ha actualizado correctamente!',
        showConfirmButton: false,
        timer: 1500
      })
});

const eliminarCategoria = (idCat) =>{
    let data = {
        id: idCat
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
                url : `http://150.136.154.173:8080/api/Category/${idCat}`,
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
                    leerCategorias();
                }
            });
        }
      })
}