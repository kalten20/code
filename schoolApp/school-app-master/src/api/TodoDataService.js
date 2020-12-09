import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL, JPA_API_URL} from './Constants'


class TodoDataService {

    retrieveAllTodos(username) {
        let user = 'user'
        let password = 'password'
        //encoding auth header USINT BTOA JS
        let basicAuthHeader = 'Basic ' + window.btoa(user + ":" + password)

        return axios.get(`${JPA_API_URL}/users/${username}/todos`,
        {
            headers : {
                authorization : basicAuthHeader
            }
        }
        
        )

    }

    retrieveTodo(username,id){
        AuthenticationService.setupAxiosInterceptors()
        return axios.get(`${JPA_API_URL}/users/${username}/todos/${id}`)
    }

    deleteTodo(username, id){
        AuthenticationService.setupAxiosInterceptors()
        
        return axios.delete(`${JPA_API_URL}/users/${username}/todos/${id}`)
    }

    

    updateTodo(username,id, todo){
        AuthenticationService.setupAxiosInterceptors()
        return axios.put(`${JPA_API_URL}/${username}/todos/${id}`, todo)
    }
    
    createTodo(username, todo){
        AuthenticationService.setupAxiosInterceptors()
        return axios.post(`${JPA_API_URL}/users/${username}/todos/`, todo)
    }





}

export default new TodoDataService()