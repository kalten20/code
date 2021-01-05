import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class CommunicationDataService {

    createAnnouncement(announcement, visibility) {
        return axios.post(`${API_URL}/announcements/new`,announcement)

    }
    createClassAnnouncement(announcement) {
        return axios.post(`${API_URL}/classAnnouncements/create`,announcement)

    }
    getClassAnnouncements(classId) {
        return axios.get(`${API_URL}/classAnnouncements/${classId}`)
        
    }



    getInbox(role) {
        return axios.get(`${API_URL}/inbox/${role}`)
    }

    editAnnouncement(id, editedAnnouncement) {
        return axios.put(`${API_URL}/announcements/edit/${id}`, editedAnnouncement)

    }
    editClassAnnouncement(announcementId, editedAnnouncement) {
        return axios.put(`${API_URL}/classAnnouncements/edit/${announcementId}`, editedAnnouncement)

    }


    deleteAnnouncement(id) {
        return axios.delete(`${API_URL}/announcements/delete/${id}`)

    }
    deleteClassAnnouncement(announcementId) {
        return axios.delete(`${API_URL}/classAnnouncements/delete/${announcementId}`)

    }

}

export default new CommunicationDataService()