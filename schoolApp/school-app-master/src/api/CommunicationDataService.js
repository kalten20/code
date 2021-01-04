import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class CommunicationDataService {

    createAnnouncement(announcement, visibility) {
        return axios.post(`${API_URL}/announcements/new`,announcement)

    }

    getInbox(role) {
        return axios.get(`${API_URL}/inbox/${role}`)
    }

    editAnnouncement(id, editedAnnouncement) {
        return axios.put(`${API_URL}/announcements/edit/${id}`, editedAnnouncement)

    }

    deleteAnnouncement(id) {
        return axios.delete(`${API_URL}/announcements/delete/${id}`)

    }

}

export default new CommunicationDataService()