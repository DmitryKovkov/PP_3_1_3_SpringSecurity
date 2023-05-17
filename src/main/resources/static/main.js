const usersList = document.querySelector('.tab-body-users')
const addUserForm = document.querySelector('.add-user-form')
const nameValue = document.getElementById('name-id')
const surnameValue = document.getElementById('surname-id')
const ageValue = document.getElementById('age-id')
const passwordValue = document.getElementById('password-id')


let output = '';
const renderUsers = (users) => {
    users.forEach(user => {
        output += `
               <tr data-id="${user.id}">
                <td id="user-id">${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.roles}</td>
                <td>
                    <button type="submit" class="btn btn-danger" id="delete-id" name="${user.id}"> Delete </button>
                </td>
                <td>
                     <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                            data-bs-target="#ModalUser${user.id}">
                                        Редактировать
                                    </button>
                                    
                                    
                    
                     <!-- Modal -->
                                    <div class="modal fade" id="ModalUser${user.id}" tabindex="-1"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="userModalexamplelable">Edit user</h5>
                                                    <button type="button" class="btn-close" id="edit-id" data-bs-dismiss="modal"
                                                            aria-label="Close">x
                                                    </button>
                                                </div>
                                                <form> 
                                              
                                                    <div class="modal-body">
                                                        <!-- СОДЕРЖИМОЕ МОДАЛЬНОГО ОКНА -->
                                                        <div class="container">
                                                            <div class="row">
                                                                <div class="col"></div>
                                                                <div class="col-6">
                                                                    <div class="form-group">
                                                                        <label>
                                                                            <label>ID </label>
                                                                            <input type="text" class="form-control"
                                                                                   name="id-modal"
                                                                                   id="id-modal"
                                                                                   placeholder="${user.id}"
                                                                                   readonly>
                                                                            <label>name </label>
                                                                            <input type="text" class="form-control"
                                                                                   name="name-modal"
                                                                                   id="name-modal"
                                                                                   value="${user.name}">
                                                                            <label>surname</label>
                                                                            <input type="text" class="form-control"
                                                                                   name="surname-modal"
                                                                                   id="surname-modal"
                                                                                   value="${user.surname}">
                                                                            <label>age</label>
                                                                            <input type="number" class="form-control"
                                                                                   name="age-modal"
                                                                                   id="age-modal"
                                                                                   value="${user.age}">
                                                                            <label>password</label>
                                                                            <input type="text" class="form-control"
                                                                                   name="password-modal"
                                                                                   id="password-modal"
                                                                                   value="${user.password}">
                                                                            <div class="form-group">
                                                                                <label> Роли </label> <p>

                                                                            </div>
                                                                        </label>
                                                                    </div>
                                                                </div>
                                                                <div class="col"></div>

                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">Закрыть
                                                        </button>
                                                        <button type="submit" id="edit-id" name="${user.id}" class="btn btn-primary">Сохранить</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                    
                </td>
               </tr>`
        })
    usersList.innerHTML = output
}

const url = 'http://localhost:8080/api/admin'
const urlAddUSer = 'http://localhost:8080/api/admin/add_user'
fetch(url)
    .then(r => r.json())
    .then(data => renderUsers(data))

//DELETE USER
usersList.addEventListener('click', (e) => {

    e.preventDefault()
    let delButtonIsPressed = e.target.id == 'delete-id'
    let editButtonIsPressed = e.target.id == 'edit-id'

    let id = e.target.name
    //console.log(id)
    if (delButtonIsPressed) {
        fetch(`${url}/${id}`, {
            method: 'DELETE',
        })
            .then(res => res.json())
            .then(() => location.reload())
    }

    if (editButtonIsPressed) {
        console.log('edit user')
        fetch(`${url}/${id}`, {
             method: 'PATCH',
            headers: {
                 'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                name: document.getElementById('name-modal').value
            })
         })
            .then(res => res.json())
            .then(res => console.log(res))
    }
})


//new USER
addUserForm.addEventListener('submit', (e) => {
    const rolesValue = document.getElementById('roles-id')
    const selected = Array.from(rolesValue.options)
        .filter(option => option.selected)
        .map(option => option.value)
    console.log(selected)
    e.preventDefault()
    fetch(urlAddUSer, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: nameValue.value,
            surname: surnameValue.value,
            age: ageValue.value,
            password: passwordValue.value,
            //roles: selected
        })
    })
        .then(res => res.json())
        .then(() => location.reload())
        .then(res => console.log(res))
       // .then(() => location.reload())
        // .then(data => {
        //     const dataArr = []
        //     dataArr.push(data)
        //     renderUsers(dataArr)
    //})
})





