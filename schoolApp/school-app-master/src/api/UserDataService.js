import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL, JPA_API_URL} from './Constants'

class UserDataService {

    createUser(user, role){
        AuthenticationService.setupAxiosInterceptors()
        return axios.post(`${API_URL}/${role}/neu`, user)
    }
}
export default new UserDataService