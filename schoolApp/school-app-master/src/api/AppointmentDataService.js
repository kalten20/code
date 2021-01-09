import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class AppointmentDataService {

    checkFieldStatus(appointment) {
        return axios.put(`${API_URL}/appointment/fieldStatus`, appointment)
    }

    getAppointmentByiId(appointmentId) {
        return axios.get(`${API_URL}/appointment/${appointmentId}`)
    }

    getAppointmentByClassIdAndTeaxherIdAndDateAndSlot(appointment) {
        return axios.get(`${API_URL}/appointment/${appointment.classId}/${appointment.teacherId}/${appointment.date}/${appointment.slot}`)


    }

    createAppointment(newAppointment) {
        return axios.post(`${API_URL}/appointment/create`, newAppointment)
    }

    editAppointment(appointmentId, newSubject) {
        return axios.put(`${API_URL}/appointment/${appointmentId}/edit/${newSubject}`)
    }


    deleteAppointment =(appointmentid)=> {
        return axios.delete(`${API_URL}/appointment/${appointmentid}/delete`)
    }

}

export default new AppointmentDataService()