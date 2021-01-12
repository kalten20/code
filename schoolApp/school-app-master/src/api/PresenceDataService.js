import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class PresenceDataService {

    getPresenceByAppointmentIdAndStudentId(appointmentId, studentId) {
        return axios.get(`${API_URL}/presence/${appointmentId}/${studentId}`)
    }

    createPresence(presence) {
        return axios.post(`${API_URL}/presence/create`, presence)

    }

    editPresence(presenceId, isPresent) {
        return axios.put(`${API_URL}/presence/${presenceId}/edit/${isPresent}`)
    }

}

export default new PresenceDataService()