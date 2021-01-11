import axios from 'axios'
import AuthenticationService from  '../Components/todo/AuthenticationService'
import {API_URL,} from './Constants'

class AppointmentDataService {

    checkFieldStatus(appointment) {
        return axios.put(`${API_URL}/appointment/fieldStatus`, appointment)
    }

    //for class perspective
    checkFieldStatusClassPerspective(classId, date, slot) {
        return axios.get(`${API_URL}/appointment/${classId}/${date}/${slot}/fieldStatus`)
    }

    //For class perspective, gets the available teachers for the dropdown menu
    getAvailableTeachers(classId, date, slot) {
        return axios.get(`${API_URL}/appointment/${classId}/${date}/${slot}/availableTeachers`)

    }
    //For class perspective 

    editRecurrentAppointment(appointmentId, newTeacherId, newTeacherName ,newSubject) {
        return axios.put(`${API_URL}/appointment/${appointmentId}/edit/${newTeacherId}/${newTeacherName}/${newSubject}`)
    }

    getAppointmentByiId(appointmentId) {
        return axios.get(`${API_URL}/appointment/${appointmentId}`)
    }

    getAppointmentByClassIdAndTeaxherIdAndDateAndSlot(appointment) {
        return axios.get(`${API_URL}/appointment/${appointment.classId}/${appointment.teacherId}/${appointment.date}/${appointment.slot}`)

    }

    //FOR class perspective
    getAppointmentByClassIdANndDateAndSlot(classId, date, slot) {
        return axios.get(`${API_URL}/appointment/${classId}/${date}/${slot}`)
    }

    createAppointment(newAppointment, weeks) {
        console.log(weeks)
        if(weeks <= 1) {
            return this.createSingleAppointment(newAppointment)
        } else {
            return this.createRecurrentAppointment(newAppointment, weeks)
        }
    }
    createSingleAppointment(newAppointment) {
        return axios.post(`${API_URL}/appointment/create`, newAppointment)
    }

    //RECURRENT APPOINTMENT
    createRecurrentAppointment(newAppointment, weeks) {
        return axios.post(`${API_URL}/appointment/recurrent/create/${weeks}`, newAppointment)
    }

    editAppointment(appointmentId, newSubject) {
        return axios.put(`${API_URL}/appointment/${appointmentId}/edit/${newSubject}`)
    }


    deleteAppointment =(appointmentid)=> {
        return axios.delete(`${API_URL}/appointment/${appointmentid}/delete`)
    }

    //************************ Class perspective */

    getAvailableTeachersByAppointmentId(appointmentId) {
        return axios.get(`${API_URL}/appointment/${appointmentId}/availableTeachers`)
    }
    getAvailableTeachersByClassIdDateSlot(classId, date, slot) {
        return axios.get(`${API_URL}/appointment/${classId}/${date}/${slot}/availableTeachers`)
    }

}

export default new AppointmentDataService()