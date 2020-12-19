import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class UserDataService {

    createUser(user, role){
        AuthenticationService.setupAxiosInterceptors()
        return axios.post(`http://localhost:8080/${role}/neu`, user)
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
}
export default new UserDataService()