import axios from 'axios'
class AuthenticationService {

    registerSuccessfullLogin(username, password) {
       sessionStorage.setItem('authenticatedUser', username) 
       this.setupAxiosInterceptors();

    }

    logout() {
        sessionStorage.removeItem('authenticatedUser')
    }

    isUserLoggedIn(){
        let user = sessionStorage.getItem('authenticatedUser')
        if(user === null) return false
        else return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem('authenticatedUser')
        if(user===null) return ''
        else return user

    }


setupAxiosInterceptors() {
    let user = 'user'
    let password = 'password'

//encoding auth header USINT BTOA JS

let basicAuthHeader = 'Basic ' + window.btoa(user + ":" + password)

    axios.interceptors.request.use(
        (config) => {
            if(this.isUserLoggedIn()) {

            config.headers.authorization = basicAuthHeader
            }
            return config

        }
    )
}
}



export default new AuthenticationService()