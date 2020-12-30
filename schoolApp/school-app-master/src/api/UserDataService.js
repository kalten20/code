import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class UserDataService {

    createUser(user, role){
        AuthenticationService.setupAxiosInterceptors()
        return axios.post(`http://localhost:8080/${role}/neu`, user)
    }

    updateUser(id, updatedUser){
        AuthenticationService.setupAxiosInterceptors()
        return axios.put(`http://localhost:8080/editUser/${id}`, updatedUser)
    }




    getAllUsers () {
        return axios.get(`http://localhost:8080/users/all`)
    }

    getUserById (id) {
        return axios.get(`http://localhost:8080/users/${id}`)
    }


    getUsersByRole(role) {
        AuthenticationService.setupAxiosInterceptors()
        return axios.get(`http://localhost:8080/${role}/all`)
    }


    approve(id) {
        return axios.put(`http://localhost:8080/admin/approve/${id}`)

    }
    disApprove(id) {
        return axios.put(`http://localhost:8080/admin/disApprove/${id}`)

    }

    getAllEmailsByRole(role) {
       return axios.get(`${API_URL}/${role}/existingEmails`)
    }

    
}
export default new UserDataService()