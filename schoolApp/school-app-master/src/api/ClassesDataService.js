import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class ClassesDataService {

    createClass(name){
        //AuthenticationService.setupAxiosInterceptors()
        return axios.post(`${API_URL}/schoolClass/new/${name}`)
    }

    getExistingClassnames() {
        return axios.get(`${API_URL}/schoolClass/all`)
    }

    getUnavailableStudentEmails(){
        return axios.get(`${API_URL}/students/unavailable`)

    }

    addElement (targetClass,role , value) {
        return axios.put(`${API_URL}/schoolClass/${targetClass}/add/${role}/${value}`)
    }

    getClass (targerClass) {
        return axios.get(`${API_URL}/schoolClass/${targerClass}`)
    }

    removeElement(targetClass,role,id) {
        return axios.put(`${API_URL}/schoolClass/${targetClass}/remove/${role}/${id}`)
    }

    getMyClasses(id) {
        return axios.get(`${API_URL}/${id}/myClasses`)
    }

    getMyClass(classId) {
        return axios.get(`${API_URL}/myClass/${classId}`)

    }

    getClassStudents(classId) {
        return axios.get(`${API_URL}/class/${classId}/students/all`)
    }


}

export default new ClassesDataService()