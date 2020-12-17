import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class UserDataService {

    createUser(user, role){
        AuthenticationService.setupAxiosInterceptors()
        return axios.post(`http://localhost:8080/${role}/neu`, user)
    }
}
export default new UserDataService()